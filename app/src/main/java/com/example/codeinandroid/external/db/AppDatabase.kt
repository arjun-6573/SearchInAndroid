package com.example.codeinandroid.external.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavouriteEventsRoomEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventsDao(): EventsDao
}