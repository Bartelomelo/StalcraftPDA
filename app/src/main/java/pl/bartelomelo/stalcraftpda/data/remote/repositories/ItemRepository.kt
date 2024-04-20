package pl.bartelomelo.stalcraftpda.data.remote.repositories

import android.util.Log
import dagger.hilt.android.scopes.ActivityScoped
import pl.bartelomelo.stalcraftpda.data.remote.ItemsApi
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.ItemTest
import pl.bartelomelo.stalcraftpda.util.Resource
import javax.inject.Inject

@ActivityScoped
class ItemRepository @Inject constructor(
    private val api: ItemsApi
) {
    suspend fun getItemDetail(itemCategory: String, itemSubcategory: String?, itemId: String): Resource<ItemTest> {
        val response: ItemTest
        when (itemSubcategory) {
            null -> {
                response = try {
                    Log.d("api call3", "$itemCategory/$itemId")
                    api.getExtraItemDetail(itemCategory, itemId)
                } catch (e: Exception) {
                    return Resource.Error(e.message.toString())
                }
            }
            else -> {
                response = try {
                    Log.d("api call2", "$itemCategory/$itemSubcategory/$itemId")
                    api.getItemDetail(itemCategory, itemSubcategory, itemId)
                } catch (e: Exception) {
                    return Resource.Error(e.message.toString())
                }
            }
        }
        return Resource.Success(response)
    }
}