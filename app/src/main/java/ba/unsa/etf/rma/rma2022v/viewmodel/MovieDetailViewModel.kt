package ba.unsa.etf.rma.rma2022v.viewmodel

import android.util.Log
import ba.unsa.etf.rma.rma2022v.data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MovieDetailViewModel(private val movieRetrieved: ((movie: Movie) -> Unit)?,
                           private val moviesRetrieved: ((movies: List<String>) -> Unit)?,
                           private val actorsRetrieved: ((actors: List<String>) -> Unit)?) {
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
                val result = MovieRepository.getMovieDetails(id)
                // Prikaže se rezultat korisniku na glavnoj niti
                when (result) {
                    is Movie -> movieRetrieved?.invoke(result)
                    else-> Log.i("error","error")
                }
            }
        }
    }
    fun getSimilarMoviesById(id: Long){
        scope.launch{
            // Vrši se poziv servisa i suspendira se rutina dok se `withContext` ne završi
            val result = MovieRepository.getSimilarMovies(id)
            // Prikaže se rezultat korisniku na glavnoj niti
            when (result) {
                is GetMoviesResponse -> moviesRetrieved?.invoke(result.movies.map { movie -> movie.title })
                else-> Log.i("error","similarMovies")
            }
        }
    }
    fun getActorsById(id: Long) {
        scope.launch{
            // Vrši se poziv servisa i suspendira se rutina dok se `withContext` ne završi
            val result = ActorMovieRepository.getMovieActors(id)
            // Prikaže se rezultat korisniku na glavnoj niti
            when (result) {
                is GetCreditsResponse -> actorsRetrieved?.invoke(result.actors)
                else-> Log.i("error","actors")
            }
        }
    }
    fun getMovieById(id: Long): Movie {
        var movie = Movie(0,"test","test","test","test","test",null)
        scope.launch{
            // Vrši se poziv servisa i suspendira se rutina dok se `withContext` ne završi
            // Prikaže se rezultat korisniku na glavnoj niti
            val result = MovieRepository.getMovieDetails(id)
            if (result != null)
                movie = result
        }
        return movie
    }
    fun getActorsByTitle(actorName: String): List<String> {
        return ActorMovieRepository.getActorMovies()?.get(actorName)?: emptyList()
    }
    fun getSimilarMoviesByTitle(movieName: String): List<String> {
        return MovieRepository.getSimilarMovies()?.get(movieName)?: emptyList()
    }
}