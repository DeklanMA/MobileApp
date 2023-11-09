package com.example.uts.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.uts.Fragment_bmi
import com.example.uts.LoginActivity
import com.example.uts.R
import com.example.uts.ui.dashboard.CalculatorFragmen
import com.example.uts.ui.main.KonversiUangFragment
import com.example.uts.ui.notifications.NotificationsFragment
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {
    // Deklarasi variabel lainnya

    private var calculateImageView: ImageView? = null
    private  var BMIKalkulatorImageView: ImageView? = null
    private  var SuhuImageView: ImageView? = null
    private  var ConvertImageView: ImageView? = null
    lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        auth = FirebaseAuth.getInstance()

        val logoutImageView: ImageView = view.findViewById(R.id.logout)

        logoutImageView.setOnClickListener {
            // Lakukan logout dari Firebase
            auth.signOut()

            // Redirect ke halaman login (atau halaman awal setelah logout)
            // Ganti LoginActivity::class.java dengan kelas aktivitas yang sesuai
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        // Inisialisasi view, binding, dan lainnya

        // Menambahkan OnClickListener ke calculateImageView
        calculateImageView = view.findViewById(R.id.calculate)
        calculateImageView?.setOnClickListener {
            val fragmentKalkulator = CalculatorFragmen()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragmentKalkulator)
            transaction.addToBackStack(null) // (opsional) tambahkan ke back stack
            transaction.commit()
        }

        // Menambahkan OnClickListener ke calculateImageView
        BMIKalkulatorImageView = view.findViewById(R.id.bmi)
        BMIKalkulatorImageView?.setOnClickListener {
            val fragmentBMI= Fragment_bmi()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragmentBMI)
            transaction.addToBackStack(null) // (opsional) tambahkan ke back stack
            transaction.commit()
        }

        // Menambahkan OnClickListener ke calculateImageView
        SuhuImageView = view.findViewById(R.id.suhu)
        SuhuImageView?.setOnClickListener {
            val fragmentSuhu= NotificationsFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragmentSuhu)
            transaction.addToBackStack(null) // (opsional) tambahkan ke back stack
            transaction.commit()
        }

        // Menambahkan OnClickListener ke calculateImageView
        ConvertImageView = view.findViewById(R.id.konversi)
        ConvertImageView?.setOnClickListener {
            val fragmentConvert= KonversiUangFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragmentConvert)
            transaction.addToBackStack(null) // (opsional) tambahkan ke back stack
            transaction.commit()
        }

        return view
    }
}