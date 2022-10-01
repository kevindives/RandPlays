package com.magicworld.randplays.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.randplays.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository =  Repository()
    val onPenaAleatoriaDone = repository.onPenaGanadoraDone
    val onNombreAleatorioDone = repository.onNombreGanadorDone

    fun guardarNombre(nombreJugador: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.guardarNombre(nombreJugador)
        }

    }

    fun guardarPenitencia(penitencia: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.guardarPenitencia(penitencia)
        }

    }

    fun jugar() {
        repository.jugar()
    }

}