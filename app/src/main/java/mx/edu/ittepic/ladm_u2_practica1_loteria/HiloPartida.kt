package mx.edu.ittepic.ladm_u2_practica1_loteria

import android.media.MediaPlayer
import kotlinx.android.synthetic.main.activity_main.*

class HiloPartida(val a: MainActivity) : Thread() {
    var execute = false
    var pause = false
    var cartasRestantes = 0
    var endGame = false


    override fun run() {
        super.run()
        var i = 0
        while (execute){
            if(!pause){
                a.runOnUiThread{
                    if (i < 54){
                        cartasRestantes = 53 - i
                        a.imagen.setImageResource(a.imgCartas[a.index[i]])
                        var audio = MediaPlayer.create(a, a.audCartas[a.index[i]])
                        audio.start()
                        if (!endGame){
                            a.texto.text = "Cartas restantes: $cartasRestantes"
                        }
                        i++
                    }
                    if(i == 54){
                        sleep(5000)
                        a.texto.text = "Fin del juego."
                        pauseThread()
                    }
                }
                sleep(5000)
            }
        }
    }

    fun finishThread(){
        execute = false
    }

    fun pauseThread(){
        pause = true
    }

    fun resumeThread(){
        pause = false
    }

    fun isPause() : Boolean {
        return pause
    }
}