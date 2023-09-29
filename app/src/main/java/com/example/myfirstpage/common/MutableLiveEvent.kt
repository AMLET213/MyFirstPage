package com.example.myfirstpage.common

import androidx.lifecycle.MutableLiveData


class MutableLiveEvent<Event : Any> : LiveEvent<Event>(MutableLiveData<Event?>()) {

    fun postEvent(event: Event) {
        livaData.value = event
        livaData.value = null
    }
}
