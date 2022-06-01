package ba.unsa.etf.rma.rma2022v.data

import ba.unsa.etf.rma.rma2022v.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

object MovieRepository {
    private val tmdb_api_key = BuildConfig.TMDB_API_KEY

    suspend fun searchRequest(query: String): GetMoviesResponse? {
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getSearchResults(BuildConfig.TMDB_API_KEY,query)
            val responseBody = response.body()
            return@withContext responseBody
        }
    }
    suspend fun getMovieDetails(id: Long): Movie? {
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getMovieDetails(id,BuildConfig.TMDB_API_KEY)
            val responseBody = response.body()
            return@withContext responseBody
        }
    }
    suspend fun getSimilarMovies(id: Long): GetMoviesResponse? {
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getSimilarMovies(id,BuildConfig.TMDB_API_KEY)
            val responseBody = response.body()
            return@withContext responseBody
        }
    }
    suspend fun getUpcomingMovies(
    ) : GetMoviesResponse?{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getUpcomingMovies()
            val responseBody = response.body()
            return@withContext responseBody
        }
    }


    fun getFavoriteMovies() : List<Movie> {
        return favoriteMovies()
    }
    fun getRecentMovies() : List<Movie> {
        return recentMovies()
    }
    fun getSimilarMovies(): Map<String, List<String>> {
        return similarMovies()
    }
}