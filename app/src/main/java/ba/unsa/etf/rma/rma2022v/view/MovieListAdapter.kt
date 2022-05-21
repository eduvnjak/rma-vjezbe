package ba.unsa.etf.rma.rma2022v.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.unsa.etf.rma.rma2022v.R
import ba.unsa.etf.rma.rma2022v.data.Movie
import com.bumptech.glide.Glide

class MovieListAdapter(
    private var movies: List<Movie>,
    private val onItemClicked: (movie: Movie, view1:View, view2:View) -> Unit
) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    private val posterPath = "https://image.tmdb.org/t/p/w342"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }
    override fun getItemCount(): Int = movies.size
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieTitle.text = movies[position].title
        val genreMatch: String? = movies[position].genre
        val context: Context = holder.movieImage.getContext()
        var id = 0
        if (genreMatch!==null)
            id = context.resources
                .getIdentifier(genreMatch, "drawable", context.packageName)
        if (id==0) id=context.resources
            .getIdentifier("drama", "drawable", context.packageName)
        Glide.with(context)
            .load(posterPath + movies[position].posterPath)
            .centerCrop()
            .placeholder(R.drawable.drama)
            .error(id)
            .fallback(id)
            .into(holder.movieImage)

//        val genreMatch: String = movies[position].genre
//        //Pronalazimo id drawable elementa na osnovu naziva žanra
//        val context: Context = holder.movieImage.getContext()
//        var id: Int = context.getResources()
//            .getIdentifier(genreMatch, "drawable", context.getPackageName())
//        if (id==0) id=context.getResources()
//            .getIdentifier("other", "drawable", context.getPackageName())
//        holder.movieImage.setImageResource(id)
        holder.itemView.setOnClickListener{ onItemClicked(movies[position],holder.movieImage,holder.movieTitle) }
    }
    fun updateMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage: ImageView = itemView.findViewById(R.id.movieImage)
        val movieTitle: TextView = itemView.findViewById(R.id.movieTitle)
    }
}