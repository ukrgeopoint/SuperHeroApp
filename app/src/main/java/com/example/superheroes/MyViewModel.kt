package com.example.superheroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroes.data.HeroItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private var repo: Repository): ViewModel() {
    private val _uiState = MutableLiveData<UIState>(UIState.Empty)
    val uiState: LiveData<UIState> = _uiState

    fun getData() {
        _uiState.value = UIState.Processing
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val hero = repo.getCurrencyHero()
                    _uiState.postValue(UIState.Result(hero))
                } catch (e: Exception) {
                    _uiState.postValue(e.localizedMessage?.let { UIState.Error(it) })
                }

            }
        }
    }

    sealed class UIState {
        data object Empty : UIState()
        data object Processing : UIState()
        class Result(val hero: ArrayList<HeroItem>) : UIState()
        class Error(val description: String) : UIState()
    }
}