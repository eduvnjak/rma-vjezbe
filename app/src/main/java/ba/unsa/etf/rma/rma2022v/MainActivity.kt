package ba.unsa.etf.rma.rma2022v

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var favoriteMovies: RecyclerView
    private lateinit var recentmovies: RecyclerView

    private lateinit var favoriteMoviesAdapter: MovieListAdapter
    private lateinit var recentMoviesAdapter: MovieListAdapter

    private var movieListViewModel =  MovieListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.v("MOJ_DEBUG","LAYOUT")

        favoriteMovies = findViewById(R.id.favoriteMovies)
        favoriteMovies.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        favoriteMoviesAdapter = MovieListAdapter(listOf())
        favoriteMovies.adapter = favoriteMoviesAdapter
        favoriteMoviesAdapter.updateMovies(movieListViewModel.getFavoriteMovies())

        recentmovies = findViewById(R.id.recentMovies)
        recentmovies.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recentMoviesAdapter = MovieListAdapter(listOf())
        recentmovies.adapter = recentMoviesAdapter
        recentMoviesAdapter.updateMovies(movieListViewModel.getRecentMovies())
    }
}