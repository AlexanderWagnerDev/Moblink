package com.eerimoq.moblink

import android.util.Log
import java.time.LocalDateTime

class Logger {
    private var log: ArrayDeque<String> = ArrayDeque()

    fun log(message: String) {
        Log.i("Moblink", message)
        if (log.size > 100000) {
            log.removeFirst()
        }
        val timestamp = LocalDateTime.now()
        log.add("$timestamp: $message")
    }

    fun formatLog(): String {
        return log.joinToString("\n")
    }
}

val logger = Logger()
