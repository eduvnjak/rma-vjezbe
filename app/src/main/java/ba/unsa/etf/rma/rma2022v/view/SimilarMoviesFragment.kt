package ba.unsa.etf.rma.rma2022v

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.unsa.etf.rma.rma2022v.view.SimpleStringAdapter
import ba.unsa.etf.rma.rma2022v.viewmodel.MovieDetailViewModel

class SimilarMoviesFragment(private val movieName: String, private val movieId: Long?): Fragment() {
    private lateinit var moviesRv: RecyclerView

    private var movieDetailViewModel =  MovieDetailViewModel(null,this@SimilarMoviesFragment::populateMovies,null)
    private lateinit var moviesRvSimpleAdapter: SimpleStringAdapter

    private var moviesList = listOf<String>()

    private fun populateMovies(movies: List<String>) {
        moviesList = movies
        moviesRvSimpleAdapter.list = movies
        moviesRvSimpleAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.similar_movies_fragment, container, false)
        moviesRv = view.findViewById<RecyclerView>(R.id.list_similar_movies)

        moviesList = movieName.let { movieDetailViewModel.getSimilarMoviesByTitle(it) }
        moviesRv.layoutManager = LinearLayoutManager(activity)
        moviesRvSimpleAdapter = SimpleStringAdapter(moviesList)
        moviesRv.adapter = moviesRvSimpleAdapter

        movieId?.let { movieDetailViewModel.getSimilarMoviesById(it)}
        return view
    }
}