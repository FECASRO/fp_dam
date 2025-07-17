package com.example.midletmensajero

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Duración de la pantalla de presentación en milisegundos
        val splashDuration = 3000L // 3 segundos

        Handler(Looper.getMainLooper()).postDelayed({
            // Redirige a la MainActivity después de la duración especificada
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finaliza la SplashActivity para que no pueda volverse atrás
        }, splashDuration)
    }
}