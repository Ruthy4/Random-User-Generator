package com.example.randomusergenerator.data.remote.dto

data class UserGeneratorResponse(
    val result: List<Result>? = emptyList(),
    val info: Info? = Info()
)


data class Result(
    val gender: String? = "",
    val name: Name? = Name(),
    val email: String? = "",
    val location: Location? = Location(),
    val login: LoginDetails? = LoginDetails(),
    val dob: DateOfBirth? = DateOfBirth(),
    val picture: PictureData? = PictureData(),
)

data class Info(
    val seed: String? = "",
    val results: Int? = 0,
    val page: Int? = 0,
    val version: String? = "",
)

data class Name(
    val title: String? = "",
    val first: String? = "",
    val last: String? = "",
)

data class Location(
    val street: Street? = Street(),
    val city: String? = "",
    val state: String? = "",
    val country: String? = ""
)

data class Street(
    val number: Int? = 0,
    val name: String? = "",
)

data class LoginDetails(
    val username: String? = "",
    val password: String? = "",
)

data class DateOfBirth(
    val age: Int? = 0,
    val date: String? = "",
)

data class PictureData(
    val thumbnail: String? = ""
)