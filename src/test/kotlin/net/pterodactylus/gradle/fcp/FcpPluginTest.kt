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

	@BeforeMethod
	fun init() {
		plugin = FcpPlugin()
		project = ProjectBuilder.builder().build()
	}

	@Test
	fun `plugin adds extension container with default settings`() {
		plugin.apply(project)
		assert((project.extensions.getByName("fcp") as FcpPluginExtension).host).isEqualTo("localhost")
		assert((project.extensions.getByName("fcp") as FcpPluginExtension).port).isEqualTo(9481)
	}

	@Test
	fun `plugin forwards configuration from plugin to pluginFcp task`() {
		plugin.apply(project)
		assert((project.tasks.getByName("pluginFcp") as PluginFcpTask).host).isEqualTo("localhost")
		assert((project.tasks.getByName("pluginFcp") as PluginFcpTask).port).isEqualTo(9481)
		(project.extensions.getByName("fcp") as FcpPluginExtension).port = 9482
		assert((project.tasks.getByName("pluginFcp") as PluginFcpTask).port).isEqualTo(9482)
	}

}
