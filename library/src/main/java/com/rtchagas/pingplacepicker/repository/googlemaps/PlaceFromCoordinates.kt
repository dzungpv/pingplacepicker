package com.rtchagas.pingplacepicker.repository.googlemaps

import android.location.Location
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.libraries.places.api.model.*
import kotlin.math.absoluteValue
import com.google.android.libraries.places.api.model.Place.BooleanPlaceAttributeValue.UNKNOWN

/**
 * Place without any additional info. Just latitude and longitude.
 */
internal class PlaceFromCoordinates(
    private val latitude: Double,
    private val longitude: Double
) : Place() {

    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun getUserRatingsTotal(): Int? {
        return null
    }

    /**
     * Default value only.
     * Clients shouldn't rely on this.
     */
    override fun getBusinessStatus(): BusinessStatus {
        return BusinessStatus.OPERATIONAL
    }

    override fun getName(): String {
        return "${formatLatitude(latitude)}, ${formatLongitude(longitude)}"
    }

    override fun getOpeningHours(): OpeningHours? {
        return null
    }

    override fun getCurbsidePickup(): BooleanPlaceAttributeValue {
        return UNKNOWN
    }

    override fun getDelivery(): BooleanPlaceAttributeValue {
        return UNKNOWN
    }

    override fun getDineIn(): BooleanPlaceAttributeValue {
        return UNKNOWN
    }

    override fun getReservable(): BooleanPlaceAttributeValue {
        return UNKNOWN
    }

    override fun getServesBeer(): BooleanPlaceAttributeValue {
        return UNKNOWN
    }

    override fun getServesBreakfast(): BooleanPlaceAttributeValue {
        return UNKNOWN
    }

    override fun getServesBrunch(): BooleanPlaceAttributeValue {
        return UNKNOWN
    }

    override fun getServesDinner(): BooleanPlaceAttributeValue {
        return UNKNOWN
    }

    override fun getServesLunch(): BooleanPlaceAttributeValue {
        return UNKNOWN
    }

    override fun getServesVegetarianFood(): BooleanPlaceAttributeValue {
        return UNKNOWN
    }

    override fun getServesWine(): BooleanPlaceAttributeValue {
        return UNKNOWN
    }

    override fun getTakeout(): BooleanPlaceAttributeValue {
        return UNKNOWN
    }

    override fun getWheelchairAccessibleEntrance(): BooleanPlaceAttributeValue {
        return UNKNOWN
    }

    override fun getId(): String? {
        return null
    }

    override fun getPhotoMetadatas(): MutableList<PhotoMetadata> {
        return mutableListOf()
    }

    override fun getSecondaryOpeningHours(): MutableList<OpeningHours>? {
        return null
    }

    override fun getWebsiteUri(): Uri? {
        return null
    }

    override fun getPhoneNumber(): String? {
        return null
    }

    override fun getRating(): Double? {
        return null
    }

    override fun getIconBackgroundColor(): Int? {
        return null
    }

    override fun getPriceLevel(): Int? {
        return null
    }

    override fun getAddressComponents(): AddressComponents? {
        return null
    }

    override fun getCurrentOpeningHours(): OpeningHours? {
        return null
    }

    override fun getAttributions(): MutableList<String> {
        return mutableListOf()
    }

    override fun getAddress(): String? {
        return null
    }

    override fun getIconUrl(): String? {
        return null
    }

    override fun getPlusCode(): PlusCode? {
        return null
    }

    override fun getUtcOffsetMinutes(): Int? {
        return null
    }

    override fun getTypes(): MutableList<Type> {
        return mutableListOf()
    }

    override fun getViewport(): LatLngBounds? {
        return null
    }

    override fun getLatLng(): LatLng {
        return LatLng(latitude, longitude)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlaceFromCoordinates> {
        override fun createFromParcel(parcel: Parcel): PlaceFromCoordinates {
            return PlaceFromCoordinates(parcel)
        }

        override fun newArray(size: Int): Array<PlaceFromCoordinates?> {
            return arrayOfNulls(size)
        }
    }

    // formatting methods -----------------------------------------------------------------

    private fun formatLatitude(latitude: Double): String {
        val direction = if (latitude > 0) "N" else "S"
        return "${
            replaceDelimiters(
                Location.convert(
                    latitude.absoluteValue,
                    Location.FORMAT_SECONDS
                )
            )
        } $direction"
    }

    private fun formatLongitude(longitude: Double): String {
        val direction = if (longitude > 0) "W" else "E"
        return "${
            replaceDelimiters(
                Location.convert(
                    longitude.absoluteValue,
                    Location.FORMAT_SECONDS
                )
            )
        } $direction"
    }

    private fun replaceDelimiters(original: String): String {

        val parts: List<String> = original.split(":")

        val degrees: String = parts[0]
        val minutes: String = parts[1]
        var seconds: String = parts[2]

        val idx = seconds.indexOfAny(charArrayOf(',', '.'))
        if (idx >= 0) {
            seconds = seconds.substring(0, idx)
        }

        return "${degrees}° ${minutes}' ${seconds}\""
    }
}
