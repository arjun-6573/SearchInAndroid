package com.example.codeinandroid.external.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FAVOURITE_EVENTS")
data class FavouriteEventsRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "EVENT_ID") val eventId: String
)