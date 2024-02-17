package com.maksim.mynotes.domain

abstract class AsyncResult<T> {
    companion object {
        fun <T> success(data: T): Data<T> {
            return Data(data)
        }

        fun <T> error(e: Throwable): Error<T> {
            return Error(e)
        }
    }

    data class Data<V>(val data: V) : AsyncResult<V>()
    data class
    Error<E>(val error: Throwable) : AsyncResult<E>()
}