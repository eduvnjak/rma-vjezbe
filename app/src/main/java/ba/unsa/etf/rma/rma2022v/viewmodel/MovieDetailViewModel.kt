package ba.unsa.etf.rma.rma2022v.viewmodel

import android.content.Context
import android.util.Log
import ba.unsa.etf.rma.rma2022v.data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MovieDetailViewModel(private val movieRetrieved: ((movie: Movie) -> Unit)?,
                           private val actorsRetrieved: ((actors: List<String>) -> Unit)?) {
    private val scope = CoroutineScope(Job() + Dispatchers.Main)


//    fun getMovieByTitle(name: String): Movie {
//        val movies:ArrayList<Movie> = arrayListOf()
//        movies.addAll(MovieRepository.getFavoriteMovies())
//        movies.addAll(MovieRepository.getRecentMovies())
//
//        val movie = movies.find{movie -> movie.title == name }
//        return movie?:Movie(0,"Test","Test","Test","Test",null)
//    }

    fun getMovieDetails(id: Long) {
//        val movies: ArrayList<Movie> = arrayListOf()
//        movies.addAll(MovieRepository.getFavoriteMovies())
//        movies.addAll(MovieRepository.getRecentMovies())
//
//        val movie = movies.find { movie -> movie.id == id }
//        if (movie != null)
//            movieRetrieved?.invoke(movie)
//        else {
            scope.launch {
                // Vrši se poziv servisa i suspendira se rutina dok se `withContext` ne završi
                val result = MovieRepository.getMovieDetails(id)
                // Prikaže se rezultat korisniku na glavnoj niti
                when (result) {
                    is Movie -> movieRetrieved?.invoke(result)
                    else -> Log.v("meh", "meh")
                }
            }
//        }
    }

    fun getMovieActors(id: Long) {
        scope.launch{
            // Vrši se poziv servisa i suspendira se rutina dok se `withContext` ne završi
            val result = MovieRepository.getMovieActors(id)
            // Prikaže se rezultat korisniku na glavnoj niti
            when (result) {
                is GetCreditsResponse-> actorsRetrieved?.invoke(result.actors.map { actor -> actor.name })
                else -> println(id)
            }
        }
    }

    fun getSimilarMovies(id: Long) {
        scope.launch{
            // Vrši se poziv servisa i suspendira se rutina dok se `withContext` ne završi
            val result = MovieRepository.getSimilarMovies(id)
            // Prikaže se rezultat korisniku na glavnoj niti
            when (result) {
                is GetMoviesResponse -> actorsRetrieved?.invoke(result.movies.map { movie -> movie.title })
                else -> Log.v("meh","meh")
            }
        }
    }

    fun getActorsByTitle(movieTitle: String): List<String> {
        return listOf("Leonardo DiCaprio","Keanu Reeves","Tom Cruise","Morgan Freeman","Natalie Portman","Hugo Weaving")
    }

    fun getMovieById(id: Long): Movie {
        var movie = Movie(0,"test","test","test","test",null, null)
        scope.launch{
            // Vrši se poziv servisa i suspendira se rutina dok se `withContext` ne završi
            // Prikaže se rezultat korisniku na glavnoj niti
            val result = MovieRepository.getMovieDetails(id)
            if (result != null)
                movie = result
        }
        return movie
    }

    fun writeDB(context: Context, movie:Movie, onSuccess: (movies: String) -> Unit,
                onError: () -> Unit){
        scope.launch{
            val result = MovieRepository.writeFavorite(context,movie)
            when (result) {
                is String -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }
}