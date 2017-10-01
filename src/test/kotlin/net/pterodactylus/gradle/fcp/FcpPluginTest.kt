package net.pterodactylus.gradle.fcp

import assertk.assert
import assertk.assertions.containsAll
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import net.pterodactylus.fcp.highlevel.FcpClient
import org.gradle.api.Project
import org.gradle.api.tasks.TaskExecutionException
import org.gradle.testfixtures.ProjectBuilder
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.verify
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

/**
 * Unit test for [FcpPlugin].
 */
class FcpPluginTest {

	private lateinit var plugin: FcpPlugin
	private lateinit var project: Project

	private val pluginExtension get() = project.extensions.get<FcpPluginExtension>("fcp")
	private val pluginFcpTask get() = project.tasks.get<PluginFcpTask>("pluginFcp")

	@BeforeMethod
	fun init() {
		plugin = FcpPlugin()
		project = ProjectBuilder.builder().build()
	}

	@Test
	fun `plugin adds extension container with default settings`() {
		plugin.apply(project)
		assert(pluginExtension.host).isEqualTo("localhost")
		assert(pluginExtension.port).isEqualTo(9481)
	}

	@Test
	fun `plugin forwards configuration from plugin to pluginFcp task`() {
		plugin.apply(project)
		assert(pluginFcpTask.host).isEqualTo("localhost")
		assert(pluginFcpTask.port).isEqualTo(9481)
		pluginExtension.port = 9482
		assert(pluginFcpTask.port).isEqualTo(9482)
	}

	@Test(expectedExceptions = arrayOf(TaskExecutionException::class))
	fun `task throws exception if plugin replies with an error`() {
		plugin.apply(project)
		val fcpClient = mock<FcpClient>()
		pluginFcpTask.fcpClientProvider = { fcpClient }
		val pluginClass = "test.plugin"
		pluginFcpTask.plugin = pluginClass
		val message = "TestMessage"
		pluginFcpTask.message = message
		val parameters = mapOf("foo" to "bar", "baz" to "quo")
		pluginFcpTask.parameters = parameters
		whenever(fcpClient.sendPluginMessage(anyString(), any())).thenReturn(mapOf(
				"Message" to "Error",
				"ErrorMesssage" to "TestError",
				"ErrorCode" to "XXX"
		))

		// when
		try {
			pluginFcpTask.execute()
		} finally {
			// then
			verify(fcpClient).connect(anyString())
			val capturedParameters = capture<Map<String, String>>()
			verify(fcpClient).sendPluginMessage(eq(pluginClass), capturedParameters.capture())
			assert(capturedParameters.value).containsAll(*(parameters + ("Message" to "TestMessage")).toList().toTypedArray())
			assert(capturedParameters.value["Identifier"]).isNotNull()
		}
	}

}
