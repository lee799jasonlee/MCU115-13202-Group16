package com.example.telephonebookusingrecycleview

data class Contact(
    val id: String = System.currentTimeMillis().toString(),
    val name: String,
    val phone: String,
    val email: String
)