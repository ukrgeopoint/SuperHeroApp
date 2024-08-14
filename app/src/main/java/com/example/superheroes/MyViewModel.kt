package com.example.superheroes

import android.provider.Contacts.Intents.UI
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroes.data.HeroItem
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val _uiState = MutableLiveData<UIState>(UIState.Empty)
    val uiState: LiveData<UIState> = _uiState

    fun getData() {
        _uiState.value = UIState.Processing
        viewModelScope.launch {
            val hero = ApiClient.client.create(ApiInterface::class.java)
            .getHeroes()
                _uiState.postValue(UIState.Result(hero))
        }
    }

    sealed class UIState {
        data object Empty: UIState()
        data object Processing: UIState()
        class Result(val hero: ArrayList<HeroItem>): UIState()
    }
}