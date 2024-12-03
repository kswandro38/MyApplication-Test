package com.kalanasarange.myapplication.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data class representing a User.
 *
 * @param id - The unique identifier of the user.
 * @param firstName - The first name of the user.
 * @param lastName - The last name of the user.
 * @param age - The age of the user.
 * @param gender - The gender of the user.
 * @param image - The URL or path to the user's image.
 * @param email - The email address of the user.
 * @param company - The company information associated with the user.
 */
@Parcelize
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val image: String,
    val email: String,
    val company: Company
): Parcelable {

    /**
     * Data class representing a Company associated with a User.
     *
     * @param name - The name of the company.
     * @param title - The title or position of the user within the company.
     */
    @Parcelize
    class Company(
        val name: String,
        val title: String
    ): Parcelable
}