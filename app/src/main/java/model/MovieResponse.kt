package model

data class MovieResponse(
    val titleResults: TitleResult
)

data class TitleResult(
    val results: ArrayList<Movie>
)