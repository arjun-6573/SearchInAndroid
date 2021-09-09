package com.example.codeinandroid.data.repositories

import com.example.codeinandroid.domain.datasources.EventsLocalDataSource
import com.example.codeinandroid.domain.datasources.EventsRemoteDataSource
import com.example.codeinandroid.domain.entity.EventEntity
import com.example.codeinandroid.domain.repositories.EventsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

class EventsRepositoryImpl(
    private val eventRemoteDataSource: EventsRemoteDataSource,
    private val eventLocalDataSource: EventsLocalDataSource
) : EventsRepository {

    override fun searchEvents(key: String): Flow<List<EventEntity>> {
        return eventRemoteDataSource.searchEvents(key).filter {
            it.forEach { event ->
                event.isFavourite = eventLocalDataSource.getEventCountById(event.id) > 0
            }
            true
        }
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