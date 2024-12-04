package model

data class NodeX(
    val __typename: String,
    val contentType: ContentType,
    val createdDate: String,
    val description: Description,
    val id: String,
    val isMature: Boolean,
    val name: Name,
    val playbackURLs: List<PlaybackURL>,
    val previewURLs: List<PreviewURL>,
    val primaryTitle: PrimaryTitle,
    val recommendedTimedTextTrack: RecommendedTimedTextTrack,
    val runtime: Runtime,
    val thumbnail: Thumbnail,
    val timedTextTracks: List<TimedTextTrack>
)