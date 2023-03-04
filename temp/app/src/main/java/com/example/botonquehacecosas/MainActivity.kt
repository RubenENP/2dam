package com.example.botonquehacecosas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var tvTexto: TextView
    lateinit var btnCambio: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvTexto = this.findViewById(R.id.textView2)

        btnCambio = this.findViewById(R.id.button)

        val s = "Me gusta Java"
        tvTexto.setText(s)


        btnCambio.setOnClickListener{
            if (tvTexto.text == s){
                tvTexto.text = "Me gusta python"
            }else {
                tvTexto.text = s
            }
        }
    }
}