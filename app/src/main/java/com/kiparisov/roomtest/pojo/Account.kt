package com.kiparisov.roomtest.pojo

data class Account(
    val id: Long,
    val email: String,
    val password: String,
    val createdAt: String
) {

    constructor(email: String, password: String, createdAt: String)
        :this(0, email, password, createdAt)
}