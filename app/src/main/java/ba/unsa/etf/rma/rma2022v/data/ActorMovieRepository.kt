package ba.unsa.etf.rma.rma2022v.data

object ActorMovieRepository {
    fun getActorMovies():Map<String,List<String>>{
        return movieActors()
    }
}