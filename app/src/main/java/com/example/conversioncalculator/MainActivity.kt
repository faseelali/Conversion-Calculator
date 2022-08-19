package com.example.conversioncalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.conversioncalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.CalculateButton.setOnClickListener { converter() }
    }

    private fun converter() {
        var result = 0.0
        val inputValue = binding.EnterInput.text.toString().toDoubleOrNull()
        if (inputValue == null){
            Toast.makeText(this, "Input field cannot be left blank", Toast.LENGTH_SHORT).show()
            binding.OutputResult.text = ""
            return
        }

            when (binding.inputOptions.checkedRadioButtonId) {

            R.id.Meter_button -> {
                result = when (binding.OutputOptions.checkedRadioButtonId) {
                    R.id.Output_Meter_button -> inputValue
                    R.id.Output_Feet_button -> inputValue * 3.281
                    else -> inputValue * 39.37
                }
            }

            R.id.Feet_button -> {
                result = when (binding.OutputOptions.checkedRadioButtonId) {
                    R.id.Output_Meter_button -> inputValue / 3.281
                    R.id.Output_Feet_button -> inputValue
                    else -> inputValue * 12
                }
            }

            R.id.Inch_button -> {
                result = when (binding.OutputOptions.checkedRadioButtonId) {
                    R.id.Output_Meter_button -> inputValue / 39.37
                    R.id.Output_Feet_button -> inputValue / 12
                    else -> inputValue
                }
            }
        }

        if(binding.roundUpButton.isChecked){
            result = kotlin.math.ceil(result)
        }
        binding.OutputResult.text = getString(R.string.converted_value, result.toString())
    }
}