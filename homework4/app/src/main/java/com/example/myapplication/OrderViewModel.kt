package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OrderViewModel : BottomSheetDialogFragment() {

    private var summaryText: String = ""

    // 接收來自 MainActivity 的點餐結果
    fun setSummary(selectedMainMeal: String, selectedSideDish: String, selectedDrink: String) {
        summaryText = "Main: $selectedMainMeal\nSide: $selectedSideDish\nDrink: $selectedDrink"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 載入 order_view 介面
        return inflater.inflate(R.layout.order_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 綁定 order_view.xml 的元件
        val tvall = view.findViewById<TextView>(R.id.tvall)
        val confirmButton = view.findViewById<Button>(R.id.confirm_button)

        // 顯示點餐明細
        tvall.text = summaryText

        // 點擊確認關閉彈窗
        confirmButton.setOnClickListener {
            dismiss()
        }
    }
}