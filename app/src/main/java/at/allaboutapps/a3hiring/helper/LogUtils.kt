package com.example.run.helper

import timber.log.Timber
import java.io.PrintWriter
import java.io.StringWriter

fun logThrowable(t: Throwable) {
    Timber.e(t)
    // TODO: send logs to analytics and servers
}
