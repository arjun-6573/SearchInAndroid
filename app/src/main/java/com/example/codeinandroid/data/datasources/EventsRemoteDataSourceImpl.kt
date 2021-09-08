package com.example.codeinandroid.data.datasources

import com.example.codeinandroid.data.mappers.EventsDataMapper
import com.example.codeinandroid.domain.datasources.EventsRemoteDataSource
import com.example.codeinandroid.domain.entity.EventEntity
import com.example.codeinandroid.external.remote.MyApi
import com.example.codeinandroid.external.remote.SafeApiRequest
import com.example.codeinandroid.utils.dispatcher.MyDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class EventsRemoteDataSourceImpl(
    private val api: MyApi,
    private val dispatchers: MyDispatchers,
    private val eventsDataMapper: EventsDataMapper,
) :
    EventsRemoteDataSource,
    SafeApiRequest() {
    override fun searchEvents(key: String): Flow<List<EventEntity>> {
        return flow {
            val response = apiRequest { api.searchArtistAsync(key) }
            emit(response)
        }.flowOn(dispatchers.IO).map {
            eventsDataMapper.toEventsEntity(it)
        }.flowOn(dispatchers.Computation)
    }
}