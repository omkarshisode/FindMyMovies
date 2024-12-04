package viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.TitleResponse
import network.MovieService
import network.RetrofitService
import repository.MovieRepository

class MovieDetailsViewModel : ViewModel() {
    private val movieService = RetrofitService.createService(MovieService::class.java)
    private val movieRepository = MovieRepository(movieService)

    fun fetchMovieDetails(titleId: String): LiveData<TitleResponse> {
        val liveData = MutableLiveData<TitleResponse>()
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getMovieById(titleId)?.let { data ->
                withContext(Dispatchers.Main) {
                    liveData.value = data
                }
            }
        }
        return liveData
    }
}