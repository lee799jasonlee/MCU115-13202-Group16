package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DrinkFragment : BottomSheetDialogFragment() {

    var onSelectedListener: ((String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.select_drink, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rgDrink = view.findViewById<RadioGroup>(R.id.rgDrink)
        val btnDone = view.findViewById<Button>(R.id.btnDone)

        btnDone.setOnClickListener {
            val checkedId = rgDrink.checkedRadioButtonId
            if (checkedId != -1) {
                val radioButton = view.findViewById<RadioButton>(checkedId)
                val selectedText = radioButton.text.toString()
                onSelectedListener?.invoke(selectedText)
            }
            dismiss()
        }
    }
}