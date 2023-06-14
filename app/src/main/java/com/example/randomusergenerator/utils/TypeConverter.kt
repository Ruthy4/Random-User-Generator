package com.example.randomusergenerator.utils

import androidx.room.TypeConverter
import com.example.randomusergenerator.data.local.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object TypeConverter {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val userEntityListAdapter: JsonAdapter<List<UserEntity>> =
        moshi.adapter(Types.newParameterizedType(List::class.java, UserEntity::class.java))


    @TypeConverter
    fun fromUserStringToClass(value: String): UserEntity? {
        return moshi.adapter(UserEntity::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromUserClassToString(value: UserEntity): String? {
        return moshi.adapter(UserEntity::class.java).toJson(value)
    }

    @TypeConverter
    fun fromStreetStringToClass(value: String): StreetEntity? {
        return moshi.adapter(StreetEntity::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromStreetClassToString(value: StreetEntity): String? {
        return moshi.adapter(StreetEntity::class.java).toJson(value)
    }

    @TypeConverter
    fun fromNameStringToClass(value: String): NameEntity? {
        return moshi.adapter(NameEntity::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromNameClassToString(value: NameEntity): String? {
        return moshi.adapter(NameEntity::class.java).toJson(value)
    }

    @TypeConverter
    fun userGeneratorResponseEntityFromJson(value: String): UserGeneratorResponseEntity? {
        return moshi.adapter(UserGeneratorResponseEntity::class.java).fromJson(value)
    }

    @TypeConverter
    fun userGeneratorResponseEntityToJson(value: UserGeneratorResponseEntity): String? {
        return moshi.adapter(UserGeneratorResponseEntity::class.java).toJson(value)
    }

    @TypeConverter
    fun fromLocationStringToClass(value: String): LocationEntity? {
        return moshi.adapter(LocationEntity::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromLocationClassToString(value: LocationEntity): String? {
        return moshi.adapter(LocationEntity::class.java).toJson(value)
    }

    @TypeConverter
    fun fromLoginDetailsStringToClass(value: String): LoginDetailsEntity? {
        return moshi.adapter(LoginDetailsEntity::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromLoginDetailsClassToString(value: LoginDetailsEntity): String? {
        return moshi.adapter(LoginDetailsEntity::class.java).toJson(value)
    }

    @TypeConverter
    fun fromDateOfBirthStringToClass(value: String): DateOfBirthEntity? {
        return moshi.adapter(DateOfBirthEntity::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromDateOfBirthClassToString(value: DateOfBirthEntity): String? {
        return moshi.adapter(DateOfBirthEntity::class.java).toJson(value)
    }

    @TypeConverter
    fun fromPictureDataStringToClass(value: String): PictureDataEntity? {
        return moshi.adapter(PictureDataEntity::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromPictureDataClassToString(value: PictureDataEntity): String? {
        return moshi.adapter(PictureDataEntity::class.java).toJson(value)
    }

    @TypeConverter
    fun fromInfoStringToClass(value: String): InfoEntity? {
        return moshi.adapter(InfoEntity::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromInfoClassToString(value: InfoEntity): String? {
        return moshi.adapter(InfoEntity::class.java).toJson(value)
    }


    @TypeConverter
    fun fromUserListStringToClass(value: String): List<UserEntity>? {
        return userEntityListAdapter.fromJson(value)
    }

    @TypeConverter
    fun fromUserListClassToString(value: List<UserEntity>): String? {
        return userEntityListAdapter.toJson(value)
    }
}