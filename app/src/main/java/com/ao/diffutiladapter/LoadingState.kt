package com.ao.diffutiladapter

import kotlinx.coroutines.delay
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

sealed class LoadingState {
    object Loading : LoadingState()
    data class Error(val throwable: Throwable) : LoadingState()

}

object  Error : Throwable(message = "Diffutil adapter RecyclerView")
private val count = AtomicInteger(0)
private val throwError = AtomicBoolean(true)


suspend fun getUsers(start: Int, limit: Int): List<User> {
    delay(3_000)
    if (count.getAndIncrement() == 5 && throwError.compareAndSet(true,false)) {
        throw Error
    }
        return List(limit){ User(
            uid = start + it,
            name = "Name$ ${start + it}",
            email = "Email$ ${start + it}@AO.COM"
            )}
}

