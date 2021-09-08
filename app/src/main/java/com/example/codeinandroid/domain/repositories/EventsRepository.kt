package com.example.codeinandroid.domain.repositories

import com.example.codeinandroid.domain.entity.EventEntity
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    fun searchEvents(key: String): Flow<List<EventEntity>>
    suspend fun markAndUnMarkEventAsFavourite(eventId: String)
}