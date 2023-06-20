package com.example.randomusergenerator.data.mapper

import com.example.randomusergenerator.data.local.*
import com.example.randomusergenerator.data.remote.dto.*


fun UserGeneratorResponse?.toEntity(): UserGeneratorResponseEntity? {
    return this?.let { it ->
        UserGeneratorResponseEntity(
            result = it.results?.toEntity(),
            info = it.info?.toEntity()
        )
    }
}


fun Info?.toEntity(): InfoEntity? {
    return this?.let { info ->
        InfoEntity(
            seed = info.seed,
            results = info.results,
            page = info.page,
            version = info.version
        )
    }
}

fun List<User>?.toEntity(): List<UserEntity>? {
    return this?.map { user ->
        UserEntity(
            gender = user.gender,
            name = user.name?.toEntity(),
            email = user.email,
            location = user.location?.toEntity(),
            login = user.login,
            dob = user.dob,
            picture = user.picture
        )
    }
}

fun Name?.toEntity(): NameEntity {
    return this.let { name ->
        NameEntity(
            title = name?.title,
            first = name?.first,
            last = name?.last
        )
    }
}

fun Location?.toEntity(): LocationEntity? {
    return this?.let { location ->
        LocationEntity(
            street = location.street,
            city = location.city,
            state = location.state,
            country = location.country
        )
    }
}

