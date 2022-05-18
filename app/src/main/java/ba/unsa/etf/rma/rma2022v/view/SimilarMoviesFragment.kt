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

class SimilarFragment(movieName:String): Fragment() {
    private var movieName:String = movieName
    private var movieDetailViewModel =  MovieDetailViewModel(null)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.similar_movies_fragment, container, false)
        var movieList = movieDetailViewModel.getSimilarMoviesByTitle(movieName)
        var movieRV = view.findViewById<RecyclerView>(R.id.list_similar_movies)
        movieRV.layoutManager = LinearLayoutManager(activity)
        var actorsRVSimpleAdapter = SimpleStringAdapter(movieList)
        movieRV.adapter = actorsRVSimpleAdapter
        return view
    }
}