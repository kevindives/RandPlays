package com.magicworld.randplays.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magicworld.randplays.penitencias.Penitencias

class Repository {

    private var nombreGanador : MutableLiveData<String> = MutableLiveData()
    val onNombreGanadorDone : LiveData<String> =nombreGanador
    private var penaGanadora : MutableLiveData<String> = MutableLiveData()
    val onPenaGanadoraDone : LiveData<String> =penaGanadora

    private val nombresGuardados = arrayListOf<String>()
    private val penitenciasGuardadas = arrayListOf<String>()
    private val penitenciasExtras = arrayListOf<String>()
    private var numRandomGuardado = 0

    fun guardarNombre(nombreJugador: String) {
        nombresGuardados.add(nombreJugador)
    }

    fun guardarPenitencia(penitencia: String) {

        penitenciasExtras.add(penitencia)
        if (penitenciasGuardadas.isNotEmpty()) penitenciasGuardadas.add(penitencia)

    }

    fun jugar() {

        fillArray()
        nombreGanador()
        penaGanadora()

    }

    private fun nombreGanador() {

        if (nombresGuardados.isEmpty()){
            nombresGuardados.addAll(listOf("Jugador 1" , "jugador 2"))
            val longArray = nombresGuardados.count() - 1
            var numRandom = (0..longArray).random()
            while (numRandom == numRandomGuardado) {
                numRandom = (0..longArray).random()
            }
            nombreGanador.value = nombresGuardados[numRandom]
            nombresGuardados.removeAll(listOf("Jugador 1" , "jugador 2"))
            numRandomGuardado = numRandom
        }
        if (nombresGuardados.isNotEmpty()){
            val longArray = nombresGuardados.count() - 1
            var numRandom = (0..longArray).random()
            if (longArray != 0){
                while (numRandom == numRandomGuardado) {
                    numRandom = (0..longArray).random()
                }
            }

            nombreGanador.value = nombresGuardados[numRandom]
            numRandomGuardado = numRandom
        }

    }

    private fun penaGanadora(){
        val longArray = penitenciasGuardadas.count() -1
        val numRandom = (0..longArray).random()
        penaGanadora.value = penitenciasGuardadas[numRandom]
        penitenciasGuardadas.removeAt(numRandom)

    }

    private fun fillArray() {
        val penitencia = Penitencias()
        if (penitenciasGuardadas.isEmpty()){
            penitenciasGuardadas.addAll(
                penitencia.listaPenitencias
            )
            if (penitenciasExtras.isEmpty()){
                penitenciasGuardadas.addAll(
                    penitenciasExtras
                )
            }

        }

    }

}