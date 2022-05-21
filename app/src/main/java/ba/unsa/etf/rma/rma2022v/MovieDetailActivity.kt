package ba.unsa.etf.rma.rma2022v

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ba.unsa.etf.rma.rma2022v.data.Movie
import ba.unsa.etf.rma.rma2022v.view.ActorsFragment
import ba.unsa.etf.rma.rma2022v.view.SearchFragment.Companion.newInstance
import ba.unsa.etf.rma.rma2022v.viewmodel.MovieDetailViewModel
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView


class MovieDetailActivity : AppCompatActivity() {
    private var movieDetailViewModel =  MovieDetailViewModel(this@MovieDetailActivity::populateDetails, null, null)
    private var movie = Movie(0,"Test","Test","Test","Test","Test","Test")

    private lateinit var title : TextView
    private lateinit var overview : TextView
    private lateinit var releaseDate : TextView
    private lateinit var genre : TextView
    private lateinit var website : TextView
    private lateinit var poster : ImageView

    private lateinit var bottomNav: BottomNavigationView

    private val posterPath = "https://image.tmdb.org/t/p/w342"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        title = findViewById(R.id.movie_title)
        overview = findViewById(R.id.movie_overview)
        releaseDate = findViewById(R.id.movie_release_date)
        genre = findViewById(R.id.movie_genre)
        poster = findViewById(R.id.movie_poster)
        website = findViewById(R.id.movie_website)

        val extras = intent.extras
        if (extras != null) {
            if (extras.containsKey("movie_title")) {
                movie = movieDetailViewModel.getMovieByTitle(extras.getString("movie_title", ""))
                populateDetails(movie)
            }
            else if (extras.containsKey("movie_id")){
                movieDetailViewModel.getMovieDetails(extras.getLong("movie_id"))
            }
        } else {
            finish()
        }
        website.setOnClickListener{
            showWebsite()
        }
        title.setOnClickListener {
            searchTrailer()
        }

        bottomNav = findViewById(R.id.detail_navigation)
        bottomNav.setOnItemSelectedListener OnItemSelectedListener@{ item ->
            when (item.itemId) {
                R.id.navigation_actors -> {
                    val actorsFragment = ActorsFragment(movie.title, movie.id)
                    openFragment(actorsFragment)
                    return@OnItemSelectedListener true
                }
                R.id.navigation_similar -> {
                    val actorsFragment = SimilarMoviesFragment(movie.title, movie.id)
                    openFragment(actorsFragment)
                    return@OnItemSelectedListener true
                }
            }
            false
        }

        bottomNav.selectedItemId = R.id.navigation_actors
    }

    private fun searchTrailer() {
        val youtubeIntent: Intent = Intent(Intent.ACTION_SEARCH).apply {
            setPackage("com.google.android.youtube")
            putExtra("query", movie.title + " trailer")
        }
        try {
            startActivity(youtubeIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this,"Nema YouTube aplikacije",Toast.LENGTH_LONG).show()
        }

    }

    private fun populateDetails(movie: Movie) {
        this.movie = movie

        title.text = movie.title
        overview.text = movie.overview
        releaseDate.text = movie.releaseDate
        genre.text = movie.genre
        website.text = movie.homepage

        val posterContext = poster.context
        var id: Int = posterContext.resources.getIdentifier(movie.genre, "drawable", posterContext.packageName)
        if (id == 0) id = posterContext.resources
            .getIdentifier("drama", "drawable", posterContext.packageName)
        poster.setImageResource(id)
        Glide.with(posterContext)
            .load(posterPath + movie.posterPath)
            .centerCrop()
            .placeholder(R.drawable.drama)
            .error(id)
            .fallback(id)
            .into(poster)
    }
    private fun showWebsite(){
        val webIntent: Intent = Uri.parse(movie.homepage).let { webpage ->
            Intent(Intent.ACTION_VIEW, webpage)
        }
        try {
            startActivity(webIntent)
        } catch (e: ActivityNotFoundException) {
            // Definisati naredbe ako ne postoji aplikacija za navedenu akciju
        }
    }
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.detail_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
