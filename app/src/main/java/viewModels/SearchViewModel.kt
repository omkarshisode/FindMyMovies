package viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import network.MovieService
import network.RetrofitService
import repository.MovieRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.MovieResponse

class SearchViewModel : ViewModel() {

    private val movieService = RetrofitService.createService(MovieService::class.java)
    private val movieRepository = MovieRepository(movieService)

    fun fetchMovie(query: String): LiveData<MovieResponse> {
        val liveData = MutableLiveData<MovieResponse>()
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                movieRepository.fetchMovies(query)?.let { data ->
                    withContext(Dispatchers.Main) {
                        liveData.value = data
                    }
                }
            }
        }
        return liveData
    }
}