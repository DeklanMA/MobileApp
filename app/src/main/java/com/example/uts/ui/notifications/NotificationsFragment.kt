package com.example.uts.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.uts.R
import com.example.uts.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var spinnerFromUnit: Spinner
    private lateinit var spinnerToUnit: Spinner
    private lateinit var editTextValue: EditText
    private lateinit var buttonConvert: Button
    private lateinit var textViewResult: TextView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_notifications, container, false)

        // Inisialisasi komponen UI
        spinnerFromUnit = rootView.findViewById(R.id.spinnerFromUnit)
        spinnerToUnit = rootView.findViewById(R.id.spinnerToUnit)
        editTextValue = rootView.findViewById(R.id.editTextValue)
        buttonConvert = rootView.findViewById(R.id.buttonConvert)
        textViewResult = rootView.findViewById(R.id.textViewResult)

        // Atur adapter untuk spinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.temperature_units,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFromUnit.adapter = adapter
            spinnerToUnit.adapter = adapter
        }

        buttonConvert.setOnClickListener {
            convertTemperature()
        }

        return rootView
    }
    private fun convertTemperature() {
        val value = editTextValue.text.toString().toDouble()
        val fromUnit = spinnerFromUnit.selectedItemPosition
        val toUnit = spinnerToUnit.selectedItemPosition

        val result = when (fromUnit) {
            0 -> convertCelsius(value, toUnit)
            1 -> convertFahrenheit(value, toUnit)
            2 -> convertKelvin(value, toUnit)
            else -> 0.0
        }

        textViewResult.text = "Result: $result"
        textViewResult.visibility = View.VISIBLE
    }

    private fun convertCelsius(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value
            1 -> value * 9/5 + 32
            2 -> value + 273.15
            else -> 0.0
        }
    }

    private fun convertFahrenheit(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> (value - 32) * 5/9
            1 -> value
            2 -> (value - 32) * 5/9 + 273.15
            else -> 0.0
        }
    }

    private fun convertKelvin(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value - 273.15
            1 -> (value - 273.15) * 9/5 + 32
            2 -> value
            else -> 0.0
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}