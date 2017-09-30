package net.pterodactylus.gradle.fcp

import assertk.assert
import assertk.assertions.isEqualTo
import org.gradle.testfixtures.ProjectBuilder
import org.testng.annotations.Test

/**
 * Unit test for [FcpPlugin].
 */
class FcpPluginTest {

	private val plugin = FcpPlugin()
	private val project = ProjectBuilder.builder().build()

	@Test
	fun `plugin adds extension container with default settings`() {
		plugin.apply(project)
		assert((project.extensions.getByName("fcp") as FcpPluginExtension).host).isEqualTo("localhost")
		assert((project.extensions.getByName("fcp") as FcpPluginExtension).port).isEqualTo(9481)
	}

}
