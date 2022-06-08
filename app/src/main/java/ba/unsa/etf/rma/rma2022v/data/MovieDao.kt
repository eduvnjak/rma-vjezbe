package ba.unsa.etf.rma.rma2022v.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>
    @Insert
    suspend fun insertAll(vararg movies: Movie)
}