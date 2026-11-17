package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var selectedMainMeal: String = "-"
    private var selectedSideDish: String = "-"
    private var selectedDrink: String = "-"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvMain = findViewById<Button>(R.id.tvMain)
        val tvSide = findViewById<Button>(R.id.tvSide)
        val tvDrink = findViewById<Button>(R.id.tvDrink)
        val tvall = findViewById<TextView>(R.id.tvall)
        val ordal = findViewById<Button>(R.id.ordal)

        fun updateSummary() {
            tvall.text = "Main:$selectedMainMeal\nSides:$selectedSideDish\nDrink:$selectedDrink"
        }

        tvMain.setOnClickListener {
            val dialog = MainMealFragment()
            dialog.onSelectedListener = { mainMeal ->
                selectedMainMeal = mainMeal
                updateSummary()
            }
            dialog.show(supportFragmentManager, "MainMealFragment")
        }

        tvSide.setOnClickListener {
            val dialog = SideDishesFragment()
            dialog.onSelectedListener = { sideDish ->
                selectedSideDish = sideDish
                updateSummary()
            }
            dialog.show(supportFragmentManager, "SideDishesFragment")
        }

        tvDrink.setOnClickListener {
            val dialog = DrinkFragment()
            dialog.onSelectedListener = { drink ->
                selectedDrink = drink
                updateSummary()
            }
            dialog.show(supportFragmentManager, "DrinkFragment")
        }

        ordal.setOnClickListener {
            val dialog = OrderViewModel()
            if (selectedMainMeal == "-" && selectedSideDish == "-" && selectedDrink == "-") {
                Toast.makeText(this, "請先選擇餐點！", Toast.LENGTH_SHORT).show()
            } else {
               dialog.setSummary(selectedMainMeal, selectedSideDish, selectedDrink)
                dialog.show(supportFragmentManager, "OrderViewModel")
            }

        }
            }



    }
