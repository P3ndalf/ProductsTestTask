package com.productstesttask.data.remote.service.base

import com.productstesttask.data.remote.service.base.Answer.Failure

class Answer<out T>(val value: Any?) {

    val isSuccess: Boolean
        get() = value !is Failure

    val isFailure: Boolean
        get() = value is Failure

    fun errorOrNull(): Failure? = when (value) {
        is Failure -> value
        else -> null
    }

    override fun toString(): String =
        when (value) {
            is Failure -> value.toString()
            else -> "Success($value)"
        }

    data class Failure(val errorCode: ErrorCode)

    companion object {
        fun <T> success(value: T): Answer<T> = Answer(value)
        fun <T> failure(code: ErrorCode): Answer<T> = Answer(Failure(code))
    }
}

@Suppress("unchecked_cast")
inline fun <R, T> Answer<T>.map(transform: (value: T) -> R): Answer<R> {
    return when {
        isSuccess -> Answer.success(transform(value as T))
        else -> Answer(value)
    }
}

inline fun <T> Answer<T>.onFailure(action: (error: Failure) -> Unit): Answer<T> {
    errorOrNull()?.let { action(it) }
    return this
}

@Suppress("unchecked_cast")
inline fun <T> Answer<T>.onSuccess(action: (value: T) -> Unit): Answer<T> {
    if (isSuccess) action(value as T)
    return this
}
