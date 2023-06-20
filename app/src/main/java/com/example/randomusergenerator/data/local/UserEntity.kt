package com.example.randomusergenerator.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.randomusergenerator.data.remote.dto.DateOfBirth
import com.example.randomusergenerator.data.remote.dto.LoginDetails
import com.example.randomusergenerator.data.remote.dto.PictureData
import com.example.randomusergenerator.data.remote.dto.Street

@Entity(tableName = "user_response")
data class UserResponseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val results: List<UserEntity>? = emptyList(),
    val info: InfoEntity? = null
)

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val gender: String? = null,
    val name: NameEntity? = null,
    val email: String? = null,
    val location: LocationEntity? = null,
    @Embedded(prefix = "Login_details")
    val login: LoginDetails? = null,
    @Embedded(prefix = "dob")
    val dob: DateOfBirth? = null,
    @Embedded(prefix = "picture_details")
    val picture: PictureData? = null,
)

@Entity(tableName = "info")
data class InfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val seed: String? = null,
    val results: Int? = 0,
    val page: Int? = 0,
    val version: String? = null,
)

@Entity(tableName = "name")
data class NameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String? = null,
    val first: String? = null,
    val last: String? = null,
)

@Entity(tableName = "location")
data class LocationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @Embedded(prefix = "street")
    val street: Street? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null
)
