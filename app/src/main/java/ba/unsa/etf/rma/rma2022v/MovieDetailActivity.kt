package ba.unsa.etf.rma.rma2022v

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast


class MovieDetailActivity : AppCompatActivity() {
    private var movieDetailViewModel =  MovieDetailViewModel()
    private lateinit var movie: Movie
    private lateinit var title : TextView
    private lateinit var overview : TextView
    private lateinit var releaseDate : TextView
    private lateinit var genre : TextView
    private lateinit var website : TextView
    private lateinit var poster : ImageView

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
            movie = movieDetailViewModel.getMovieByTitle(extras.getString("movie_title",""))
            populateDetails()
        } else {
            finish()
        }
        website.setOnClickListener{
            showWebsite()
        }
        title.setOnClickListener {
            searchTrailer()
        }
    }

    private fun searchTrailer() {
        val youtubeIntent: Intent = Intent(Intent.ACTION_SEARCH).apply {
            setPackage("com.google.android.youtube")
            putExtra("query", movie.title + " trailer")
        }

        /*
        if (youtubeIntent.resolveActivity(packageManager) != null) {
            startActivity(youtubeIntent)
        } else {
            Toast.makeText(this,"Nema YouTube aplikacije",Toast.LENGTH_LONG).show()
        }
        */
        try {
            startActivity(youtubeIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this,"Nema YouTube aplikacije",Toast.LENGTH_LONG).show()
        }

    }

    private fun populateDetails() {
        title.text=movie.title
        releaseDate.text=movie.releaseDate
        genre.text=movie.genre
        website.text=movie.homepage
        overview.text=movie.overview
        val context: Context = poster.context
        var id: Int = context.resources
            .getIdentifier(movie.genre, "drawable", context.packageName)
        if (id===0) id=context.resources
            .getIdentifier("other", "drawable", context.packageName)
        poster.setImageResource(id)
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
}
