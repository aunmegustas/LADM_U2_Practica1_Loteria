package mx.edu.ittepic.ladm_u2_practica1_loteria

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity() {

    val audCartas: Array<Int> = arrayOf(R.raw.uno, R.raw.dos, R.raw.tres, R.raw.cuatro, R.raw.cinco, R.raw.seis, R.raw.siete, R.raw.ocho, R.raw.nueve, R.raw.diez,
                                        R.raw.once, R.raw.doce, R.raw.trece, R.raw.catorce, R.raw.quince, R.raw.dieciseis, R.raw.dieciocho, R.raw.diecinueve, R.raw.veinte,
                                        R.raw.veintiuno, R.raw.veintidos, R.raw.veintitres, R.raw.veinticuatro, R.raw.veinticinco, R.raw.veintiseis, R.raw.veintisiete, R.raw.veintiocho, R.raw.veintinueve, R.raw.treinta,
                                        R.raw.treintauno, R.raw.treintados, R.raw.treintatres, R.raw.treintacuatro, R.raw.treintacinco, R.raw.treintaseis, R.raw.treintasiete, R.raw.treintaocho, R.raw.treintanueve, R.raw.cuarenta,
                                        R.raw.cuarentauno, R.raw.cuarentados, R.raw.cuarentatres, R.raw.cuarentacuatro, R.raw.cuarentacinco, R.raw.cuarentaseis, R.raw.cuarentasiete, R.raw.cuarentaocho, R.raw.cuarentanueve, R.raw.cincuenta,
                                        R.raw.cincuentauno, R.raw.cincuentados, R.raw.cincuentatres, R.raw.cincuentacuatro)
    val imgCartas: Array<Int> = arrayOf(
        R.drawable.carta1, R.drawable.carta2,R.drawable.carta3,R.drawable.carta4,R.drawable.carta5,R.drawable.carta6,R.drawable.carta7,R.drawable.carta8,R.drawable.carta9,R.drawable.carta10,
        R.drawable.carta11, R.drawable.carta12,R.drawable.carta13,R.drawable.carta14,R.drawable.carta15,R.drawable.carta16,R.drawable.carta17,R.drawable.carta18,R.drawable.carta19,R.drawable.carta20,
        R.drawable.carta21, R.drawable.carta22,R.drawable.carta23,R.drawable.carta24,R.drawable.carta25,R.drawable.carta26,R.drawable.carta27,R.drawable.carta28,R.drawable.carta29,R.drawable.carta30,
        R.drawable.carta31, R.drawable.carta32,R.drawable.carta33,R.drawable.carta34,R.drawable.carta35,R.drawable.carta36,R.drawable.carta37,R.drawable.carta38,R.drawable.carta39,R.drawable.carta40,
        R.drawable.carta41, R.drawable.carta42,R.drawable.carta43,R.drawable.carta44,R.drawable.carta45,R.drawable.carta46,R.drawable.carta47,R.drawable.carta48,R.drawable.carta49,R.drawable.carta50,
        R.drawable.carta51, R.drawable.carta52,R.drawable.carta53,R.drawable.carta54)

    var index = arrayOf(0,1,2,3,4,5,6,7,8,9,10,
                        11,12,13,14,15,16,17,18,19,20,
                        21,22,23,24,25,26,27,28,29,30,
                        31,32,33,34,35,36,37,38,39,40,
                        41,42,43,44,45,46,47,48,49,50,
                        51,52,53)

    private val scope = CoroutineScope(Job() + Dispatchers.Main)
    private val randomIndex = scope.launch(EmptyCoroutineContext, CoroutineStart.LAZY){
        index.shuffle()
    }
    var thread = HiloPartida(this)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciar.setOnClickListener{
            thread.execute = true
            randomIndex.start()
            thread.start()
        }

        detener.setOnClickListener{
            if (!thread.execute) texto.text = "Aún no se inicia el juego."
            else if(thread.isPause()){
                thread.resumeThread()
                detener.text = "Detener Juego"
            }else{
                detener.text = "Reanudar Juego"
                thread.pauseThread()
            }
        }

        terminar.setOnClickListener {
            if(thread.cartasRestantes == 0){
                texto.text = "El juego ya terminó."
            }else if(thread.cartasRestantes == 53){
                texto.text = "El juego aún no empieza."
            }else if(thread.isPause()){
                texto.text = "Las cartas que sobraron fueron ${thread.cartasRestantes}"
                thread.endGame = true
                thread.resumeThread()
            }else{
                Toast.makeText(this, "No está pausado.", Toast.LENGTH_LONG).show()
            }
        }
    }

}