package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)


        val createNowTextView1: TextView = findViewById(R.id.btn_1)
        val clickAnimation = AnimationUtils.loadAnimation(this, R.anim.click_animation)

// Menambahkan onClickListener ke TextView
        createNowTextView1.setOnClickListener {
            it.startAnimation(clickAnimation)
            val  intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)

        }
        val createNowTextView: TextView = findViewById(R.id.btn_2)


// Menambahkan onClickListener ke TextView
        createNowTextView.setOnClickListener {
            it.startAnimation(clickAnimation)
            val  intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }


    }

}