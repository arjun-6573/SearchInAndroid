package com.example.codeinandroid.ui.mappers

import com.example.codeinandroid.domain.entity.EventEntity
import com.example.codeinandroid.domain.entity.VenueEntity
import com.example.codeinandroid.ui.model.EventUIItemModel

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
                "WED, 12 JUN 2019 8:00 PM",
//                todo format date
                toAddress(venue),
                isFavourite
            )
        }
    }

    fun toAddress(venueEntity: VenueEntity): String {
        return "${venueEntity.city}, ${venueEntity.country}"
    }
}