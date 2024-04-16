package pl.bartelomelo.stalcraftpda.screens.itemcategoryscreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.bartelomelo.stalcraftpda.data.remote.repositories.DbRepository
import pl.bartelomelo.stalcraftpda.data.remote.responses.ItemList
import pl.bartelomelo.stalcraftpda.util.Resource
import javax.inject.Inject

@HiltViewModel
class ItemCategoryViewModel @Inject constructor(
    private val repository: DbRepository
): ViewModel(){

    suspend fun getItemCategoryList(itemCategory: String): Resource<ItemList> {
        return repository.getItemCategoryList(itemCategory)
    }
}