package com.example.eventbus

import getEventsInRealtime
import getResultEventsInRealtime
import kotlinx.coroutines.*
import kotlin.random.Random

private lateinit var eventBus: EventBus
private val job = Job()
private val scope = CoroutineScope(Dispatchers.IO + job)


fun main() {
    initEventBus()
    runBlocking {
//        setumSuscriber(scope)
//        setpSuscriberTwo(scope)
//        setupPublisher()

        setupSubscribeResult(scope)
        setupPublishers()
    }
}

suspend fun setupPublishers() {
    getResultEventsInRealtime().forEach{ result->
        delay(someTime());
        eventBus.publish(result)
    }
}

fun setupSubscribeResult(scope: CoroutineScope) {
    scope.launch {
        eventBus.suscribe<SportEvent.ResultSuccess> {
            event -> println("Result: ${event.sportName}")
        }
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

fun someTime(): Long = Random.nextLong(500, 1_000)