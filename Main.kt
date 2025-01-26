fun main(){
    println("Bienvenido al juego del ahorcado con temática Pokémon")
    println("Cargando juego...")
    Thread.sleep(2000)
    val reproductor = ReproductorMidi("opening.mid")

    val palabras = listOf("pikachu", "dragonite", "charmander", "muk", "ditto", "meowth", "snorlax", "vaporeon")
    val palabraElegida = palabras.random()
    var asteriscos = "*".repeat(palabraElegida.length)

    var vidas = 7
    var fallos = 0

    while(vidas>0){
        println("El Pokémon a adivinar es: $asteriscos")
        val palabraFallo = if(fallos==1) "fallo" else "fallos"

        println("Llevas $fallos $palabraFallo (máximo número de fallos permitidos: 7). Introduce una letra:")
        val letra = readln().lowercase()

        if(palabraElegida.contains(letra)){
            println("¡Has acertado!")
            var nuevosAsteriscos = ""

            for(i in palabraElegida.indices){
                if(palabraElegida[i].toString() == letra){
                    nuevosAsteriscos += letra
                }
                else{
                    nuevosAsteriscos += asteriscos[i]
                }
            }

            asteriscos = nuevosAsteriscos
        }
        else{
            fallos++
            vidas--
            println("El Pokémon que buscas no contiene esa letra. ¡Has fallado!")
            DibujoAhorcado.dibujar(fallos)
        }

        if(asteriscos == palabraElegida){
            println("¡Felicidades, has adivinado al Pokémon $palabraElegida!")
            reproductor.cerrar()
            break
        }

        if(vidas == 0){
            println("Lo siento, has perdido. El Pokémon que buscabas era $palabraElegida")
            reproductor.cerrar()
        }
    }
}