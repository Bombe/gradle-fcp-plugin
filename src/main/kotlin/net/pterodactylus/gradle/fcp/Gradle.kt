package net.pterodactylus.gradle.fcp

import org.gradle.api.plugins.ExtensionContainer

@Suppress("UNCHECKED_CAST")
fun <T> ExtensionContainer.get(name: String) = getByName(name) as T
