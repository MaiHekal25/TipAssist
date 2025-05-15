package com.training.tiptimeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.training.tiptimeapp.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            val costOfService = binding.etService.text.toString().toDouble()
            val checkedRB = binding.group.checkedRadioButtonId
            val tipPercentage = when (checkedRB) {
                R.id.rb_amazing -> 0.2
                R.id.rb_good -> 0.18
                else -> 0.15
            }
            var tip = tipPercentage * costOfService
            val roundUp = binding.switchRoundTip.isChecked
            if (roundUp) {
                tip = ceil(tip)
            }
            binding.tvTipAmount.text = "$${tip.toString()}"

        }
    }
}