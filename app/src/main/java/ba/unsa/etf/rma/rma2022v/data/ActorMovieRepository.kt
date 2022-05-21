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
    suspend fun actorsRequest(id: Long): Result<List<String>> {
        return withContext(Dispatchers.IO) {
            try {
                val actors = arrayListOf<String>()
                val url1 = "https://api.themoviedb.org/3/movie/$id/credits?api_key=$tmdb_api_key" //1
                val url = URL(url1)
                (url.openConnection() as? HttpURLConnection)?.run { //3
                    val result = this.inputStream.bufferedReader().use { it.readText() } //4
                    val jo = JSONObject(result)//5
                    val results = jo.getJSONArray("cast")//6
                    for (i in 0 until results.length()) {//7
                        val actor = results.getJSONObject(i)
                        val name = actor.getString("name")
                        actors.add(name)
                        if (i == 5) break
                    }
                }
                return@withContext Result.Success(actors)
            }
            catch (e: MalformedURLException) {
                return@withContext Result.Error(Exception("Cannot open HttpURLConnection"))
            } catch (e: IOException) {
                return@withContext Result.Error(Exception("Cannot read stream"))
            } catch (e: JSONException) {
                return@withContext Result.Error(Exception("Cannot parse JSON"))
            }
        }
    }
}