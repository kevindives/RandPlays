package com.magicworld.randplays.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var nombreAleatorio: MutableLiveData<String> = MutableLiveData()
    val onNombreAleatorioDone: LiveData<String> = nombreAleatorio

    private var penaAleatoria: MutableLiveData<String> = MutableLiveData()
    val onPenaAleatoriaDone: LiveData<String> = penaAleatoria

    private val nombresGuardados = arrayListOf<String>()
    private val penitenciasGuardadas = arrayListOf<String>()
    private var numRandomGuardado = 0
    private var penaRandomGuardada = 0

    fun guardarNombre(nombreJugador: String) {
        nombresGuardados.add(nombreJugador)
    }

    fun guardarPenitencia(penitencia: String) {
        if (penitenciasGuardadas.isEmpty()) {
            crearArray()
            penitenciasGuardadas.add(penitencia)
        } else {
            penitenciasGuardadas.add(penitencia)
        }

    }

    fun jugar() {

        if (penitenciasGuardadas.isEmpty())
            crearArray()

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
        val longArray = nombresGuardados.count() - 1
        var numRandom = (0..longArray).random()
        if (longArray == 0) {
            val aleatorio = nombresGuardados[numRandom]
            nombreAleatorio.value = aleatorio
        } else {
            while (numRandom == numRandomGuardado) {
                numRandom = (0..longArray).random()
            }
            val aleatorio = nombresGuardados[numRandom]
            nombreAleatorio.value = aleatorio
            numRandomGuardado = numRandom
        }
    }

    private fun penaGanadora() {
        val longArray = penitenciasGuardadas.count() - 1
        var numRandom = (0..longArray).random()
        while (numRandom == penaRandomGuardada)
            numRandom = (0..longArray).random()
        val aleatorio = penitenciasGuardadas[numRandom]
        penaAleatoria.value = aleatorio
        penaRandomGuardada = numRandom
    }

    private fun crearArray() {
        penitenciasGuardadas.addAll(
            listOf(
                "LA VERDAD Y SOLO LA VERDAD \n \n El jugador debe responder una pregunta diciendo solo la verdad ",
                "A BAILAR!! \n \n Muestra tu paso de baile prohibido favorito, debes hacerlo por 30 segundos",
                "¿ENAMORADOS? \n \n Debes declarartele a la persona que esta al frente tuyo",
                "YO SOY EL CANTANTE \n \n Debes interpretar una canción famosa que todos conozcan",
                "BESAME!! \n \n Darse un beso apasionado en su propia mano o en el brazo, puede decirse frases amorosas. El beso debe durar unos 20 segundos",
                "DERROCHE DE CARIÑO \n \n Dígale una frasea amorosa o cariñosa a cada una de las personas en el juego. Puede acompañar esto con un abrazo o caricia especial",
                "EL MASAJISTA \n \n Debe hacerle un masaje por 15 segundos a la persona de mayor edad en la fiesta. Puede ser en el cuello, la espalda o donde la persona le indique",
                "GRITALO MAS FUERTE!! \n \nLos demás jugadores escriben frases incómodas, vulgares, vergonzosas. O se las pueden decir al oído.  El jugador debe gritar fuerte esas frases, una por jugador.",
                "COMO ESTATUA \n \n Debes quedarte quieto por 2 minutos.EL juego continúa!!. Si te mueves, empieza otra vez a contar el tiempo.",
                "EL DESCACHADO \n \n Debes contar chistes malos, hacer muecas, o cosas graciosas hasta que al menos se rían tres personas.",
                "HISTORIAS DE TERROR \n \n Inventarse o contar una historia o cuento de terror que se sepa. Debe apagar la luz y ambientar la narración.",
                "CANCIÓN INFANTIL \n \n La persona elegida para este reto debe cantar una canción infantil o de cuna, imitando la voz de un niño o niña",
                "BIlINGÜE \n \n La persona debera decir 10 palabras en ingles",
                "PIROPOS!! \n \n Inventarse un piropo bien rebuscado para cada una de las damas u hombres de la fiesta y decírselos",
                "RISA CONTAGIOSA \n \n Empiece a reírse hasta contagiar de la risa al menos a otra persona.",
                "NO LO NIEGUES!! \n \n Contestar afirmativamente todas las preguntas que le hagan los otros jugadores. La idea es que los otros hagan preguntas comprometedoras y difíciles.",
                "JUNTOS POR SIEMPRE \n \n Debes tomar de la mano a la persona que tienes al lado durante dos minutos, si la sueltas, empieza otra vez a contar el tiempo",
                "BOSTEZOS CONTAGIOSOS \n \n Bostezar y estirarse por un minuto o hasta que otra persona se contagie de los bostezos."
                )
        )
    }

}