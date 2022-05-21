package ba.unsa.etf.rma.rma2022v.data

data class Movie (
    var id: Long,
    var title: String,
    var overview: String,
    var releaseDate: String,
    var homepage: String?,
    var genre: String?,
    var posterPath: String?,
)