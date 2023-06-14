package com.example.randomusergenerator.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_generator_response")
data class UserGeneratorResponseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val result: List<UserEntity>? = emptyList(),
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
    val login: LoginDetailsEntity? = null,
    val dob: DateOfBirthEntity? = null,
    val picture: PictureDataEntity? = null,
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
    val street: StreetEntity? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null
)

@Entity(tableName = "street")
data class StreetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val number: Int? = 0,
    val name: String? = null,
)

@Entity(tableName = "login_details")
data class LoginDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val username: String? = null,
    val password: String? = null,
)

@Entity(tableName = "date_of_birth")
data class DateOfBirthEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val age: Int? = 0,
    val date: String? = null,
)

@Entity(tableName = "picture_data")
data class PictureDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val thumbnail: String? = null
)