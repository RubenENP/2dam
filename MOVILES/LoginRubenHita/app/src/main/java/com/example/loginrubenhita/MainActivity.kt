package com.example.loginrubenhita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var textEditUsuario: EditText
    lateinit var textEditContrasenya: EditText
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textEditUsuario = this.findViewById(R.id.editTextUsuario)
        textEditContrasenya = this.findViewById(R.id.editTextContraseña)
        btnLogin = this.findViewById(R.id.button)

        btnLogin.setOnClickListener {
            if (textEditUsuario.text.toString() == "Manolo" && textEditContrasenya.text.toString() == "123"){
                Toast.makeText(this, "ADELANTE!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "ERROR!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}