package com.training.tiptimeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.training.tiptimeapp.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private var total = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        total = savedInstanceState?.getDouble("total") ?: 0.0
        binding.tvTipAmount.text = total.toString()

        binding.btnCalculate.setOnClickListener {
            val costOfService = binding.etService.text.toString().toDouble()
            val checkedRB = binding.group.checkedRadioButtonId
            val tipPercentage = when (checkedRB) {
                R.id.rb_amazing -> 0.2
                R.id.rb_good -> 0.18
                else -> 0.15
            }
            total = tipPercentage * costOfService
            val roundUp = binding.switchRoundTip.isChecked
            if (roundUp) {
                total = ceil(total)
            }
            binding.tvTipAmount.text = "$${total.toString()}"

            Snackbar
                .make(binding.root, "Reset", BaseTransientBottomBar.LENGTH_INDEFINITE)
                .setAction("Proceed") {
                    binding.etService.text?.clear()
                    binding.group.check(R.id.rb_amazing)
                    binding.switchRoundTip.isChecked = true
                    binding.tvTipAmount.text = getString(R.string.tip_amount)
                }
                .setBackgroundTint(getColor(android.R.color.holo_purple))
                .setTextColor(getColor(android.R.color.white))
                .setActionTextColor(getColor(android.R.color.white))
                .show()
        }
    }

    //between onPause & onStop
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //means before activity destroyed , save the state
        outState.putDouble("total", total)
    }
}