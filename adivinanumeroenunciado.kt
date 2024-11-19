import java.io.File
import kotlin.random.Random

fun generarLista (): MutableList<Int> {
    val lista = mutableListOf<Int>()
    val listaNumerosPosibles = mutableListOf(0,1,2,3,4,5,6,7,8,9)
    for (i in 0..3) {
        val indiceSeleccionado = Random.nextInt(listaNumerosPosibles.size)
        lista.add(listaNumerosPosibles[indiceSeleccionado])
        listaNumerosPosibles.removeAt(indiceSeleccionado)
    }
    return lista
}

fun intList(entrada : String) : List<Int> {
    val entradaIntList = mutableListOf<Int>()
    for (element in entrada) {
        entradaIntList.add(element.digitToInt())
    }
    return entradaIntList
}

fun comprobarCoincidencias (lista : List<Int>, listaEntrada :  List<Int>) : Int {
    var coincidencias = 0
    for (numeroEntrada in listaEntrada) {
        for (numero in lista) {
            if (numero == numeroEntrada) {coincidencias++}
        }
    }
    return coincidencias
}

fun comprobarAciertos (lista : List<Int>, listaEntrada :  List<Int>) : Int {
    var aciertos = 0
    for (indice in 0..3) {
        if (lista[indice] == listaEntrada[indice]) {aciertos++}
    }
    return aciertos
}

fun sobreescribirFichero (cadena : StringBuilder) {
    File("resultadoUltima.txt").writeText(cadena.toString())
}

fun mostrarContenido () : String {
    return File("resultadoUltima.txt").readText()
}

fun main() {
    while (true) {
        println("1. Jugar\n2. Ver traza de último intento\n3. Salir")
        val select : Int = readln().toInt()
        if (select == 3) {break}
        if (select < 1 || select > 3) {
            println("\nIngrese una opción correcta\n")
            continue
        }
        if (select == 1) {
            println("Comienza el juego:\nTienes 5 intentos para adivinar el número\n")
            val lista = generarLista()
            val salidaFichero : StringBuilder = StringBuilder("numero secreto: ${lista[0]}${lista[1]}${lista[2]}${lista[3]}\n")
            print(salidaFichero.toString()) // testing
            var intentos = 0
            for (a in 0..1) {
                intentos++
                val entrada = readln()
                val listaEntrada = intList(entrada)
                val coincidencias = comprobarCoincidencias(lista, listaEntrada)
                val aciertos = comprobarAciertos(lista, listaEntrada)
                salidaFichero.append("Intento ${intentos}: ${entrada}, Aciertos: ${aciertos}, Coincidencias: ${coincidencias}\n")
                println(salidaFichero)
            }
            sobreescribirFichero(salidaFichero)
        }
        if (select == 2) {
            println(mostrarContenido())
        }
    }
}