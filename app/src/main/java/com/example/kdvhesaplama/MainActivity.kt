package com.example.kdvhesaplama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.kdvhesaplama.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        changeText()

        binding.calculateButtonId.setOnClickListener {
            val kdvEdit = binding.kdvEditId.text.toString()
            val tutarEdit = binding.tutarEditText.text

            if(binding.tutarEditText.text.toString().isEmpty())
                Toast.makeText(this,"Tutar giriniz",Toast.LENGTH_SHORT).show()
            else
                calculate()

    }}

    fun calculate() {

        val groupIslem = binding.islemGroupId.checkedRadioButtonId
        val groupKDV = binding.radioGroupId.checkedRadioButtonId
        var kdvOrani: Double = 0.0
        val digerKDV = binding.kdvEditId.text.toString()
        var tutarString = binding.tutarEditText.text.toString()
        val tutar = tutarString.toDouble()
        binding.res1Id.isVisible = true
        binding.res2Id.isVisible = true
        binding.res3Id.isVisible = true

        if (groupIslem == R.id.islem1Id) {
            when (groupKDV) {
                R.id.radioButton1Id -> kdvOrani = 1.0
                R.id.radioButton2Id -> kdvOrani = 8.0
                R.id.radioButton3Id -> kdvOrani = 18.0
                R.id.radioButton4Id -> if (digerKDV != "") kdvOrani = digerKDV.toDouble()
                else Toast.makeText(this, "KDV oranı girin", Toast.LENGTH_SHORT).show()
            }
            if (kdvOrani != 0.0) {

                binding.res3Id.text = "KDV Dahil Tutar: ${binding.tutarEditText.text.toString()} TL"
                binding.res2Id.text = "KDV(%${kdvOrani.toInt()}): ${tutar * kdvOrani / 100} TL"
                var KHT = tutar * (1 - kdvOrani / 100.0)
                binding.res1Id.text = "KDV Hariç Tutar: ${KHT} TL"
            }
        }

        if (groupIslem == R.id.islem2Id) {
            when (groupKDV) {
                R.id.radioButton1Id -> kdvOrani = 1.0
                R.id.radioButton2Id -> kdvOrani = 8.0
                R.id.radioButton3Id -> kdvOrani = 18.0
                R.id.radioButton4Id -> if (digerKDV != "") kdvOrani = digerKDV.toDouble()
                else Toast.makeText(this, "KDV oranı girin", Toast.LENGTH_SHORT).show()
            }
            if (kdvOrani != 0.0) {
                var KHT = tutar * (1 + kdvOrani / 100.0)
                binding.res3Id.text = "KDV Dahil Tutar: ${KHT} TL"
                binding.res2Id.text = "KDV(%${kdvOrani.toInt()}): ${tutar * kdvOrani / 100} TL"
                binding.res1Id.text = "KDV Hariç Tutar: ${binding.tutarEditText.text.toString()} TL"
            }
        }

        if (groupIslem == R.id.islem3Id) {
            when (groupKDV) {
                R.id.radioButton1Id -> kdvOrani = 1.0
                R.id.radioButton2Id -> kdvOrani = 8.0
                R.id.radioButton3Id -> kdvOrani = 18.0
                R.id.radioButton4Id -> if (digerKDV != "") kdvOrani = digerKDV.toDouble()
                else Toast.makeText(this, "KDV oranı girin", Toast.LENGTH_SHORT).show()
            }
            if (kdvOrani != 0.0) {
                binding.res3Id.text = "KDV Dahil Tutar: ${tutar + tutar / (kdvOrani / 100)} TL"
                binding.res2Id.text =
                    "KDV(%${kdvOrani.toInt()}): ${binding.tutarEditText.text.toString()} TL"
                var KHT = tutar * (1 - kdvOrani / 100.0)
                binding.res1Id.text = "KDV Hariç Tutar: ${tutar / (kdvOrani / 100)} TL"
            }
        }

    }

    fun changeText() {

        //checking the kdv oranı options which checked in the radio group
        //if checked the 4. option, showed an edit text
        //setOnCheckedChangeListener{this radio group listening, checked is true}
        //the listener listening the group with every members
        //radio group kısmına _ yazıp when içindeki radio group kısmına binding.radioGroupId yazabiliriz

        /**
         * abstract fun onCheckedChanged(
        buttonView: CompoundButton!,
        isChecked: Boolean
        ): Unit  */

        binding.kdvEditId.isVisible = false

        binding.islemGroupId.setOnCheckedChangeListener { islemGroup, _ ->

            when (islemGroup.checkedRadioButtonId) {
                R.id.islem1Id -> binding.tutartext.text = "KDV Dahil Tutar:"
                R.id.islem2Id -> binding.tutartext.text = "KDV Hariç Tutar:"
                else -> binding.tutartext.text = "KDV Tutar:"
            }

        }

        binding.radioGroupId.setOnCheckedChangeListener { radioGroup, _ ->
            when (radioGroup.checkedRadioButtonId) {
                R.id.radioButton4Id -> binding.kdvEditId.isVisible = true
                else -> binding.kdvEditId.isVisible = false
            }
        }


    }
}