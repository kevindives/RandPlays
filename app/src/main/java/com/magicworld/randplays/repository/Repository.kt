package com.magicworld.randplays.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magicworld.randplays.penitencias.Penitencias

class Repository {

    private var winningName : MutableLiveData<String> = MutableLiveData()
    val onWinningNameDone : LiveData<String> =winningName
    private var winningPenalty : MutableLiveData<String> = MutableLiveData()
    val onWinningPenaltyDone : LiveData<String> =winningPenalty

    private val savedName = arrayListOf<String>()
    private val savedPenalty = arrayListOf<String>()
    private val penaltyExtras = arrayListOf<String>()
    private var numRandomSaved = 0

    fun savedName(nombreJugador: String) {
        savedName.add(nombreJugador)
    }

    fun savedPenalty(penitencia: String) {

        penaltyExtras.add(penitencia)
        if (savedPenalty.isNotEmpty()) savedPenalty.add(penitencia)

    }

    fun play() {

        fillArray()
        winningName()
        winningPenalty()

    }

    private fun winningName() {

        if (savedName.isEmpty()){
            savedName.addAll(listOf("Jugador 1" , "jugador 2"))
            val longArray = savedName.count() - 1
            var numRandom = (0..longArray).random()
            while (numRandom == numRandomSaved) {
                numRandom = (0..longArray).random()
            }
            winningName.value = savedName[numRandom]
            savedName.removeAll(listOf("Jugador 1" , "jugador 2"))
            numRandomSaved = numRandom
        }
        if (savedName.isNotEmpty()){
            val longArray = savedName.count() - 1
            var numRandom = (0..longArray).random()
            if (longArray != 0){
                while (numRandom == numRandomSaved) {
                    numRandom = (0..longArray).random()
                }
            }

            winningName.value = savedName[numRandom]
            numRandomSaved = numRandom
        }

    }

    private fun winningPenalty(){
        val longArray = savedPenalty.count() -1
        val numRandom = (0..longArray).random()
        winningPenalty.value = savedPenalty[numRandom]
        savedPenalty.removeAt(numRandom)

    }

    private fun fillArray() {
        val penitencia = Penitencias()
        if (savedPenalty.isEmpty()){
            savedPenalty.addAll(
                penitencia.listaPenitencias
            )
            if (penaltyExtras.isNotEmpty()){
                savedPenalty.addAll(
                    penaltyExtras
                )
            }
        }
    }

}