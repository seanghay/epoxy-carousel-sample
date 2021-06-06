package com.seanghay.explorer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airbnb.epoxy.EpoxyController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CopyOnWriteArrayList


class MainViewModel : ViewModel() {

    private val _loading = MutableLiveData(false)
    private val _items = CopyOnWriteArrayList<DataItem>()

    val items: List<DataItem> = _items
    val loading: LiveData<Boolean> = _loading

    fun fetch(controller: EpoxyController) {
        if (items.isNotEmpty()) return
        viewModelScope.launch(Dispatchers.IO) {
            _loading.postValue(true)
            val posts = HttpService().posts()
            _items.clear()
            _items.addAll(posts)
            _loading.postValue(false)
            withContext(Dispatchers.Main.immediate) {
                controller.requestModelBuild()
            }
        }
    }
}