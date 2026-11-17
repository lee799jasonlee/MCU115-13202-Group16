package com.example.homework3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvReplyMsg: TextView

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val replyData = result.data?.extras?.getString("reply_key")
            tvReplyMsg.text = replyData
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvSendMsg = findViewById<TextView>(R.id.tv_send_msg)
        val btnGoSec = findViewById<Button>(R.id.btn_go_sec)
        tvReplyMsg = findViewById(R.id.tv_reply_msg)

        btnGoSec.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("send_key", tvSendMsg.text.toString())

            val intent = Intent(this, SecActivity::class.java)
            intent.putExtras(bundle)

            startForResult.launch(intent)
        }
    }
}