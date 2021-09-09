package com.example.codeinandroid.ui.mappers

import com.example.codeinandroid.domain.entity.EventEntity
import com.example.codeinandroid.domain.entity.VenueEntity
import com.example.codeinandroid.ui.model.EventUIItemModel
import com.example.codeinandroid.utils.formatDate

class EventsUIMapper {

    fun toEventsUIItemModel(list: List<EventEntity>): List<EventUIItemModel> {
        return list.map { toEventUIItemModel(it) }
    }

    fun toEventUIItemModel(event: EventEntity): EventUIItemModel {
        return event.run {
            EventUIItemModel(
                id,
                image,
                eventName,
                formatDate(dateTime),
                toAddress(venue),
                isFavourite
            )
        }
    }

    fun toAddress(venueEntity: VenueEntity): String {
        return "${venueEntity.city}, ${venueEntity.country}"
    }
}