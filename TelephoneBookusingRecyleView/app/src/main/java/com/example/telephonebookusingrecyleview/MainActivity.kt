package com.example.telephonebookusingrecycleview

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var btnAddContact: Button

    private val fullContactList = mutableListOf<Contact>()
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        etSearch = findViewById(R.id.etSearch)
        btnAddContact = findViewById(R.id.btnAddContact)

        // 產生 35 筆測試資料（大於 30 筆）
        generateDummyData()

        adapter = ContactAdapter(fullContactList) { contactToDelete ->
            fullContactList.remove(contactToDelete)
            filterList(etSearch.text.toString())
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before, count) {
                filterList(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        btnAddContact.setOnClickListener {
            showAddContactDialog()
        }
    }

    private fun generateDummyData() {
        for (i in 1..35) {
            fullContactList.add(
                Contact(
                    name = "聯絡人 $i",
                    phone = "0912345$i".padStart(10, '0'),
                    email = "contact$i@example.com"
                )
            )
        }
    }

    private fun filterList(query: String) {
        val filteredList = if (query.isEmpty()) {
            fullContactList
        } else {
            fullContactList.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.phone.contains(query, ignoreCase = true) ||
                        it.email.contains(query, ignoreCase = true)
            }
        }
        adapter.updateList(filteredList)
    }

    private fun showAddContactDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_contact, null)
        val etName = dialogView.findViewById<EditText>(R.id.etNameInput)
        val etPhone = dialogView.findViewById<EditText>(R.id.etPhoneInput)
        val etEmail = dialogView.findViewById<EditText>(R.id.etEmailInput)

        AlertDialog.Builder(this)
            .setTitle("新增聯絡人")
            .setView(dialogView)
            .setPositiveButton("確認新增") { _, _ ->
                val name = etName.text.toString().trim()
                val phone = etPhone.text.toString().trim()
                val email = etEmail.text.toString().trim()

                if (name.isNotEmpty() && phone.isNotEmpty()) {
                    val newContact = Contact(name = name, phone = phone, email = email)
                    fullContactList.add(0, newContact)
                    filterList(etSearch.text.toString())
                }
            }
            .setNegativeButton("取消", null)
            .show()
    }
}