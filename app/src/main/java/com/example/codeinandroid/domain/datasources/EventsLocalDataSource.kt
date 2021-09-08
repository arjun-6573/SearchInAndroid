package com.example.codeinandroid.domain.datasources


interface EventsLocalDataSource {
    suspend fun getEventCountById(eventId: String): Int
    suspend fun saveEventId(eventId: String): Long
    suspend fun deleteEventId(eventId: String)
}