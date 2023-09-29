package com.example.myfirstpage.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

abstract class LiveEvent<Event>(protected val livaData: MutableLiveData<Event?>) {

    fun observe(owner: LifecycleOwner, observer: Observer<in Event>) {
        livaData.observe(owner) { event -> if (event != null) observer.onChanged(event) }
    }
}
