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

        if (penitenciasGuardadas.isEmpty()) {
            crearArray()
            penitenciasGuardadas.add(penitencia)
            penitenciasExtras.add(penitencia)
        } else {
            penitenciasGuardadas.add(penitencia)
            penitenciasExtras.add(penitencia)
        }
    }

    fun jugar() {

        if (penitenciasGuardadas.isEmpty()){
            crearArray()
            if (penitenciasExtras.isNotEmpty())
                guardarPenitenciasExtras()
        }

        if (nombresGuardados.isEmpty()) {
            nombresGuardados.add("Jugador 1")
            nombreGanador()
            nombresGuardados.removeAt(0)
        } else {
            nombreGanador()
        }
        penaGanadora()
    }

    private fun nombreGanador() {
        val longArray = nombresGuardados.count() -1
        var numRandom = (0..longArray).random()
        //Se hace para no caer en un ciclo infinito
        if (longArray == 0) {
            nombreGanador.value = nombresGuardados[numRandom]

        } else {
            while (numRandom == numRandomGuardado) {
                numRandom = (0..longArray).random()
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

    private fun guardarPenitenciasExtras(){
        penitenciasGuardadas.addAll(penitenciasExtras)
    }


    private fun crearArray() {
        val penitencia = Penitencias()
        penitenciasGuardadas.addAll(
            penitencia.listaPenitencias
        )
    }

}