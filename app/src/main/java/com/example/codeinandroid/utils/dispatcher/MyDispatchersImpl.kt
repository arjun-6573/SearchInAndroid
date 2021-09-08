package com.example.codeinandroid.utils.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class MyDispatchersImpl : MyDispatchers {
    override val IO: CoroutineDispatcher = Dispatchers.IO
    override val Main: CoroutineDispatcher = Dispatchers.Main
    override val Computation: CoroutineDispatcher = Dispatchers.Default
}