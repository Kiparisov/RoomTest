package com.kiparisov.roomtest.pojo

data class Account(
    val id: Long,
    val email: String,
    val password: String,
    val createdAt: Long
) {

    constructor(email: String, password: String, createdAt: Long)
        :this(0, email, password, createdAt)
}