package pl.bartelomelo.stalcraftpda.data.remote

import pl.bartelomelo.stalcraftpda.data.remote.responses.ItemList
import pl.bartelomelo.stalcraftpda.util.Constants.API_TOKEN
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface DbApi {
    @GET("items")
    @Headers("Authorization: $API_TOKEN")
    suspend fun getItemList(): ItemList

    @GET("items/{category}")
    @Headers("Authorization: $API_TOKEN")
    suspend fun getItemCategoryList(
        @Path ("category") itemCategory: String
    ): ItemList

    @GET("items/{category}/{subcategory}")
    @Headers("Authorization: $API_TOKEN")
    suspend fun getItemSubcategoryList(
        @Path ("category") itemCategory: String,
        @Path ("subcategory") itemSubcategory: String
    ): ItemList
}