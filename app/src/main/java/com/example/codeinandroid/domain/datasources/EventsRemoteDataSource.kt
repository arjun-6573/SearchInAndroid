package com.example.codeinandroid.domain.datasources

import com.example.codeinandroid.domain.entity.EventEntity
import kotlinx.coroutines.flow.Flow

interface EventsRemoteDataSource {
    fun searchEvents(key: String): Flow<List<EventEntity>>
}