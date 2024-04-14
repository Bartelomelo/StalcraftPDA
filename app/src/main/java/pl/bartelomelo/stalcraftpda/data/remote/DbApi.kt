package pl.bartelomelo.stalcraftpda.data.remote

import pl.bartelomelo.stalcraftpda.data.remote.responses.ItemList
import retrofit2.http.GET

interface DbApi {
    @GET("items")
    suspend fun getItemList(): ItemList
}