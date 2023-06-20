package com.example.randomusergenerator.data.mappers

import com.example.randomusergenerator.data.local.*
import com.example.randomusergenerator.data.remote.dto.*

fun UserResponseEntity.toDomain(): UserGeneratorResponse {
    return UserGeneratorResponse(
        results = results?.toDomain(),
        info = info?.toDomain()
    )
}

fun List<UserEntity>?.toDomain(): List<User>? {
    return this?.map { userEntity ->
        User(
            gender = userEntity.gender,
            name = userEntity.name?.toDomain(),
            email = userEntity.email,
            location = userEntity.location?.toDomain(),
            login = userEntity.login,
            dob = userEntity.dob,
            picture = userEntity.picture
        )
    }
}

fun InfoEntity?.toDomain(): Info? {
    return this?.let { info ->
        Info(
            seed = info.seed,
            results = info.results,
            page = info.page,
            version = info.version
        )
    }
}

fun NameEntity?.toDomain(): Name? {
    return this?.let { name ->
        Name(
            title = name.title,
            first = name.first,
            last = name.last
        )
    }
}

fun LocationEntity?.toDomain(): Location? {
    return this?.let { location ->
        Location(
            street = location.street,
            city = location.city,
            state = location.state,
            country = location.country
        )
    }
}
