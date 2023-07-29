package com.example.onlinedhobi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.Toast

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val background: Thread = object : Thread() {
            override fun run() {
                try {

                    sleep((2 * 1000).toLong())
                    val i = Intent(baseContext, MainActivity::class.java)
                    startActivity(i)
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(applicationContext,e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        background.start()
    }


    private fun anim(){
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.interpolator = DecelerateInterpolator() //add this
        fadeIn.duration = 1000

        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = AccelerateInterpolator() //and this
        fadeOut.startOffset = 1000
        fadeOut.duration = 1000

        val animation = AnimationSet(false) //change to false
        animation.addAnimation(fadeIn)
        animation.addAnimation(fadeOut)
        val img = findViewById<ImageView>(R.id.screen)
        img.animation = animation
    }
}