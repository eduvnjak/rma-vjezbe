package ba.unsa.etf.rma.rma2022v

class MovieDetailViewModel {
    fun getMovieByTitle(name:String):Movie{
        var movies: ArrayList<Movie> = arrayListOf()
        movies.addAll(MovieRepository.getRecentMovies())
        movies.addAll(MovieRepository.getFavoriteMovies())
        val movie= movies.find { movie -> name.equals(movie.title) }
        //ako film ne postoji vratimo testni
        return movie?:Movie(0,"Test","Test","Test","Test","Test")
    }

    fun getActorsByTitle(actorName: String): List<String> {
        return ActorMovieRepository.getActorMovies()?.get(actorName)?: emptyList()
    }
    fun getSimilarMoviesByTitle(movieName: String): List<String> {
        return MovieRepository.getSimilarMovies()?.get(movieName)?: emptyList()
    }
}