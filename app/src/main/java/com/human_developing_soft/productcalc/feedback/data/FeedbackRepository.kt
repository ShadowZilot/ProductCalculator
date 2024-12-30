package com.human_developing_soft.productcalc.feedback.data

import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface FeedbackRepository {

    suspend fun sendFeedback(feedbackData: FeedbackData)
}

class FeedbackRepositoryImpl @Inject constructor(
    private val client: OkHttpClient,
    private val botValueProvider: BotKeysValueProvider
) : FeedbackRepository {

    override suspend fun sendFeedback(feedbackData: FeedbackData) {
        suspendCancellableCoroutine { continuation ->
            botValueProvider.getBotApiValue(onBotApiComplete = {
                val (botToken, groupId) = it
                val request = feedbackData.toHttpRequest(botToken, groupId)
                val call = client.newCall(request)
                continuation.invokeOnCancellation { call.cancel() }
                call.enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        continuation.resumeWithException(e)
                    }

                    override fun onResponse(call: Call, response: Response) {
                        response.close()
                        continuation.resume(Unit)
                    }
                })
            }, onFailure = {
                continuation.resumeWithException(WrongBotApiValueException())
            })
        }
    }
}