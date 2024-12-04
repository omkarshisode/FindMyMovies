package model

data class TitlePosterImageModel(
    val url: String?,
    val maxHeight: Int?,
    val maxWidth: Int?,
    val caption: String?,
    val topCredits: ArrayList<String>?,
    val imageType: String?
)
