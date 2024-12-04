package network

import model.MovieResponse
import model.TitleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService: IAPIService {
    @GET("/v1/find/")
    suspend fun getMovies(@Query("query") query: String): MovieResponse

    @GET("/v1/title/")
    suspend fun getMovieTitleDetails(@Query("id") movieId: String): TitleResponse

}