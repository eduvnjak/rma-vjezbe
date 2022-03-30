package ba.unsa.etf.rma.rma2022v

class MovieListViewModel {
    fun getFavoriteMovies():List<Movie>{
        return MovieRepository.getFavoriteMovies();
    }
    fun getRecentMovies():List<Movie>{
        return MovieRepository.getRecentMovies();
    }
}