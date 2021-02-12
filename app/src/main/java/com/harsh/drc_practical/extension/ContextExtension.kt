package com.harsh.drc_practical.extension

import android.content.Context
import android.widget.Toast

fun Context.makeToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}