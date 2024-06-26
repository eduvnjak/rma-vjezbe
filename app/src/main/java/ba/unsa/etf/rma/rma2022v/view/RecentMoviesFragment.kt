package ba.unsa.etf.rma.rma2022v.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.unsa.etf.rma.rma2022v.MovieDetailActivity
import ba.unsa.etf.rma.rma2022v.R
import ba.unsa.etf.rma.rma2022v.data.Movie
import ba.unsa.etf.rma.rma2022v.viewmodel.MovieListViewModel
import android.util.Pair as UtilPair

class RecentMoviesFragment: Fragment() {
    private lateinit var recentMovies: RecyclerView
    private lateinit var recentMoviesAdapter: MovieListAdapter
    private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieListViewModel.getUpcoming(
            onSuccess = ::onSuccess,
            onError = ::onError
        )
    }

    fun onSuccess(movies:List<Movie>){
        val toast = Toast.makeText(context, "Upcoming movies found", Toast.LENGTH_SHORT)
        toast.show()
        recentMoviesAdapter.updateMovies(movies)
    }
    fun onError() {
        val toast = Toast.makeText(context, "Search error", Toast.LENGTH_SHORT)
        toast.show()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.recents_fragment, container, false)
        recentMovies = view.findViewById(R.id.recentMovies)
        recentMovies.layoutManager = GridLayoutManager(activity, 2)
        recentMoviesAdapter = MovieListAdapter(arrayListOf()) { movie,view1,view2 -> showMovieDetails(movie,view1,view2) }
        recentMovies.adapter = recentMoviesAdapter
        context?.let { movieListViewModel= MovieListViewModel(it) }
        return view
    }

    companion object {
        fun newInstance(): RecentMoviesFragment = RecentMoviesFragment()
    }

    private fun showMovieDetails(movie: Movie, view1: View, view2: View) {
        val intent = Intent(activity, MovieDetailActivity::class.java).apply {
            putExtra("movie_id", movie.id)
        }

        val options = ActivityOptions.makeSceneTransitionAnimation(activity, UtilPair.create(view1,"poster"),
            UtilPair.create(view2,"title"))

        startActivity(intent,options.toBundle())
    }
}
