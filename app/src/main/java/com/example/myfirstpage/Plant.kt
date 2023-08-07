package com.example.myfirstpage

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Plant(val id: Int, val title: String) : Parcelable
