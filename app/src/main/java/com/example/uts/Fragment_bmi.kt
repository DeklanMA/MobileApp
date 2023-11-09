package com.example.uts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_bmi.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_bmi : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var editTextWeight: EditText? = null
    private var editTextHeight: EditText? = null
    private var buttonCalculateBMI: Button? = null
    private var textViewBMIResult: TextView? = null
    private var textViewIdealWeight: TextView? = null
    private var textViewFatPercentage: TextView? = null
    private var textViewBMIClassification: TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_bmi, container, false)
        editTextWeight = rootView.findViewById<EditText>(R.id.weightInput)
        editTextHeight = rootView.findViewById<EditText>(R.id.heightInput)

        textViewBMIResult = rootView.findViewById<TextView>(R.id.bmiResult)
        textViewIdealWeight = rootView.findViewById<TextView>(R.id.idealWeight)
        textViewFatPercentage = rootView.findViewById<TextView>(R.id.fatPercentage)
        textViewBMIClassification = rootView.findViewById<TextView>(R.id.bmiClassification)

        buttonCalculateBMI = rootView.findViewById<Button>(R.id.calculateButton)

        buttonCalculateBMI!!.setOnClickListener {
            calculateBMI()
        }

        return rootView
    }

    private fun calculateBMI() {
        try {
            val weight = editTextWeight!!.text.toString().toDouble()
            val heightInCm = editTextHeight!!.text.toString().toDouble()

            // Konversi tinggi dari cm ke meter
            val heightInMeter = heightInCm / 100

            // Hitung BMI
            val bmi = weight / (heightInMeter * heightInMeter)

            // Calculate Ideal Weight, Fat Percentage, and BMI Classification
            val idealWeight = 22.0 * (heightInMeter * heightInMeter)
            val fatPercentage = (1.2 * bmi) + (0.23 * 25) - 5.4
            val classification = when {
                bmi < 18.5 -> "Underweight"
                bmi >= 18.5 && bmi < 24.9 -> "Normal weight"
                bmi >= 25 && bmi < 29.9 -> "Overweight"
                else -> "Obese"
            }

            // Format hasil dan tampilkan pada TextViews
            textViewBMIResult!!.text = String.format("Your BMI is %.2f", bmi)
            textViewIdealWeight!!.text = String.format("Ideal Weight: %.2f kg", idealWeight)
            textViewFatPercentage!!.text = String.format("Fat Percentage: %.2f", fatPercentage)
            textViewBMIClassification!!.text = "BMI Classification: $classification"

        } catch (e: NumberFormatException) {
            textViewBMIResult!!.text = "Invalid input"
        }
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_graph.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_bmi().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}