package com.productstesttask.data.remote.service.base

import com.productstesttask.data.remote.service.base.ErrorCode.EMPTY_BODY_ERROR
import com.productstesttask.data.remote.service.base.ErrorCode.INTERNAL_ERROR
import retrofit2.Response

abstract class BaseService {

    protected suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Answer<T> {
        val response: Response<T> = try {
            call.invoke()
        } catch (exception: Exception) {
            return Answer.failure(INTERNAL_ERROR)
        }

        return if (response.isSuccessful) {
            if (response.body() == null) Answer.failure(EMPTY_BODY_ERROR)
            else Answer.success(response.body()!!)
        } else {
            Answer.failure(INTERNAL_ERROR)
        }
    }
}
