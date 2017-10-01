package net.pterodactylus.gradle.fcp

import net.pterodactylus.fcp.highlevel.FcpClient
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Task that communications with a plugin on a Freenet node using FCP.
 */
open class PluginFcpTask : DefaultTask() {

	val host get() = project.extensions.get<FcpPluginExtension>("fcp").host
	val port get() = project.extensions.get<FcpPluginExtension>("fcp").port
	var plugin = ""
	var message = ""
	var parameters = mapOf<String, String>()
	open internal var fcpClientProvider: FcpClientProvider = { FcpClient(host, port) }
	private val identifier get() = "PluginFcpTask-${System.currentTimeMillis()}-${(Math.random() * Int.MAX_VALUE).toInt()}"

	@TaskAction
	fun runFcpCommand() {
		fcpClientProvider().use { fcpClient ->
			fcpClient.connect("foo")
			val reply = fcpClient.sendPluginMessage(plugin, parameters + ("Message" to message) + ("Identifier" to identifier))
			if (reply["Message"] == "Error") {
				throw IllegalStateException("${reply["ErrorCode"]} ${reply["ErrorMessage"]}")
			}
		}
	}

}
