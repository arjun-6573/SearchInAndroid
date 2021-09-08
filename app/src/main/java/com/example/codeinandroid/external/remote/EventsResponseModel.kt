package com.example.codeinandroid.external.remote


import com.google.gson.annotations.SerializedName

data class EventsResponseModel(
    @SerializedName("events")
    val events: List<Event>,
    @SerializedName("meta")
    val meta: Meta
) {
    data class Event(
        @SerializedName("access_method")
        val accessMethod: AccessMethod,
        @SerializedName("announce_date")
        val announceDate: String,
        @SerializedName("announcements")
        val announcements: Announcements,
        @SerializedName("conditional")
        val conditional: Boolean,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("date_tbd")
        val dateTbd: Boolean,
        @SerializedName("datetime_local")
        val datetimeLocal: String,
        @SerializedName("datetime_tbd")
        val datetimeTbd: Boolean,
        @SerializedName("datetime_utc")
        val datetimeUtc: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("domain_information")
        val domainInformation: List<Any>,
        @SerializedName("enddatetime_utc")
        val enddatetimeUtc: String,
        @SerializedName("event_promotion")
        val eventPromotion: Any,
        @SerializedName("id")
        val id: Int,
        @SerializedName("is_open")
        val isOpen: Boolean,
        @SerializedName("links")
        val links: List<Any>,
        @SerializedName("performers")
        val performers: List<Performer>,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("score")
        val score: Double,
        @SerializedName("short_title")
        val shortTitle: String,
        @SerializedName("stats")
        val stats: Stats,
        @SerializedName("status")
        val status: String,
        @SerializedName("taxonomies")
        val taxonomies: List<Taxonomy>,
        @SerializedName("themes")
        val themes: List<Any>,
        @SerializedName("time_tbd")
        val timeTbd: Boolean,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("venue")
        val venue: Venue,
        @SerializedName("visible_until_utc")
        val visibleUntilUtc: String
    ) {
        data class AccessMethod(
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("employee_only")
            val employeeOnly: Boolean,
            @SerializedName("method")
            val method: String
        )

        data class Announcements(
            @SerializedName("checkout_disclosures")
            val checkoutDisclosures: CheckoutDisclosures
        ) {
            data class CheckoutDisclosures(
                @SerializedName("messages")
                val messages: List<Message>
            ) {
                data class Message(
                    @SerializedName("text")
                    val text: String
                )
            }
        }

        data class Performer(
            @SerializedName("away_team")
            val awayTeam: Boolean,
            @SerializedName("colors")
            val colors: Any,
            @SerializedName("divisions")
            val divisions: Any,
            @SerializedName("genres")
            val genres: List<Genre>,
            @SerializedName("has_upcoming_events")
            val hasUpcomingEvents: Boolean,
            @SerializedName("home_team")
            val homeTeam: Boolean,
            @SerializedName("home_venue_id")
            val homeVenueId: Any,
            @SerializedName("id")
            val id: Int,
            @SerializedName("image")
            val image: String,
            @SerializedName("image_attribution")
            val imageAttribution: Any,
            @SerializedName("image_license")
            val imageLicense: Any,
            @SerializedName("images")
            val images: Images,
            @SerializedName("location")
            val location: Any,
            @SerializedName("name")
            val name: String,
            @SerializedName("num_upcoming_events")
            val numUpcomingEvents: Int,
            @SerializedName("popularity")
            val popularity: Int,
            @SerializedName("primary")
            val primary: Boolean,
            @SerializedName("score")
            val score: Double,
            @SerializedName("short_name")
            val shortName: String,
            @SerializedName("slug")
            val slug: String,
            @SerializedName("stats")
            val stats: Stats,
            @SerializedName("taxonomies")
            val taxonomies: List<Taxonomy>,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String
        ) {
            data class Genre(
                @SerializedName("document_source")
                val documentSource: DocumentSource,
                @SerializedName("id")
                val id: Int,
                @SerializedName("image")
                val image: String,
                @SerializedName("images")
                val images: Images,
                @SerializedName("name")
                val name: String,
                @SerializedName("primary")
                val primary: Boolean,
                @SerializedName("slug")
                val slug: String
            ) {
                data class DocumentSource(
                    @SerializedName("generation_type")
                    val generationType: String,
                    @SerializedName("source_type")
                    val sourceType: String
                )

                data class Images(
                    @SerializedName("banner")
                    val banner: String,
                    @SerializedName("block")
                    val block: String,
                    @SerializedName("criteo_130_160")
                    val criteo130160: String,
                    @SerializedName("criteo_170_235")
                    val criteo170235: String,
                    @SerializedName("criteo_205_100")
                    val criteo205100: String,
                    @SerializedName("criteo_400_300")
                    val criteo400300: String,
                    @SerializedName("fb_100x72")
                    val fb100x72: String,
                    @SerializedName("fb_600_315")
                    val fb600315: String,
                    @SerializedName("huge")
                    val huge: String,
                    @SerializedName("ipad_event_modal")
                    val ipadEventModal: String,
                    @SerializedName("ipad_header")
                    val ipadHeader: String,
                    @SerializedName("ipad_mini_explore")
                    val ipadMiniExplore: String,
                    @SerializedName("mongo")
                    val mongo: String,
                    @SerializedName("square_mid")
                    val squareMid: String,
                    @SerializedName("triggit_fb_ad")
                    val triggitFbAd: String,
                    @SerializedName("136x136")
                    val x136: String,
                    @SerializedName("800x320")
                    val x320: String,
                    @SerializedName("500_700")
                    val x500700: String,
                    @SerializedName("1200x525")
                    val x525: String,
                    @SerializedName("1200x627")
                    val x627: String
                )
            }

            data class Images(
                @SerializedName("huge")
                val huge: String
            )

            data class Stats(
                @SerializedName("event_count")
                val eventCount: Int
            )

            data class Taxonomy(
                @SerializedName("document_source")
                val documentSource: DocumentSource,
                @SerializedName("id")
                val id: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("parent_id")
                val parentId: Any,
                @SerializedName("rank")
                val rank: Int
            ) {
                data class DocumentSource(
                    @SerializedName("generation_type")
                    val generationType: String,
                    @SerializedName("source_type")
                    val sourceType: String
                )
            }
        }

        data class Stats(
            @SerializedName("average_price")
            val averagePrice: Int,
            @SerializedName("dq_bucket_counts")
            val dqBucketCounts: List<Int>,
            @SerializedName("highest_price")
            val highestPrice: Int,
            @SerializedName("listing_count")
            val listingCount: Int,
            @SerializedName("lowest_price")
            val lowestPrice: Int,
            @SerializedName("lowest_price_good_deals")
            val lowestPriceGoodDeals: Int,
            @SerializedName("lowest_sg_base_price")
            val lowestSgBasePrice: Int,
            @SerializedName("lowest_sg_base_price_good_deals")
            val lowestSgBasePriceGoodDeals: Int,
            @SerializedName("median_price")
            val medianPrice: Int,
            @SerializedName("visible_listing_count")
            val visibleListingCount: Int
        )

        data class Taxonomy(
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("parent_id")
            val parentId: Any,
            @SerializedName("rank")
            val rank: Int
        )

        data class Venue(
            @SerializedName("access_method")
            val accessMethod: AccessMethod,
            @SerializedName("address")
            val address: String,
            @SerializedName("capacity")
            val capacity: Int,
            @SerializedName("city")
            val city: String,
            @SerializedName("country")
            val country: String,
            @SerializedName("display_location")
            val displayLocation: String,
            @SerializedName("extended_address")
            val extendedAddress: String,
            @SerializedName("has_upcoming_events")
            val hasUpcomingEvents: Boolean,
            @SerializedName("id")
            val id: Int,
            @SerializedName("links")
            val links: List<Any>,
            @SerializedName("location")
            val location: Location,
            @SerializedName("metro_code")
            val metroCode: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("name_v2")
            val nameV2: String,
            @SerializedName("num_upcoming_events")
            val numUpcomingEvents: Int,
            @SerializedName("popularity")
            val popularity: Int,
            @SerializedName("postal_code")
            val postalCode: String,
            @SerializedName("score")
            val score: Double,
            @SerializedName("slug")
            val slug: String,
            @SerializedName("state")
            val state: String,
            @SerializedName("timezone")
            val timezone: String,
            @SerializedName("url")
            val url: String
        ) {
            data class AccessMethod(
                @SerializedName("created_at")
                val createdAt: String,
                @SerializedName("employee_only")
                val employeeOnly: Boolean,
                @SerializedName("method")
                val method: String
            )

            data class Location(
                @SerializedName("lat")
                val lat: Double,
                @SerializedName("lon")
                val lon: Double
            )
        }
    }

    data class Meta(
        @SerializedName("geolocation")
        val geolocation: Any,
        @SerializedName("page")
        val page: Int,
        @SerializedName("per_page")
        val perPage: Int,
        @SerializedName("took")
        val took: Int,
        @SerializedName("total")
        val total: Int
    )
}