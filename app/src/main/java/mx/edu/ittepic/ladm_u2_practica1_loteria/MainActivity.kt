package mx.edu.ittepic.ladm_u2_practica1_loteria

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val a: MainActivity = this
    private var baraja: ArrayList<Int> = ArrayList<Int>()

    private val audCartas: Array<Int> = arrayOf(R.drawable.carta1)
    private val imgCartas: Array<Int> = arrayOf(
        R.drawable.carta1, R.drawable.carta2,R.drawable.carta3,R.drawable.carta4,R.drawable.carta5,R.drawable.carta6,R.drawable.carta7,R.drawable.carta8,R.drawable.carta9,R.drawable.carta10,
        R.drawable.carta11, R.drawable.carta12,R.drawable.carta13,R.drawable.carta14,R.drawable.carta15,R.drawable.carta16,R.drawable.carta17,R.drawable.carta18,R.drawable.carta19,R.drawable.carta20,
        R.drawable.carta21, R.drawable.carta22,R.drawable.carta23,R.drawable.carta24,R.drawable.carta25,R.drawable.carta26,R.drawable.carta27,R.drawable.carta28,R.drawable.carta29,R.drawable.carta30,
        R.drawable.carta31, R.drawable.carta32,R.drawable.carta33,R.drawable.carta34,R.drawable.carta35,R.drawable.carta36,R.drawable.carta37,R.drawable.carta38,R.drawable.carta39,R.drawable.carta40,
        R.drawable.carta41, R.drawable.carta42,R.drawable.carta43,R.drawable.carta44,R.drawable.carta45,R.drawable.carta46,R.drawable.carta47,R.drawable.carta48,R.drawable.carta49,R.drawable.carta50,
        R.drawable.carta51, R.drawable.carta52,R.drawable.carta53,R.drawable.carta54)

    var mediaPlayer : MediaPlayer?=null
    var detener = false
    var scope = CoroutineScope(Job()+Dispatchers.Main)
    var corriendo = true
    var indice = 1
    var contador = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        a.title = "Loteria!"

        var thread = HiloPartida(a)
        rep.setOnClickListener {
            loteria.text = "Loteria!"
            corriendo = true
            contador = 1
            mensaje2.text = "Juego #${indice}"
            val jobCoroutine = scope.launch(EmptyCoroutineContext, CoroutineStart.LAZY) {
                while(corriendo){
                    (0 .. audCartas.size).forEach(){
                        if(detener){
                            delay(6000L)
                        }else{
                            try{
                                mediaPlayer = MediaPlayer.create(a, audCartas[baraja[it]])
                            }catch (e : Exception){}
                            try{
                                runOnUiThread{
                                    carta.setImageResource(imgCartas[baraja[it]])
                                    mediaPlayer?.start()
                                    mensaje.text = "Se juega ${contador++} cartas."
                                }
                                delay(3000L)
                            }catch (e : Exception){}
                            mediaPlayer?.release()
                        }
                    }
                    corriendo = false
                    indice++
                    delay(1000L)
                }
            }
            if(corriendo){
                barajear()
                jobCoroutine.start()
                rep.isEnabled = false
                loteria.isEnabled = true
                return@setOnClickListener
            }
        }

        loteria.setOnClickListener {
            if(!detener){
                detener = true
                loteria.text = "Restantes"
                mensaje.text = "Loteria!"
                setTitle("Loteria han ganado!")
                return@setOnClickListener
            }else{
                detener = false
                loteria.text = "Espera a que termine."
                rep.isEnabled = true
                return@setOnClickListener
            }

        }
    }

    private fun barajear(){
        baraja = ArrayList<Int>()
        for(m in audCartas) baraja.add(azar(baraja))
    }

    private fun azar(baraja : ArrayList<Int>) : Int {
        var random = Random.nextInt(54)
        while(!baraja.contains(random)) return random
        return azar(baraja)
    }
}