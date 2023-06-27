package com.example.randomusergenerator.utils

import com.example.randomusergenerator.data.remote.ApiService
import com.example.randomusergenerator.data.remote.dto.UserResponse
import com.example.randomusergenerator.utils.Constants.CONNECT_EXCEPTION
import com.example.randomusergenerator.utils.Constants.SOCKET_TIME_OUT_EXCEPTION
import com.example.randomusergenerator.utils.Constants.SSL_EXCEPTION
import com.example.randomusergenerator.utils.Constants.UNKNOWN_HOST_EXCEPTION
import com.example.randomusergenerator.utils.Constants.UNKNOWN_NETWORK_EXCEPTION
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.json.JSONException
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Resource<T> {
    try {
        val response = call.invoke()
        if (response.isSuccessful) {
            if (response.body() != null) {
                return Resource.Success(response.body())
            }
        }
        return Resource.Error(response.message())
    } catch (e: Exception) {
        return when (e) {
            is ConnectException -> {
                Resource.Error(CONNECT_EXCEPTION)
            }
            is UnknownHostException -> {
                Resource.Error(UNKNOWN_HOST_EXCEPTION)
            }
            is SocketTimeoutException -> {
                Resource.Error(SOCKET_TIME_OUT_EXCEPTION)
            }
            is HttpException -> {
                try {
                    val type = Types.newParameterizedType(ApiService::class.java)
                    val adapter: JsonAdapter<ApiResponse<Response<T>>> =
                        Moshi.Builder().build().adapter(type)
                    val errorMessage = adapter.fromJson(e.response()?.errorBody()?.string()!!)
                    Resource.Error(errorMessage?.message)
                } catch (err: JSONException) {
                    Resource.Error(UNKNOWN_NETWORK_EXCEPTION)
                } catch (err: IOException) {
                    Resource.Error(UNKNOWN_NETWORK_EXCEPTION)
                } catch (err: SSLException) {
                    Resource.Error( SSL_EXCEPTION)
                }
            }
            else -> {
                Resource.Error(UNKNOWN_NETWORK_EXCEPTION)
            }
        }
    }
}
