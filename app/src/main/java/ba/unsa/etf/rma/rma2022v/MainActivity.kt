package ba.unsa.etf.rma.rma2022v

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var searchText: EditText

    private lateinit var favoriteMovies: RecyclerView
    private lateinit var recentmovies: RecyclerView

    private lateinit var favoriteMoviesAdapter: MovieListAdapter
    private lateinit var recentMoviesAdapter: MovieListAdapter

    private var movieListViewModel =  MovieListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        favoriteMovies = findViewById(R.id.favoriteMovies)
        favoriteMovies.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        favoriteMoviesAdapter = MovieListAdapter(listOf()) {movie -> showMovieDetails(movie)}
        favoriteMovies.adapter = favoriteMoviesAdapter
        favoriteMoviesAdapter.updateMovies(movieListViewModel.getFavoriteMovies())

        recentmovies = findViewById(R.id.recentMovies)
        recentmovies.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recentMoviesAdapter = MovieListAdapter(listOf()) {movie -> showMovieDetails(movie)}
        recentmovies.adapter = recentMoviesAdapter
        recentMoviesAdapter.updateMovies(movieListViewModel.getRecentMovies())

        searchText = findViewById(R.id.searchText)
        if(intent?.action == Intent.ACTION_SEND && intent?.type == "text/plain")
            handleSendText(intent)

    }

    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java).apply {
            putExtra("movie_title", movie.title)
        }
        startActivity(intent)
    }

    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            searchText.setText(it)
        }
    }
}