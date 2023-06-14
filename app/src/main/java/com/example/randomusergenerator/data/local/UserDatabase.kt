package com.example.randomusergenerator.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.randomusergenerator.utils.TypeConverter


@Database(
    entities = [UserEntity::class,
        UserGeneratorResponseEntity::class,
        InfoEntity::class,
        NameEntity::class,
        LocationEntity::class,
        StreetEntity::class,
        LoginDetailsEntity::class,
        DateOfBirthEntity::class,
        PictureDataEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userGeneratorResponseDao(): UserGeneratorResponseDao
    abstract fun infoDao(): InfoDao
}
