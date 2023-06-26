package com.example.randomusergenerator.utils

import androidx.room.TypeConverter
import com.example.randomusergenerator.data.remote.dto.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class TypeConverter {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @TypeConverter
    fun fromNameStringToClass(value: String): Name? {
        return moshi.adapter(Name::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromNameClassToString(value: Name): String? {
        return moshi.adapter(Name::class.java).toJson(value)
    }

    @TypeConverter
    fun fromLocationStringToClass(value: String): Location? {
        return moshi.adapter(Location::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromLocationClassToString(value: Location): String? {
        return moshi.adapter(Location::class.java).toJson(value)
    }

    @TypeConverter
    fun fromLoginDetailsStringToClass(value: String): LoginDetails? {
        return moshi.adapter(LoginDetails::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromLoginDetailsClassToString(value: LoginDetails): String? {
        return moshi.adapter(LoginDetails::class.java).toJson(value)
    }

    @TypeConverter
    fun fromDobStringToClass(value: String): DateOfBirth? {
        return moshi.adapter(DateOfBirth::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromDobClassToString(value: DateOfBirth): String? {
        return moshi.adapter(DateOfBirth::class.java).toJson(value)
    }

    @TypeConverter
    fun fromPictureStringToClass(value: String): Picture? {
        return moshi.adapter(Picture::class.java).fromJson(value)
    }

    @TypeConverter
    fun fromPictureClassToString(value: Picture): String? {
        return moshi.adapter(Picture::class.java).toJson(value)
    }
}
