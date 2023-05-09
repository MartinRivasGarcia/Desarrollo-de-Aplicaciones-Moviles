package com.utn.segundaconsigna.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.utn.segundaconsigna.R

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 3000 // 3 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var imgBook: ImageView = findViewById(R.id.imageSplash)
        var url = "https://gestionculturaldefensa.ar/wp-content/uploads/2021/06/FOTO-BCE2-726x800.jpeg"

        Glide
            .with(this)
            .load(url)
            .into(imgBook)


        Handler().postDelayed(

            {
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            , SPLASH_TIME_OUT)
    }
}