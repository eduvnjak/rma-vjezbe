package ba.unsa.etf.rma.rma2022v.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Pair.create
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.unsa.etf.rma.rma2022v.MovieDetailActivity
import ba.unsa.etf.rma.rma2022v.R
import ba.unsa.etf.rma.rma2022v.data.Movie
import ba.unsa.etf.rma.rma2022v.viewmodel.MovieListViewModel
import android.util.Pair as UtilPair


class SearchFragment: Fragment() {
    private lateinit var searchText: EditText
    private lateinit var searchBtn: ImageButton
    private lateinit var searchMovies: RecyclerView
    private lateinit var searchMoviesAdapter: MovieListAdapter
    private var movieListViewModel = MovieListViewModel(this@SearchFragment::searchDone,this@SearchFragment::onError)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.search_fragment, container, false)
        searchText = view.findViewById(R.id.searchText)
        searchBtn = view.findViewById(R.id.searchButton)
        searchMovies = view.findViewById(R.id.searchMovies)
        searchMovies.layoutManager = GridLayoutManager(activity, 2)
        searchMoviesAdapter = MovieListAdapter(arrayListOf()) { movie,view1,view2 -> showMovieDetails(movie,view1,view2) }
        searchMovies.adapter = searchMoviesAdapter

        searchBtn.setOnClickListener {
            onClick()
        }

        arguments?.getString("search")?.let {
            searchText.setText(it)
        }
        return view;
    }

    private fun showMovieDetails(movie: Movie, view1: View, view2: View) {
        val intent = Intent(activity, MovieDetailActivity::class.java).apply {
            putExtra("movie_id", movie.id)
        }

        val options = ActivityOptions.makeSceneTransitionAnimation(activity, UtilPair.create(view1,"poster"),
            UtilPair.create(view2,"title"))

        startActivity(intent,options.toBundle())
    }

    private fun onClick() {
        val toast = Toast.makeText(context,"Search start",Toast.LENGTH_SHORT)
        toast.show()
        movieListViewModel.search(searchText.text.toString())
    }

    fun searchDone(movies: List<Movie>) {
        val toast = Toast.makeText(context,"Search done",Toast.LENGTH_SHORT)
        toast.show()
        searchMoviesAdapter.updateMovies(movies)
    }

    fun onError() {
        val toast = Toast.makeText(context,"Search error",Toast.LENGTH_SHORT)
        toast.show()
    }

    companion object {
        fun newInstance(search: String): SearchFragment = SearchFragment().apply {
            arguments = Bundle().apply {
                putString("search", search)
            }
        }
    }
}