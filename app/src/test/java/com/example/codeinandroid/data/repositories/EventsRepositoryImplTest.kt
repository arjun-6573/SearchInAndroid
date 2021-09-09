package com.example.codeinandroid.data.repositories

import com.example.codeinandroid.domain.datasources.EventsLocalDataSource
import com.example.codeinandroid.domain.datasources.EventsRemoteDataSource
import com.example.codeinandroid.domain.entity.EventEntity
import com.example.codeinandroid.domain.entity.VenueEntity
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class EventsRepositoryImplTest {

    @RelaxedMockK
    private lateinit var localEventsLocalDataSource: EventsLocalDataSource

    @RelaxedMockK
    private lateinit var remoteEventsLocalDataSource: EventsRemoteDataSource

    private lateinit var repositoryImpl: EventsRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repositoryImpl =
            EventsRepositoryImpl(remoteEventsLocalDataSource, localEventsLocalDataSource)
    }

    private val dummyEventList = listOf(
        EventEntity(
            "1", "name 1", "image 1",
            VenueEntity("address 1", "country 1", "city 1"),
            Date(), false
        ),
        EventEntity(
            "2", "name 2", "image 2",
            VenueEntity("address 2", "country 2", "city 2"),
            Date(), false
        )
    )

    @Test
    fun testSearchEvents() = runBlocking {
        every { remoteEventsLocalDataSource.searchEvents(any()) } returns flow { emit(dummyEventList) }
        coEvery { localEventsLocalDataSource.getEventCountById("1") } returns 0
        coEvery { localEventsLocalDataSource.getEventCountById("2") } returns 1
        repositoryImpl.searchEvents("").first().let {
            assertEquals(2, it.size)
            assertEquals(false, it[0].isFavourite)
            assertEquals(true, it[1].isFavourite)
        }
    }

    @Test
    fun testMarkEventAsFavourite() = runBlocking {
        coEvery { localEventsLocalDataSource.getEventCountById("1") } returns 0
        repositoryImpl.markAndUnMarkEventAsFavourite("1")
        coVerify(exactly = 1) { localEventsLocalDataSource.saveEventId("1") }
    }

    @Test
    fun testUnMarkEventAsFavourite() = runBlocking {
        coEvery { localEventsLocalDataSource.getEventCountById("1") } returns 1
        repositoryImpl.markAndUnMarkEventAsFavourite("1")
        coVerify(exactly = 1) { localEventsLocalDataSource.deleteEventId("1") }
    }
}