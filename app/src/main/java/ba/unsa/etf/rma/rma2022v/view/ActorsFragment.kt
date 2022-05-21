package ba.unsa.etf.rma.rma2022v.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.unsa.etf.rma.rma2022v.R
import ba.unsa.etf.rma.rma2022v.viewmodel.MovieDetailViewModel

class ActorsFragment(private var movieName: String, private var movieId: Long?) : Fragment() {
    private lateinit var actorsRv: RecyclerView

    private var movieDetailViewModel =  MovieDetailViewModel(null,null, this@ActorsFragment::populateActors)
    private lateinit var actorsRVSimpleAdapter: SimpleStringAdapter

    private var actorsList = listOf<String>()

    private fun populateActors(actors: List<String>) {
        actorsList = actors
        actorsRVSimpleAdapter.list = actors
        actorsRVSimpleAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.actors_fragment, container, false)
        actorsRv = view.findViewById<RecyclerView>(R.id.list_actors)

        actorsList = movieName.let { movieDetailViewModel.getActorsByTitle(it) }
        actorsRv.layoutManager = LinearLayoutManager(activity)
        actorsRVSimpleAdapter = SimpleStringAdapter(actorsList)
        actorsRv.adapter = actorsRVSimpleAdapter

        movieId?.let{movieDetailViewModel.getActorsById(it)}
        return view
    }
}