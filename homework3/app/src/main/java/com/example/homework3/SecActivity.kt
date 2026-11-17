package com.example.homework3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec)

        val tvReceivedMsg = findViewById<TextView>(R.id.tv_received_msg)
        val etReply = findViewById<EditText>(R.id.et_reply)
        val btnBackMain = findViewById<Button>(R.id.btn_back_main)

        intent.extras?.let { bundle ->
            val msg = bundle.getString("send_key")
            tvReceivedMsg.text = msg
        }

        btnBackMain.setOnClickListener {
            val replyBundle = Bundle()
            replyBundle.putString("reply_key", etReply.text.toString())

            val replyIntent = Intent()
            replyIntent.putExtras(replyBundle)

            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }
}