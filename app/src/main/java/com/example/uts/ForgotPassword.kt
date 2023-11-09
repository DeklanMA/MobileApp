package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uts.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {

    private lateinit var  binding: ActivityForgotPasswordBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val createNowTextView: TextView = findViewById(R.id.backlogin)
        val clickAnimation = AnimationUtils.loadAnimation(this, R.anim.click_animation)

// Menambahkan onClickListener ke TextView
        createNowTextView.setOnClickListener { it ->
            // Memulai animasi saat TextView diklik
            it.startAnimation(clickAnimation)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }

        binding.next2.setOnClickListener {
            val email: String = binding.etEmail3.text.toString().trim()

            if (email.isEmpty()){
                binding.etEmail3.error = "Email dont empty"
                binding.etEmail3.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.etEmail3.error = "Email tidak valid"
                binding.etEmail3.requestFocus()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "Check your email", Toast.LENGTH_SHORT).show()
                    Intent(this, LoginActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.backlogin.setOnClickListener {
            val intent = Intent(this , LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
    
