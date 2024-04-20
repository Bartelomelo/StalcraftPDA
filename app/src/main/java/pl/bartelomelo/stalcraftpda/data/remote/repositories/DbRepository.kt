package pl.bartelomelo.stalcraftpda.data.remote.repositories

import android.util.Log
import dagger.hilt.android.scopes.ActivityScoped
import pl.bartelomelo.stalcraftpda.data.remote.DbApi
import pl.bartelomelo.stalcraftpda.data.remote.responses.ItemList
import pl.bartelomelo.stalcraftpda.util.Resource
import javax.inject.Inject

@ActivityScoped
class DbRepository @Inject constructor(
    private val api: DbApi
) {
    suspend fun getItemList(): Resource<ItemList> {
        val response = try {
            api.getItemList()
        } catch (e: Exception) {
            return Resource.Error("Unknown Error.")
        }
        return Resource.Success(response)
    }

    suspend fun getItemCategoryList(itemCategory: String): Resource<ItemList> {
        val response = try {
            api.getItemCategoryList(itemCategory)
        } catch (e: Exception) {
            return Resource.Error("Unknown Error.")
        }
        return Resource.Success(response)
    }

    suspend fun getItemSubcategory(itemCategory: String, itemSubCategory: String): Resource<ItemList> {
        val response = try {
            Log.d("api call", "$itemCategory/$itemSubCategory")
            api.getItemSubcategoryList(itemCategory, itemSubCategory)
        } catch (e: Exception) {
            return Resource.Error("Unknown Error.")
        }
        return Resource.Success(response)
    }

}