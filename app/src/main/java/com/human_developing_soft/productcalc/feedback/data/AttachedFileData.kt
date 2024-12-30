package com.human_developing_soft.productcalc.feedback.data

data class AttachedFileData(
    val fileBytes: ByteArray,
    val fileName: String,
    val extension: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AttachedFileData

        if (!fileBytes.contentEquals(other.fileBytes)) return false
        if (fileName != other.fileName) return false
        if (extension != other.extension) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fileBytes.contentHashCode()
        result = 31 * result + fileName.hashCode()
        result = 31 * result + extension.hashCode()
        return result
    }
}

fun AttachedFileData.isImage(): Boolean {
    val lowercaseExtension = extension.lowercase()
    val imageFormats = listOf("png", "jpeg", "jpg", "raw", "avif", "heif", "heig")
    return imageFormats.contains(lowercaseExtension)
}