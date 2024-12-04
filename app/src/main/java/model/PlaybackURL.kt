package model

data class PlaybackURL(
    val __typename: String,
    val displayName: DisplayNameX,
    val url: String,
    val videoDefinition: String,
    val videoMimeType: String
)