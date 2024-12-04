package model

data class TitleResponse(
    val featuredReviews: FeaturedReviews,
    val id: String,
    val primaryImage: PrimaryImage,
    val primaryVideos: PrimaryVideos,
    val ratingsSummary: RatingsSummary,
    val releaseDate: ReleaseDate,
    val releaseYear: ReleaseYearX,
    val titleText: TitleTextX
)