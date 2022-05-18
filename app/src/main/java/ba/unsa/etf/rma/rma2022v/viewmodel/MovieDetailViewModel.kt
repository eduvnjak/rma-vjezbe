package ba.unsa.etf.rma.rma2022v.viewmodel

import android.util.Log
import ba.unsa.etf.rma.rma2022v.data.ActorMovieRepository
import ba.unsa.etf.rma.rma2022v.data.MovieRepository
import ba.unsa.etf.rma.rma2022v.data.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ba.unsa.etf.rma.rma2022v.data.Result


class MovieDetailViewModel(private val movieRetrieved: ((movie: Movie) -> Unit)?) {
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun getMovieByTitle(name:String): Movie {
        var movies: ArrayList<Movie> = arrayListOf()
        movies.addAll(MovieRepository.getRecentMovies())
        movies.addAll(MovieRepository.getFavoriteMovies())
        val movie= movies.find { movie -> name.equals(movie.title) }
        //ako film ne postoji vratimo testni
        return movie?: Movie(0,"Test","Test","Test","Test","Test", null)
    }
    fun getMovieDetails(id: Long) {
        val movies:ArrayList<Movie> = arrayListOf()
        movies.addAll(MovieRepository.getFavoriteMovies())
        movies.addAll(MovieRepository.getRecentMovies())

        val movie = movies.find{movie -> movie.id == id}
        if (movie != null)
            movieRetrieved?.invoke(movie)
        else {
            scope.launch{
                // Vrši se poziv servisa i suspendira se rutina dok se `withContext` ne završi
                val result = MovieRepository.movieDetailsRequest(id)
                // Prikaže se rezultat korisniku na glavnoj niti
                when (result) {
                    is Result.Success<Movie> -> movieRetrieved?.invoke(result.data)
                    else-> Log.i("error","error")
                }
            }
        }
    }
    fun getActorsByTitle(actorName: String): List<String> {
        return ActorMovieRepository.getActorMovies()?.get(actorName)?: emptyList()
    }
    fun getSimilarMoviesByTitle(movieName: String): List<String> {
        return MovieRepository.getSimilarMovies()?.get(movieName)?: emptyList()
    }
}