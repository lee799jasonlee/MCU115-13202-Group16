package com.example.mycalculatorfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvExpression: TextView

    private var firstNumber: Double? = null
    private var currentOperator: String? = null
    private var isNewOp: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
        tvExpression = findViewById(R.id.tvExpression)

        val numberButtons = listOf(
            Pair(R.id.btn0, "0"), Pair(R.id.btn1, "1"), Pair(R.id.btn2, "2"),
            Pair(R.id.btn3, "3"), Pair(R.id.btn4, "4"), Pair(R.id.btn5, "5"),
            Pair(R.id.btn6, "6"), Pair(R.id.btn7, "7"), Pair(R.id.btn8, "8"),
            Pair(R.id.btn9, "9"), Pair(R.id.btnDot, ".")
        )
        for ((id, value) in numberButtons) {
            findViewById<Button>(id).setOnClickListener { appendNumber(value) }
        }

        findViewById<Button>(R.id.btnPlus).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { setOperator("×") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { setOperator("÷") }

        findViewById<Button>(R.id.btnEqual).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.btnC).setOnClickListener { clearCurrent() }
        findViewById<Button>(R.id.btnAC).setOnClickListener { clearAll() }
        findViewById<Button>(R.id.btnBackspace).setOnClickListener { backspace() }
    }

    private fun appendNumber(value: String) {
        if (isNewOp) {
            tvResult.text = ""
            isNewOp = false
        }
        if (value == "." && tvResult.text.contains(".")) return
        tvResult.append(value)
    }

    private fun setOperator(op: String) {
        val currentText = tvResult.text.toString()
        if (currentText.isNotEmpty()) {
            firstNumber = currentText.toDoubleOrNull()
            currentOperator = op
            tvExpression.text = "$currentText $op"
            isNewOp = true
        }
    }

    private fun calculateResult() {
        val secondNumber = tvResult.text.toString().toDoubleOrNull()
        if (firstNumber != null && secondNumber != null && currentOperator != null) {
            val result = when (currentOperator) {
                "+" -> firstNumber!! + secondNumber
                "-" -> firstNumber!! - secondNumber
                "×" -> firstNumber!! * secondNumber
                "÷" -> {
                    if (secondNumber == 0.0) {
                        tvResult.text = "Error"
                        return
                    }
                    firstNumber!! / secondNumber
                }
                else -> 0.0
            }

            val resultText = if (result % 1.0 == 0.0) {
                result.toLong().toString()
            } else {
                result.toString()
            }

            tvExpression.text = "${tvExpression.text} ${tvResult.text} ="
            tvResult.text = resultText
            firstNumber = null
            currentOperator = null
            isNewOp = true
        }
    }

    private fun clearCurrent() {
        tvResult.text = "0"
        isNewOp = true
    }

    private fun clearAll() {
        firstNumber = null
        currentOperator = null
        tvResult.text = "0"
        tvExpression.text = ""
        isNewOp = true
    }

    private fun backspace() {
        val current = tvResult.text.toString()
        if (current.length > 1) {
            tvResult.text = current.dropLast(1)
        } else {
            tvResult.text = "0"
            isNewOp = true
        }
    }
}