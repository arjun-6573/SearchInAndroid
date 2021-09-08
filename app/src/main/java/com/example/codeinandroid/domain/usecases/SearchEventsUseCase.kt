package com.example.codeinandroid.domain.usecases

import com.example.codeinandroid.domain.entity.EventEntity
import com.example.codeinandroid.domain.repositories.EventsRepository
import kotlinx.coroutines.flow.Flow


class SearchEventsUseCase(private val repository: EventsRepository) {
    fun invoke(eventId: String): Flow<List<EventEntity>> {
        return repository.searchEvents(eventId)
    }
}