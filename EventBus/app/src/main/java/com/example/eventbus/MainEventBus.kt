package com.example.eventbus

import getEventsInRealtime
import kotlinx.coroutines.*

private lateinit var eventBus: EventBus
private val job = Job()
private val scope = CoroutineScope(Dispatchers.IO + job)


fun main() {
    initEventBus()
    runBlocking {
        setumSuscriber(scope)
        setpSuscriberTwo(scope)
        setupPublisher()
    }
}

fun setpSuscriberTwo(scope: CoroutineScope) {
    scope.launch {
        eventBus.suscribe<Result> { result -> if (result.isWarning) println("WARNING " + result.sportName) }
    }
}

fun initEventBus() {
    eventBus = EventBus()
}

suspend fun setumSuscriber(coroutineScope: CoroutineScope) {
    coroutineScope.launch {
        eventBus.suscribe<Result> { result ->
            println(result.sportName)
        }
    }
}

suspend fun setupPublisher() {
    getEventsInRealtime().forEach {
        eventBus.publish(it)
        delay(500)
    }
}