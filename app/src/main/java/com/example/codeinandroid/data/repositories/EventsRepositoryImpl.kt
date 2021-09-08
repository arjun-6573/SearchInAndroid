package com.example.codeinandroid.data.repositories

import com.example.codeinandroid.domain.datasources.EventsLocalDataSource
import com.example.codeinandroid.domain.datasources.EventsRemoteDataSource
import com.example.codeinandroid.domain.entity.EventEntity
import com.example.codeinandroid.domain.repositories.EventsRepository
import kotlinx.coroutines.flow.Flow

class EventsRepositoryImpl(
    private val eventRemoteDataSource: EventsRemoteDataSource,
    private val eventLocalDataSource: EventsLocalDataSource
) : EventsRepository {

    override fun searchEvents(key: String): Flow<List<EventEntity>> {
        return eventRemoteDataSource.searchEvents(key)
    }

    override suspend fun markAndUnMarkEventAsFavourite(eventId: String) {
        with(eventLocalDataSource) {
            if (getEventCountById(eventId) > 0) {
                deleteEventId(eventId)
            } else {
                saveEventId(eventId)
            }
        }
    }
}