package mx.edu.ittepic.ladm_u2_practica1_loteria

import kotlinx.android.synthetic.main.activity_main.*

class HiloPartida(a: MainActivity) : Thread() {
    private var pausar = false
    private var correr = true
    var a = a

    var indice = 1
    val imgCartas = arrayOf(
        R.drawable.carta1, R.drawable.carta2,R.drawable.carta3,R.drawable.carta4,R.drawable.carta5,R.drawable.carta6,R.drawable.carta7,R.drawable.carta8,R.drawable.carta9,R.drawable.carta10,
        R.drawable.carta11, R.drawable.carta12,R.drawable.carta13,R.drawable.carta14,R.drawable.carta15,R.drawable.carta16,R.drawable.carta17,R.drawable.carta18,R.drawable.carta19,R.drawable.carta20,
        R.drawable.carta21, R.drawable.carta22,R.drawable.carta23,R.drawable.carta24,R.drawable.carta25,R.drawable.carta26,R.drawable.carta27,R.drawable.carta28,R.drawable.carta29,R.drawable.carta30,
        R.drawable.carta31, R.drawable.carta32,R.drawable.carta33,R.drawable.carta34,R.drawable.carta35,R.drawable.carta36,R.drawable.carta37,R.drawable.carta38,R.drawable.carta39,R.drawable.carta40,
        R.drawable.carta41, R.drawable.carta42,R.drawable.carta43,R.drawable.carta44,R.drawable.carta45,R.drawable.carta46,R.drawable.carta47,R.drawable.carta48,R.drawable.carta49,R.drawable.carta50,
        R.drawable.carta51, R.drawable.carta52,R.drawable.carta53,R.drawable.carta54)

    override fun run() {
        super.run()
        while(correr){
            if(!pausar){
                a.runOnUiThread{
                    a.mensaje2.text = "Juego #${indice}"
                }
            }
            Thread.sleep(10000L)
        }
        indice++
    }

    fun finishThread(){
        correr = false
    }
    fun pauseThread(){
        pausar = true
    }
    fun resumeThread(){
        pausar = false
    }
    fun isPause() : Boolean {
        return pausar
    }
}