package com.example.codeinandroid.data.mappers

import com.example.codeinandroid.domain.entity.EventEntity
import com.example.codeinandroid.domain.entity.VenueEntity
import com.example.codeinandroid.external.db.FavouriteEventsRoomEntity
import com.example.codeinandroid.external.remote.EventsResponseModel
import java.util.*

class EventsDataMapper {
    fun toEventsEntity(response: EventsResponseModel): List<EventEntity> {
        return response.events.map { toEventEntity(it) }
    }

    fun toEventEntity(event: EventsResponseModel.Event): EventEntity {
        return event.run {
            EventEntity(
                "$id",
                shortTitle,
                performers.firstOrNull()?.image.orEmpty(),
                toVenueEntity(venue),
                Date()
//            TODO map date from API response
            )
        }
    }

    fun toVenueEntity(venue: EventsResponseModel.Event.Venue): VenueEntity {
        return venue.run {
            VenueEntity(
                address,
                country,
                city,
            )
        }
    }

    fun toFavouriteEventRoomModal(eventId: String) = FavouriteEventsRoomEntity(0, eventId)
}