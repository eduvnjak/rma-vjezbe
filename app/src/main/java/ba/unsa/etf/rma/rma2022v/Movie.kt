package ba.unsa.etf.rma.rma2022v

data class Movie (
    val id: Long,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val homepage: String,
    val genre: String
)