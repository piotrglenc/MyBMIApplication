package com.example.mybmiapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mybmiapplication.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels<MainViewModel>()
//    var bmi = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (viewModel.bmi != 0.0) {
            pokazEtykiety()
        }

/*        if (savedInstanceState != null && savedInstanceState.getDouble("OBLICZONE_BMI") != 0.0) {
            bmi = savedInstanceState.getDouble("OBLICZONE_BMI")
            pokazEtykiety()
        }*/

        binding.btnOblicz.setOnClickListener {
            obliczBMI()
        }
    }

    fun obliczBMI() {
        val podanaMasa = binding.masa.text.toString().toDoubleOrNull()
        val podanyWzrost = binding.wzrost.text.toString().toDoubleOrNull()

        if (podanaMasa == null || podanyWzrost == null)
            return

        viewModel.bmi = podanaMasa / (podanyWzrost * podanyWzrost)
        viewModel.bmi = (viewModel.bmi * 100.0).roundToInt() / 100.0
        pokazEtykiety()
    }

    fun pokazEtykiety() {
        binding.twojeBMI.text = "Twoje BMI:"
        binding.wynik.text = viewModel.bmi.toString()
    }

/*    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble("OBLICZONE_BMI",bmi)
    }*/
}