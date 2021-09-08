package com.example.codeinandroid.external.db

import androidx.room.*

@Dao
interface EventsDao {

    @Query("SELECT COUNT(*) FROM FAVOURITE_EVENTS WHERE EVENT_ID = :eventId")
    suspend fun getFavouriteEventsCount(eventId: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavourite(favouriteEventsRoomEntity: FavouriteEventsRoomEntity): Long

    @Query("DELETE FROM FAVOURITE_EVENTS WHERE EVENT_ID = :eventId")
    fun removeFromFavourite(eventId: String)
}