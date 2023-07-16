package com.example.randomusergenerator.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    val gender: String? = null,
    val name: Name? = null,
    val email: String? = null,
    val location: Location? = null,
    val login: LoginDetails? = null,
    val dob: DateOfBirth? = null,
    val picture: Picture? = null,
)
