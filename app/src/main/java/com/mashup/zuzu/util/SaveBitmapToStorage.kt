package com.mashup.zuzu.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.net.toUri
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

/**
 * @Created by 김현국 2022/08/18
 */

fun saveBitmapToStorage(context: Context, bitmap: Bitmap) {
    // Generating a file name
    val filename = "${System.currentTimeMillis()}"

    // Output stream
    var fos: OutputStream? = null

    var uri: Uri? = null
    // For devices running android >= Q
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        // getting the contentResolver
        context.contentResolver?.also { resolver ->

            // Content resolver will process the contentvalues
            val contentValues = ContentValues().apply {
                // putting file information in content values
                put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }

            // Inserting the contentValues to contentResolver and getting the Uri
            val imageUri: Uri? =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            uri = imageUri
            // Opening an outputstream with the Uri that we got
            fos = imageUri?.let { resolver.openOutputStream(it) }
        }
    } else {
        // These for devices running on android < Q
        // So I don't think an explanation is needed here
        val imagesDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        uri = imagesDir.absolutePath.toUri()
        val image = File(imagesDir, filename)
        fos = FileOutputStream(image)
    }

    fos?.use { outputStream ->
        // Finally writing the bitmap to the output stream that we opened
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        uri?.let { shareBitmapImage(context = context, uri = it) }
    }
}
