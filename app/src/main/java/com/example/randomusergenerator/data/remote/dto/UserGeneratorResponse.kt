package com.example.randomusergenerator.data.remote.dto

data class UserGeneratorResponse(
    val result: List<Result>? = emptyList(),
    val info: Info? = null
)

data class Result(
    val gender: String? = null,
    val name: Name? = null,
    val email: String? = null,
    val location: Location? = null,
    val login: LoginDetails? = null,
    val dob: DateOfBirth? = null,
    val picture: PictureData? = null,
)

data class Info(
    val seed: String? = null,
    val results: Int? = 0,
    val page: Int? = 0,
    val version: String? = null,
)

data class Name(
    val title: String? = null,
    val first: String? = null,
    val last: String? = null,
)

data class Location(
    val street: Street? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null
)

data class Street(
    val number: Int? = 0,
    val name: String? = null,
)

data class LoginDetails(
    val username: String? = null,
    val password: String? = null,
)

data class DateOfBirth(
    val age: Int? = 0,
    val date: String? = null,
)

data class PictureData(
    val thumbnail: String? = null
)
