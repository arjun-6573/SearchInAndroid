package com.example.codeinandroid.domain.usecases

import com.example.codeinandroid.domain.repositories.EventsRepository


class MarkAndUnMarkEventAsFavouriteUseCase(private val repository: EventsRepository) {
    suspend fun invoke(eventId: String) {
        repository.markAndUnMarkEventAsFavourite(eventId)
    }
}