package com.example.codeinandroid.ui.eventDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.codeinandroid.domain.usecases.MarkAndUnMarkEventAsFavouriteUseCase
import com.example.codeinandroid.ui.base.BaseViewModel
import com.example.codeinandroid.ui.mappers.EventsUIMapper
import com.example.codeinandroid.ui.model.EventUIItemModel
import com.example.codeinandroid.utils.dispatcher.MyDispatchers


class EventDetailsViewModel(
    private val myDispatchers: MyDispatchers,
    private val uiDataMapper: EventsUIMapper,
    private val markAndUnMarkEventAsFavouriteUseCase: MarkAndUnMarkEventAsFavouriteUseCase
) : BaseViewModel(myDispatchers) {

    private val _eventDetails = MutableLiveData<EventUIItemModel>()
    val eventDetails: LiveData<EventUIItemModel> = _eventDetails

    fun initializeArgs(eventUIItemModel: EventUIItemModel) {
        _eventDetails.value = eventUIItemModel
    }
}