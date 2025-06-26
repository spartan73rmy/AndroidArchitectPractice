package com.example.eventbus
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.*
import kotlin.coroutines.coroutineContext


class EventBus {
    private val _events = MutableSharedFlow<Any>()
    val events: SharedFlow<Any> = _events

    suspend fun publish(event: Any){
        _events.emit(event)
    }

    suspend inline fun <reified T> suscribe(crossinline onEvent:(T)->Unit){
        events.filterIsInstance<T>()
            .collectLatest {
                event->
                coroutineContext.ensureActive()
                onEvent(event)
            }
    }
}