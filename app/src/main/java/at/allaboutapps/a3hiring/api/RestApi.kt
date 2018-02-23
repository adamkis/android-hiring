package at.allaboutapps.a3hiring.api

import at.allaboutapps.a3hiring.api.models.Club
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by adam on 2018. 02. 23..
 */

interface RestApi {

    companion object {
        val A3_URL_BASE = "https://public.allaboutapps.at/hiring/"
    }

    @GET("clubs.json")
    fun getClubs(): Observable<Array<Club>>

}