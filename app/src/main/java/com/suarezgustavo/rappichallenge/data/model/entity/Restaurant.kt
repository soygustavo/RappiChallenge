package com.suarezgustavo.rappichallenge.data.model.entity

import com.google.gson.annotations.SerializedName

data class Restaurant(
    val id: Int,
    val name: String,
    val url: String,
    val location: Location,
    @SerializedName("average_cost_for_two") val averageCostForTwo: Int,
    @SerializedName("price_range") val priceRange: Int,
    val currency: String,
    val thumb: String,
    @SerializedName("featured_image") val featuredImage: String,
    @SerializedName("photos_url") val photosUrl: String,
    @SerializedName("menu_url") val menuUrl: String,
    @SerializedName("events_url") val eventsUrl: String,
    @SerializedName("user_rating") val userRating: UserRating,
    @SerializedName("has_online_delivery") val hasOnlineDelivery: Int,
    @SerializedName("is_delivering_now") val isDeliveringNow: Int,
    @SerializedName("has_table_booking") val hasTableBooking: Int,
    val deeplink: String,
    val cuisines: String,
    @SerializedName("all_reviews_count") val allReviewsCount: Int,
    @SerializedName("photo_count") val photoCount: Int,
    @SerializedName("phone_numbers") val phoneNumbers: String,
    @SerializedName("photos") val photoList: List<Photo>,
    @SerializedName("all_reviews") val reviewList: List<Review>
)