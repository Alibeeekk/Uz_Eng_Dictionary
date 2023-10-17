package dev.alibek.uz_rulugat.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import dev.alibek.uz_rulugat.R
import dev.alibek.uz_rulugat.ui.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            initViews()
        },2200)
    }

    private fun initViews() {
        val ivIcon = findViewById<ImageView>(R.id.iv_icon)
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}