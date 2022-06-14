package ba.unsa.etf.rma.rma2022v

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ba.unsa.etf.rma.rma2022v.data.Movie
import ba.unsa.etf.rma.rma2022v.view.AdditionalInfoFragment
import ba.unsa.etf.rma.rma2022v.viewmodel.MovieDetailViewModel
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MovieDetailActivity : AppCompatActivity() {
    private var movieDetailViewModel = MovieDetailViewModel(this@MovieDetailActivity::populateDetails,null)
    private lateinit var title: TextView
    private lateinit var overview: TextView
    private lateinit var date: TextView
    private lateinit var genre : TextView
    private lateinit var website : TextView
    private lateinit var poster : ImageView
    private lateinit var backDrop: ImageView
    private lateinit var navBar: BottomNavigationView
    private lateinit var addTOFav: FloatingActionButton
    private var movie: Movie? = null
    private val posterPath = "https://image.tmdb.org/t/p/w342"
    private val backdropPath = "https://image.tmdb.org/t/p/w500"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        title = findViewById(R.id.movie_title)
        overview = findViewById(R.id.movie_overview)
        date = findViewById(R.id.movie_release_date)
        genre = findViewById(R.id.movie_genre)
        website = findViewById(R.id.movie_website)
        poster = findViewById(R.id.movie_poster)
        backDrop = findViewById(R.id.movie_backdrop)
        navBar = findViewById(R.id.detail_navigation)
        addTOFav = findViewById(R.id.shareButton)

        val extras = intent.extras

        if (extras != null) {
            movieDetailViewModel.getMovieDetails(extras.getLong("movie_id",0L))
        }
        else {
            finish()
        }

        navBar.setOnItemSelectedListener {item ->
            when (item.itemId) {
                R.id.navigation_actors -> {
                    val actorsFragment = AdditionalInfoFragment.newInstance(movie!!.id)
                    openFragment(actorsFragment,"actors")
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_similar -> {
                    val actorsFragment = AdditionalInfoFragment.newInstance(movie!!.id)
                    openFragment(actorsFragment,"similar movies")
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }

        website.setOnClickListener {
            showWebsite()
        }

        title.setOnClickListener {
            openVideoTrailer()
        }

        addTOFav.setOnClickListener{
            writeDB()
        }
    }

    fun writeDB(){
        movieDetailViewModel.writeDB(applicationContext,this.movie!!,onSuccess = ::onSuccess1,
            onError = ::onError)
    }
    fun onSuccess1(message:String){
        val toast = Toast.makeText(applicationContext, "Spaseno", Toast.LENGTH_SHORT)
        toast.show()
        addTOFav.visibility = View.GONE
    }
    fun onError() {
        val toast = Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun openFragment(fragment: Fragment,tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        val naStacku = supportFragmentManager.findFragmentByTag(tag)
        if (naStacku != null)
            transaction.replace(R.id.detail_container,naStacku,tag)
        else {
            transaction.replace(R.id.detail_container,fragment,tag)
            transaction.addToBackStack(tag)
        }
        transaction.commit()
    }

    private fun openVideoTrailer() {
        if (movie != null) {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEARCH
                setPackage("com.google.android.youtube")
                putExtra("query", movie!!.title + " trailer")
            }
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(sendIntent)
            }
        }
    }

    private fun showWebsite() {
        if (movie != null) {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(movie!!.homepage)
            }
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(sendIntent)
            }
        }
    }

    private fun populateDetails(movie: Movie) {
        this.movie = movie
        title.text = movie.title
        overview.text = movie.overview
        date.text = movie.releaseDate
//        genre.text = movie.genre
        if (movie.homepage != null)
            website.text = movie.homepage
        else website.text = "website"

        val posterContext = poster.context
//        var id: Int = posterContext.resources.getIdentifier(movie.genre, "drawable", posterContext.packageName)
        var id: Int = posterContext.resources.getIdentifier("movie", "drawable", posterContext.packageName)
        if (id == 0) id = posterContext.resources
            .getIdentifier("movie", "drawable", posterContext.packageName)
        poster.setImageResource(id)
        Glide.with(posterContext)
            .load(posterPath + movie.posterPath)
            .placeholder(R.drawable.drama)
            .error(id)
            .fallback(id)
            .into(poster)

        val backdropContext = backDrop.context
        Glide.with(backdropContext)
            .load(backdropPath + movie.backdropPath)
            .centerCrop()
            .placeholder(R.drawable.backdrop)
            .error(R.drawable.backdrop)
            .fallback(R.drawable.backdrop)
            .into(backDrop)
    }
}