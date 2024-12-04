package repository

import model.MovieResponse
import model.TitleResponse
import network.IAPIService
import network.MovieService

class MovieRepository(private val service: IAPIService) :
    IRepository {

    suspend fun fetchMovies(query: String): MovieResponse? {
        try {
            return (service as MovieService).getMovies(query)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    suspend fun getMovieById(id: String): TitleResponse? {
        return (service as MovieService).getMovieTitleDetails(id)
    }

}