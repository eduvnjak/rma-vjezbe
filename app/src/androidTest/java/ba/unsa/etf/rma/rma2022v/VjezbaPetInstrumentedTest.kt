package ba.unsa.etf.rma.rma2022v

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VjezbaPetInstrumentedTest {

    @Test
    fun prviTest(){

        val pokreniDetalje = Intent(ApplicationProvider.getApplicationContext(),MovieDetailActivity::class.java)
        pokreniDetalje.putExtra("movie_title","Pride and prejudice")
        launchActivity<MovieDetailActivity>(pokreniDetalje)
        val actors = MovieDetailViewModel().getActorsByTitle("Pride and prejudice")
        for(actor in actors){
            onView(withId(R.id.list_actors)).check(matches(isDisplayed()))
        }
    }

}