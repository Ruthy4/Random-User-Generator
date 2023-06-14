package com.example.randomusergenerator.data.mapper

import com.example.randomusergenerator.data.local.*
import com.example.randomusergenerator.data.remote.dto.*
import com.example.randomusergenerator.utils.DomainMapper
import javax.inject.Inject

class UserDomainEntityMapper @Inject constructor() : DomainMapper<UserEntity, User> {
    override fun mapToDomainModel(model: UserEntity): User {
        return User(
            gender = model.gender,
            name = mapNameEntityToDomain(model.name),
            email = model.email,
            dob = mapDateOfBirthEntityToDomain(model.dob),
            login = mapLoginDetailsEntityToDomain(model.login),
            picture = mapPictureDataEntityToDomain(model.picture),
            location = mapLocationEntityToDomain(model.location)
        )
    }

    override fun mapFromDomainModel(domainModel: User): UserEntity {
        return UserEntity(
            gender = domainModel.gender,
            name = mapNameToEntity(domainModel.name),
            email = domainModel.email,
            dob = mapDateOfBirthToEntity(domainModel.dob),
            login = mapLoginDetailsToEntity(domainModel.login),
            picture = mapPictureDataToEntity(domainModel.picture),
            location = mapLocationToEntity(domainModel.location)
        )
    }

    private fun mapNameEntityToDomain(entity: NameEntity?): Name? {
        return entity?.let {
            Name(
                title = entity.title,
                first = entity.first,
                last = entity.last
            )
        }
    }

    private fun mapNameToEntity(name: Name?): NameEntity? {
        return name?.let {
            NameEntity(
                title = name.title,
                first = name.first,
                last = name.last
            )
        }
    }

    private fun mapLocationEntityToDomain(entity: LocationEntity?): Location? {
        return entity?.let {
            Location(
                street = mapStreetEntityToDomain(entity.street),
                city = entity.city,
                state = entity.state,
                country = entity.country
            )
        }
    }

    private fun mapLocationToEntity(location: Location?): LocationEntity? {
        return location?.let {
            LocationEntity(
                street = mapStreetToEntity(location.street),
                city = location.city,
                state = location.state,
                country = location.country
            )
        }
    }

    private fun mapStreetEntityToDomain(entity: StreetEntity?): Street? {
        return entity?.let {
            Street(
                number = entity.number,
                name = entity.name
            )
        }
    }

    private fun mapStreetToEntity(street: Street?): StreetEntity? {
        return street?.let {
            StreetEntity(
                number = street.number,
                name = street.name
            )
        }
    }

    private fun mapLoginDetailsEntityToDomain(entity: LoginDetailsEntity?): LoginDetails? {
        return entity?.let {
            LoginDetails(
                username = entity.username,
                password = entity.password
            )
        }
    }

    private fun mapLoginDetailsToEntity(login: LoginDetails?): LoginDetailsEntity? {
        return login?.let {
            LoginDetailsEntity(
                username = login.username,
                password = login.password
            )
        }
    }

    private fun mapDateOfBirthEntityToDomain(entity: DateOfBirthEntity?): DateOfBirth? {
        return entity?.let {
            DateOfBirth(
                age = entity.age,
                date = entity.date
            )
        }
    }

    private fun mapDateOfBirthToEntity(dob: DateOfBirth?): DateOfBirthEntity? {
        return dob?.let {
            DateOfBirthEntity(
                age = dob.age,
                date = dob.date
            )
        }
    }

    private fun mapPictureDataEntityToDomain(entity: PictureDataEntity?): PictureData? {
        return entity?.let {
            PictureData(
                thumbnail = entity.thumbnail
            )
        }
    }

    private fun mapPictureDataToEntity(picture: PictureData?): PictureDataEntity? {
        return picture?.let {
            PictureDataEntity(
                thumbnail = picture.thumbnail
            )
        }
    }
}