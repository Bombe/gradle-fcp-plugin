package net.pterodactylus.gradle.fcp

import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.stubbing.Answer
import org.mockito.stubbing.OngoingStubbing

inline fun <reified T> mock(defaultAnswer: Answer<Any> = Mockito.RETURNS_DEFAULTS) = Mockito.mock(T::class.java, defaultAnswer)!!

fun <T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)!!

inline fun <reified T : Any> capture(): ArgumentCaptor<T> = ArgumentCaptor.forClass<T, T>(T::class.java)
