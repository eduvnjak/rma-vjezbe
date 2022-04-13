package ba.unsa.etf.rma.rma2022v

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ActorsFragment(movieName:String) : Fragment() {
    private var movieName:String = movieName
    private var movieDetailViewModel =  MovieDetailViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.actors_fragment, container, false)
        var actorsList = movieDetailViewModel.getActorsByTitle(movieName)
        var actorsRV = view.findViewById<RecyclerView>(R.id.list_actors)
        actorsRV.layoutManager = LinearLayoutManager(activity)
        var actorsRVSimpleAdapter = SimpleStringAdapter(actorsList)
        actorsRV.adapter = actorsRVSimpleAdapter
        return view
    }
}