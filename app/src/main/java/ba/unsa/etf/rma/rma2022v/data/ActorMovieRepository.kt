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

object ActorMovieRepository {
    private val tmdb_api_key = BuildConfig.TMDB_API_KEY

    fun getActorMovies():Map<String,List<String>>{
        return movieActors()
    }
    suspend fun getMovieActors(id: Long): GetCreditsResponse? {
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getMovieActors(id,BuildConfig.TMDB_API_KEY)
            val responseBody = response.body()
            return@withContext responseBody
        }
    }
}