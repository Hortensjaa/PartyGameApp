package com.example.fiksjagame.data

import kotlinx.serialization.Serializable

@Serializable
data class Vote(
    val player: String,
    val vote: String
)
