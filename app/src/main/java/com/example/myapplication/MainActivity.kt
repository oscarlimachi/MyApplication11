package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button

import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    //weight
    lateinit var removeWeightButton: Button
    lateinit var addWeightButton: Button
    lateinit var weightTextView: TextView
    //height
    lateinit var heightSlider: Slider
    lateinit var heightTextView: TextView
    //button
    lateinit var calculateButton: Button
    //resultado
    lateinit var resultTextView: TextView
    lateinit var descriptionTextView: TextView
    //Datos predeterminados
    var weight: Float = 74.0f
    var height: Float = 170.0f

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //weight
        removeWeightButton=findViewById(R.id.removeWeightButton)
        addWeightButton=findViewById(R.id.addWeightButton)
        weightTextView=findViewById(R.id.weightTextView)
        //height
        heightTextView=findViewById(R.id.heightTextView)
        heightSlider=findViewById(R.id.heightSlider)
        //button
        calculateButton=findViewById(R.id.calculateButton)
        //resultado
        resultTextView=findViewById(R.id.resultTextView)
        descriptionTextView=findViewById(R.id.descriptionTextView)

// -------------Empieza la programación


        removeWeightButton.setOnClickListener {
            weight--
            weightTextView.text="${weight.toInt()} kg"
            }
        addWeightButton.setOnClickListener {
                weight++
                weightTextView.text="${weight.toInt()} kg"
            }
        heightSlider.addOnChangeListener { slider, value, fromUser ->
                height=value
                heightTextView.text="${height.toInt()} cm"
            }
        calculateButton.setOnClickListener {
                val result=weight/(height/100).pow(2)
                resultTextView.text=String.format("%.2f", result)

            var colorId=0
            var descriptionId=0

            when(result){
                in 0f..18.4f ->{
                    colorId=R.color.bmi_underweight
                    descriptionId=R.string.bmi_underweight
                }
                in 18.5f..24.9f->{
                    colorId=R.color.bmi_normal_weight
                    descriptionId=R.string.bmi_normal_weight
                }
                in 25f..29.9f->{
                    colorId=R.color.bmi_overweight
                    descriptionId=R.string.bmi_overweight
                }
                in 30f..35f ->{
                    colorId=R.color.bmi_obesity
                    descriptionId=R.string.bmi_obesity
                }
                else -> {
                    colorId=R.color.bmi_extreme_obesity
                    descriptionId=R.string.bmi_extreme_obesity
                }
            }
        descriptionTextView.text=getString(descriptionId)
        descriptionTextView.setTextColor(getColor(colorId))
        resultTextView.setTextColor(getColor(colorId))
        }

    }
}