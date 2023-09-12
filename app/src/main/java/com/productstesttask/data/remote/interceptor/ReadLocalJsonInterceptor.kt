package com.productstesttask.data.remote.interceptor

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import javax.inject.Inject

private const val JSON_FILENAME = "sample.json"

class ReadLocalJsonInterceptor @Inject constructor(
    private val context: Context
) : Interceptor {

    override fun intercept(chain: Chain): Response {
        val response = try {
            context.assets.open(JSON_FILENAME).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            ""
        }
        return Response.Builder()
            .code(200)
            .message("")
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .body(ResponseBody.create(MediaType.parse("application/json"), response))
            .addHeader("content-type", "application/json")
            .build()
    }
}
