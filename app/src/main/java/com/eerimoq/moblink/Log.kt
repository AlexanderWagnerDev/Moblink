package com.eerimoq.moblink

import android.util.Log
import java.time.LocalDateTime

class Logger {
    private var log: ArrayDeque<String> = ArrayDeque()

    fun log(message: String) {
        Log.i("Moblink", message)
        synchronized(this) {
            if (log.size > 1000) {
                log.removeFirst()
            }
            val timestamp = LocalDateTime.now()
            log.add("$timestamp: $message")
        }
    }

    fun formatLog(): String {
        synchronized(this) {
            return log.joinToString("\n")
        }
    }

    fun makeSmaller() {
        synchronized(this) {
            val newSize = (log.size - 100).coerceAtLeast(0)
            while (log.size > newSize) {
                log.removeFirst()
            }
        }
    }
}

val logger = Logger()
