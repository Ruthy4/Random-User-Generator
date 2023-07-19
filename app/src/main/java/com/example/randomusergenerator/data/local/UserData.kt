package com.example.randomusergenerator.data.local

import android.os.Parcelable
import com.example.randomusergenerator.data.remote.dto.*
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val id: Int = 0,
    val gender: String? = null,
    val name: NameData? = null,
    val email: String? = null,
    val location: LocationData? = null,
    val login: LoginDetailsData? = null,
    val dob: DateOfBirthData? = null,
    val picture: PictureData? = null,
) : Parcelable {
    companion object {
        fun from(users: List<User>): List<UserData> {
            return users.map {
                UserData(
                    id = it.userId,
                    gender = it.gender,
                    name = NameData.from(it.name),
                    email = it.email,
                    location = LocationData.from(it.location),
                    login = LoginDetailsData.from(it.login),
                    dob = DateOfBirthData.from(it.dob),
                    picture = PictureData.from(it.picture)
                )
            }
        }
    }
}
