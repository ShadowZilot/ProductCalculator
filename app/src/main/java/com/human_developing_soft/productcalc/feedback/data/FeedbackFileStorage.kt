package com.human_developing_soft.productcalc.feedback.data

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import java.io.File
import javax.inject.Inject


interface FeedbackFileStorage {

    suspend fun saveFileByUri(uri: Uri): AttachedFileData
}

class FeedbackFileStorageImpl @Inject constructor(
    private val context: Context
) : FeedbackFileStorage {
    private val filesDir = context.filesDir

    override suspend fun saveFileByUri(uri: Uri): AttachedFileData {
        val mime = MimeTypeMap.getSingleton()
        val extension = mime.getExtensionFromMimeType(context.contentResolver.getType(uri)) ?: ""
        val file = File(filesDir, "${uri.lastPathSegment}.${extension}")
        context.contentResolver.openInputStream(uri).use { input ->
            file.outputStream().use { output ->
                input?.copyTo(output)
            }
        }
        if (file.length() >= 20_971_520) { // 20 MB
            throw FileTooBigException()
        }
        val bytes = file.readBytes()
        val fileName = "${uri.lastPathSegment}.${extension}"
        file.delete()
        return AttachedFileData(bytes, fileName, extension)
    }
}