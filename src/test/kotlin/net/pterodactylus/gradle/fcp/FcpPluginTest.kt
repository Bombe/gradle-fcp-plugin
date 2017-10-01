package net.pterodactylus.gradle.fcp

import assertk.assert
import assertk.assertions.isEqualTo
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
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

}
