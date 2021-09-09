package com.example.codeinandroid.ui.eventDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.codeinandroid.domain.usecases.MarkAndUnMarkEventAsFavouriteUseCase
import com.example.codeinandroid.ui.base.BaseViewModel
import com.example.codeinandroid.ui.model.EventUIItemModel
import com.example.codeinandroid.utils.dispatcher.MyDispatchers
import kotlinx.coroutines.launch


class EventDetailsViewModel(
    myDispatchers: MyDispatchers,
    private val markAndUnMarkEventAsFavouriteUseCase: MarkAndUnMarkEventAsFavouriteUseCase
) : BaseViewModel(myDispatchers) {

    private val _eventDetails = MutableLiveData<EventUIItemModel>()
    val eventDetails: LiveData<EventUIItemModel> = _eventDetails

    private val _isFavourite = MutableLiveData<Boolean>()
    val isFavourite: LiveData<Boolean> = _isFavourite

    fun initializeArgs(eventUIItemModel: EventUIItemModel) {
        _eventDetails.value = eventUIItemModel
        _isFavourite.value = eventUIItemModel.isFavourite
    }

    fun onFavouriteClick() {
        uiScope.launch {
            markAndUnMarkEventAsFavouriteUseCase.invoke(_eventDetails.value?.id.orEmpty())
            _isFavourite.value = _isFavourite.value?.not()
        }
    }
}