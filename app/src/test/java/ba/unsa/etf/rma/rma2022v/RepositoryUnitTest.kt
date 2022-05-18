package ba.unsa.etf.rma.rma2022v

import ba.unsa.etf.rma.rma2022v.data.Movie
import ba.unsa.etf.rma.rma2022v.data.MovieRepository
import org.hamcrest.Matchers.hasItem
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Assert.assertEquals


class RepositoryUnitTest {
    @Test
    fun testGetFavoriteMovies(){
        val movies = MovieRepository.getFavoriteMovies()
        assertEquals(movies.size,6)
        assertThat(movies, hasItem<Movie>(hasProperty("title", Is("Pulp Fiction"))))
        assertThat(movies, not(hasItem<Movie>(hasProperty("title", Is("Black Widow")))))
    }
}