package com.kiparisov.roomtest.room.account

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.NOCASE
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kiparisov.roomtest.pojo.Account


@Entity(
    tableName = "accounts",
    indices = [
        Index("email", unique = true)
    ]
)
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(collate = NOCASE) val email: String,
    val password: String,
    @ColumnInfo(name = "created_at") val createdAt: String
) {

    fun toAccount(): Account = Account(
        id = id,
        email = email,
        password = password,
        createdAt = createdAt
    )
}