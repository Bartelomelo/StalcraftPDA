package pl.bartelomelo.stalcraftpda.screens.itemdetailscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.bartelomelo.stalcraftpda.data.remote.repositories.ItemRepository
import pl.bartelomelo.stalcraftpda.data.remote.responses.test.ItemTest
import pl.bartelomelo.stalcraftpda.util.Resource
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel @Inject constructor(
    private val repository: ItemRepository
): ViewModel() {
    suspend fun getItemDetail(itemCategory: String, itemSubcategory: String?, itemId: String): Resource<ItemTest> {
        val item = repository.getItemDetail(itemCategory, itemSubcategory, itemId)
        when (item) {
            is Resource.Success -> {
                Log.d("success", "SUKCES!")
                return item
            }
            is Resource.Error -> {
                Log.d("message", item.message.toString())
            }
            else -> {}
        }
        return item
    }
}