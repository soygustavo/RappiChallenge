package com.suarezgustavo.rappichallenge.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.suarezgustavo.rappichallenge.model.network.response.Location
import com.suarezgustavo.rappichallenge.model.network.response.UserRating
import com.suarezgustavo.rappichallenge.model.network.response.photo.Photos
import com.suarezgustavo.rappichallenge.model.network.response.review.Reviews

@Entity(tableName = "restaurant_table")
data class Restaurant(
    @PrimaryKey(autoGenerate = true) var internalID: Int,
    var id: Int,
    var name: String,
    var url: String,
    @Embedded(prefix = "location_") var location: Location?,
    @SerializedName("average_cost_for_two") var averageCostForTwo: Int,
    @SerializedName("price_range") var priceRange: Int,
    var currency: String,
    var thumb: String,
    @SerializedName("featured_image") var featuredImage: String,
    @SerializedName("photos_url") var photosUrl: String,
    @SerializedName("menu_url") var menuUrl: String,
    @SerializedName("events_url") var eventsUrl: String,
    @Embedded(prefix = "user_rating_") @SerializedName("user_rating") var userRating: UserRating?,
    @SerializedName("has_online_delivery") var hasOnlineDelivery: Int,
    @SerializedName("has_table_booking") var hasTableBooking: Int,
    var deeplink: String,
    var cuisines: String,
    @SerializedName("all_reviews_count") var allReviewsCount: Int,
    @SerializedName("photo_count") var photoCount: Int,
    @SerializedName("phone_numbers") var phoneNumbers: String,
    @Ignore @SerializedName("photos") var photoList: List<Photos>,
    @Ignore @SerializedName("all_reviews") var reviewList: Reviews?
) {
    constructor() : this(
        0, 0, "", "", null, 0, 0,
        "", "", "", "", "", "", null,
        0, 0, "", "", 0,
        0, "", emptyList(), null
    )
}