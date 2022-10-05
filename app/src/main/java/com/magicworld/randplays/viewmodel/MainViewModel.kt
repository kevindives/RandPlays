package com.magicworld.randplays.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.randplays.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository =  Repository()
    val onRandomPenaltyDone = repository.onWinningPenaltyDone
    val onRandomNameDone = repository.onWinningNameDone

    fun saveName(nombreJugador: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.savedName(nombreJugador)
        }

    }

    fun savePenalty(penitencia: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.savedPenalty(penitencia)
        }

    }

    fun play() {
        repository.play()
    }

}