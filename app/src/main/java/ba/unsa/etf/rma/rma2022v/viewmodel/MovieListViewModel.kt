package ba.unsa.etf.rma.rma2022v.viewmodel

import android.content.Context
import ba.unsa.etf.rma.rma2022v.data.GetMoviesResponse
import ba.unsa.etf.rma.rma2022v.data.MovieRepository
import ba.unsa.etf.rma.rma2022v.data.Result
import ba.unsa.etf.rma.rma2022v.data.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieListViewModel(private val context: Context) {
    val favoriteMovies = MovieRepository.getFavorites(context)

    val scope = CoroutineScope(Job() + Dispatchers.Main)


    fun search(query: String,onSuccess: (movies: List<Movie>) -> Unit,
               onError: () -> Unit){

        // Create a new coroutine on the UI thread
        scope.launch{

            // Make the network call and suspend execution until it finishes
            val result = MovieRepository.searchRequest(query)

            // Display result of the network request to the user
            when (result) {
                is GetMoviesResponse -> onSuccess?.invoke(result.movies)
                else-> onError?.invoke()
            }
        }
    }



    fun getUpcoming( onSuccess: (movies: List<Movie>) -> Unit,
                     onError: () -> Unit){

        // Create a new coroutine on the UI thread
        scope.launch{

            // Make the network call and suspend execution until it finishes
            val result = MovieRepository.getUpcomingMovies()

            // Display result of the network request to the user
            when (result) {
                is GetMoviesResponse -> onSuccess?.invoke(result.movies)
                else-> onError?.invoke()
            }
        }
    }
}