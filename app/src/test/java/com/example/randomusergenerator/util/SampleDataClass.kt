package com.example.randomusergenerator.util

import com.example.randomusergenerator.data.local.*
import com.example.randomusergenerator.data.remote.dto.*

val sampleUser = User(
    userId = 0,
    gender = "male",
    name = Name(
        title = "Mr",
        first = "John",
        last = "Doe"
    ),
    email = "johndoe@example.com",
    location = Location(
        street = Street(
            number = 123,
            name = "Main Street"
        ),
        city = "New York",
        state = "NY",
        country = "USA"
    ),
    login = LoginDetails(
        username = "johndoe123",
        password = "password123"
    ),
    dob = DateOfBirth(
        age = 30,
        date = "1992-05-20"
    ),
    picture = Picture(
        thumbnail = "https://example.com/thumbnail.jpg"
    )
)

val sampleUserResponse = UserResponse(
    results = listOf(sampleUser),
    info = Info(
        seed = "randomseed",
        results = 1,
        page = 1,
        version = "1.0"
    )
)

val sampleUserData = UserData(
    id = 0,
    gender = "male",
    name = NameData(
        title = "Mr",
        first = "John",
        last = "Doe"
    ),
    email = "johndoe@example.com",
    location = LocationData(
        street = StreetData(
            number = 123,
            name = "Main Street"
        ),
        city = "New York",
        state = "NY",
        country = "USA"
    ),
    login = LoginDetailsData(
        username = "johndoe123",
        password = "password123"
    ),
    dob = DateOfBirthData(
        age = 30,
        date = "1992-05-20"
    ),
    picture = PictureData(
        thumbnail = "https://example.com/thumbnail.jpg"
    )
)
