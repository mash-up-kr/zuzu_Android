package com.mashup.zuzu.util

import android.content.Context
import android.content.Intent
import android.content.Intent.createChooser
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity

/**
 * @Created by 김현국 2022/08/18
 */

fun shareBitmapImage(context: Context, uri: Uri) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "image/jpeg"
    }
    startActivity(context, createChooser(shareIntent, null), null)
}
