package com.example.fiksjagame.data

import kotlinx.serialization.Serializable

@Serializable
data class Question (
    val header: Headers,
    val text: String
)

enum class Headers {
    WHO {
        override fun toString() = "Kto..."
    },
    SAY {
        override fun toString() = "Kto by to powiedział?"
    },
    DO {
        override fun toString() = "Kto byłby w stanie to zrobić?"
    },
}