package com.example.codeinandroid.utils.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface MyDispatchers {
    val IO: CoroutineDispatcher
    val Main: CoroutineDispatcher
    val Computation: CoroutineDispatcher
}