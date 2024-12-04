package model

data class PrimaryTitle(
    val __typename: String,
    val id: String,
    val originalTitleText: OriginalTitleText,
    val releaseYear: ReleaseYear,
    val titleText: TitleTextX
)