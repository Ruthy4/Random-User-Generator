package com.example.randomusergenerator.data.remote.dto

data class UserGeneratorResponse(
    val result: List<Result>,
    val info: Info
)


data class Result(
    val gender: String,
    val name: Name,
    val email: String,
    val location: Location,
    val login: LoginDetails,
    val dob: DateOfBirth,
    val picture: PictureData,
)

data class Info(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String,
)

data class Name(
    val title: String,
    val first: String,
    val last: String,
)

data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String
)

data class Street(
    val number: Int,
    val name: String,
)

data class LoginDetails(
    val username: String,
    val password: String,
)

data class DateOfBirth(
    val age: Int,
    val date: String,
)

data class PictureData(
    val thumbnail: String
)