package pl.bartelomelo.stalcraftpda.data.remote

import pl.bartelomelo.stalcraftpda.data.remote.responses.test.ItemTest
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemsApi {
    @GET("items/{category}/{subcategory}/{itemId}")
    suspend fun getItemDetail(
        @Path("category") itemCategory: String,
        @Path("subcategory") itemSubcategory: String,
        @Path("itemId") itemId: String
    ): ItemTest

    @GET("items/{category}/{itemId}")
    suspend fun getExtraItemDetail(
        @Path("category") itemCategory: String,
        @Path("itemId") itemId: String
    ): ItemTest
}