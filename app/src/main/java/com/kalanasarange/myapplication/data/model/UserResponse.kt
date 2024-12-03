package com.kalanasarange.myapplication.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Data class representing a response containing a list of users.
 *
 * @param users - A list of [User] objects received in the response.
 */
@Parcelize
data class UserResponse(
    @SerializedName("users") val users: ArrayList<User>
) : Parcelable