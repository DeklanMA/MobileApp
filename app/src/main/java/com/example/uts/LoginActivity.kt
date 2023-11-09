package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uts.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        val createNowTextView: TextView = findViewById(R.id.group_9)
        val createNowTextView1: TextView = findViewById(R.id.forgot_pass)
        val clickAnimation = AnimationUtils.loadAnimation(this, R.anim.click_animation)

// Menambahkan onClickListener ke TextView
        createNowTextView1.setOnClickListener {
            it.startAnimation(clickAnimation)
            val  intent = Intent(this,ForgotPassword::class.java)
            startActivity(intent)
        }



        createNowTextView.setOnClickListener {
            // Memulai animasi saat TextView diklik
            it.startAnimation(clickAnimation)


            // Kode yang akan dijalankan saat TextView diklik
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.loginButton.setOnClickListener {

            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            //Validasi email
            if (email.isEmpty()) {
                binding.email.error = "Email Harus Diisi"
                binding.email.requestFocus()
                return@setOnClickListener
            }
            //Validasi email tidak sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.email.error = "Email Tidak Valid"
                binding.email.requestFocus()
                return@setOnClickListener
            }

            //Validasi password
            if (password.isEmpty()) {
                binding.password.error = "Password Harus Diisi"
                binding.password.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 6) {
                binding.password.error = "Password Minimal 6"
                binding.password.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(email, password)





        }
    }

    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Selamat Datang $email", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }


    }

}