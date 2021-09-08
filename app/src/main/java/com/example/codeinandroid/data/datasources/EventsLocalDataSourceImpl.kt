package com.example.codeinandroid.data.datasources

import com.example.codeinandroid.data.mappers.EventsDataMapper
import com.example.codeinandroid.domain.datasources.EventsLocalDataSource
import com.example.codeinandroid.external.db.AppDatabase
import com.example.codeinandroid.utils.dispatcher.MyDispatchers
import kotlinx.coroutines.withContext

class EventsLocalDataSourceImpl(
    private val appDatabase: AppDatabase,
    private val dispatchers: MyDispatchers,
    private val eventsDataMapper: EventsDataMapper
) : EventsLocalDataSource {
    override suspend fun getEventCountById(eventId: String): Int = withContext(dispatchers.IO) {
        appDatabase.eventsDao().getFavouriteEventsCount(eventId)
    }

    override suspend fun saveEventId(eventId: String): Long = withContext(dispatchers.IO) {
        appDatabase.eventsDao()
            .addToFavourite(eventsDataMapper.toFavouriteEventRoomModal(eventId))
    }

    override suspend fun deleteEventId(eventId: String) = withContext(dispatchers.IO) {
        appDatabase.eventsDao().removeFromFavourite(eventId)
    }
}