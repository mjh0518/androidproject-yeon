package com.example.project_yeon.core.common.image

import android.content.Context
import android.net.Uri
import java.io.File

object ImageStorageHelper {
    fun copyProfileImageToInternalStorage(
        context: Context,
        sourceUri: Uri
    ): String? {
        return try {
            val inputStream = context.contentResolver.openInputStream(sourceUri) ?: return null
            val fileName = "profile_${System.currentTimeMillis()}.jpg"
            val targetFile = File(context.filesDir, fileName)

            inputStream.use { input ->
                targetFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }

            targetFile.absolutePath
        } catch (e: Exception) {
            null
        }
    }
}