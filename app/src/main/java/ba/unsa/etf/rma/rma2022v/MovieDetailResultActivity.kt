package ba.unsa.etf.rma.rma2022v

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ba.unsa.etf.rma.rma2022v.data.Movie
import com.bumptech.glide.Glide

class MovieDetailResultActivity : AppCompatActivity() {
    private  var movie = Movie(0, "Test", "Test", "Test", "Test", "Test", null)
    private lateinit var title : TextView
    private lateinit var overview : TextView
    private lateinit var releaseDate : TextView
    private lateinit var genre : TextView
    private lateinit var website : TextView
    private lateinit var poster : ImageView
    private lateinit var backdrop : ImageView
    private val posterPath = "https://image.tmdb.org/t/p/w780"
    private val backdropPath = "https://image.tmdb.org/t/p/w500"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail_result)
        title = findViewById(R.id.movie_title)
        overview = findViewById(R.id.movie_overview)
        releaseDate = findViewById(R.id.movie_release_date)
        genre = findViewById(R.id.movie_genre)
        poster = findViewById(R.id.movie_poster)
        website = findViewById(R.id.movie_website)
        backdrop = findViewById(R.id.movie_backdrop)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(123)

        if(intent?.getParcelableExtra<Movie>("movie") != null) {
            movie = intent?.getParcelableExtra<Movie>("movie")!!
            populateDetails()
        }
    }
    private fun populateDetails() {
        title.text = movie.title
        releaseDate.text = movie.releaseDate
//        genre.text = movie.genre
//        website.text = movie.homepage
        website.text = "website"
        overview.text = movie.overview
        val context: Context = poster.context
        var id = 0
//        if (movie.genre != null)
//            id = context.resources
//                .getIdentifier(movie.genre, "drawable", context.packageName)
//        if (id == 0)
        id=context.resources
            .getIdentifier("picture1", "drawable", context.packageName)
        Glide.with(context)
            .load(posterPath + movie.posterPath)
            .placeholder(R.drawable.drama)
            .error(id)
            .fallback(id)
            .into(poster)
        val backdropContext: Context = backdrop.context
        Glide.with(backdropContext)
            .load(backdropPath + movie.backdropPath)
            .centerCrop()
            .placeholder(R.drawable.backdrop)
            .error(R.drawable.backdrop)
            .fallback(R.drawable.backdrop)
            .into(backdrop)
    }
}
