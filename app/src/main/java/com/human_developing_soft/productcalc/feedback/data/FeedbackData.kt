package com.human_developing_soft.productcalc.feedback.data

import com.human_developing_soft.productcalc.BuildConfig
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

data class FeedbackData(
    val feedbackBody: String?,
    val userContact: String?,
    val attachedFile: AttachedFileData?,
) {

    override fun toString() = buildString {
        appendLine(feedbackBody)
        appendLine()
        appendLine(userContact)
        appendLine("App version = ${BuildConfig.VERSION_NAME}")
    }
}

fun FeedbackData.toHttpRequest(botKey: String, chatId: Long): Request {
    return if (attachedFile == null) {
        Request.Builder()
            .url("https://api.telegram.org/bot$botKey/sendMessage")
            .post(
                FormBody.Builder()
                    .add("chat_id", chatId.toString())
                    .add("text", toString())
                    .build()
            )
            .build()
    } else {
        if (attachedFile.isImage()) {
            Request.Builder()
                .url("https://api.telegram.org/bot$botKey/sendPhoto")
                .post(
                    MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("chat_id", chatId.toString())
                        .addFormDataPart("caption", toString())
                        .addFormDataPart(
                            "photo",
                            attachedFile.fileName,
                            attachedFile.fileBytes
                                .toRequestBody(attachedFile.extension.toMediaTypeOrNull())
                        )
                        .build()
                )
                .build()
        } else {
            Request.Builder()
                .url("https://api.telegram.org/bot$botKey/sendVideo")
                .post(
                    MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("chat_id", chatId.toString())
                        .addFormDataPart("caption", toString())
                        .addFormDataPart(
                            "video",
                            attachedFile.fileName,
                            attachedFile.fileBytes
                                .toRequestBody(attachedFile.extension.toMediaTypeOrNull())
                        )
                        .build()
                )
                .build()
        }
    }
}