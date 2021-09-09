package com.example.codeinandroid.domain.entity

import java.util.*

data class EventEntity(
    val id: String,
    val eventName: String,
    val image: String,
    val venue: VenueEntity,
    val dateTime: Date,
    var isFavourite: Boolean
)