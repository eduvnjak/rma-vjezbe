package ba.unsa.etf.rma.rma2022v

object ActorMovieRepository {
    fun getActorMovies():Map<String,List<String>>{
        return movieActors()
    }
}