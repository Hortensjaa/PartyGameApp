package com.example.fiksjagame.data

import kotlinx.serialization.Serializable

@Serializable
data class Question (
    val header: Headers,
    val text: String
)

enum class Headers {
    WHO {
        override fun toString() = "Who..."
    },
    SAY {
        override fun toString() = "Who said that?"
    },
    DO {
        override fun toString() = "Who is more likely to...?"
    },
    TRAIT {
        override fun toString() = "Who is this term most suitable for?"
    },
}