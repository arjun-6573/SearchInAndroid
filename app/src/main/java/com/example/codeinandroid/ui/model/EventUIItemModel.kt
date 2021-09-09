package com.example.codeinandroid.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventUIItemModel(
    val id: String,
    val image: String,
    val title: String,
    val dateTime: String,
    val address: String,
) : Parcelable
