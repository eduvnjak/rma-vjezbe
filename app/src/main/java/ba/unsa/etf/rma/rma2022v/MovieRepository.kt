package ba.unsa.etf.rma.rma2022v

object MovieRepository {
    fun getFavoriteMovies() : List<Movie> {
        return favoriteMovies();
    }
    fun getRecentMovies() : List<Movie> {
        return recentMovies();
    }
    fun getSimilarMovies(): Map<String, List<String>> {
        return similarMovies()
    }
}