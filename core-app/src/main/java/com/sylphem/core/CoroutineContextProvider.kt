package com.sylphem.core

import kotlin.coroutines.CoroutineContext

@Suppress("PropertyName")
interface CoroutineContextProvider {

    val UI: CoroutineContext

    val IO: CoroutineContext

    val Default: CoroutineContext
}