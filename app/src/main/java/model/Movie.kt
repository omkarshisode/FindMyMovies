package model

data class Movie(
    val id: String,
    val titleNameText: String,
    val titleReleaseText: String,
    val titleTypeText: String,
    val titlePosterImageModel: TitlePosterImageModel?
)

