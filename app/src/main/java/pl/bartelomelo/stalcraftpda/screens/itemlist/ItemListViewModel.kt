package pl.bartelomelo.stalcraftpda.screens.itemlist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.bartelomelo.stalcraftpda.data.remote.repositories.DbRepository
import pl.bartelomelo.stalcraftpda.data.remote.responses.ItemListItem
import pl.bartelomelo.stalcraftpda.util.Resource
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val repository: DbRepository
): ViewModel() {
    var itemList = mutableStateOf(listOf<ItemListItem>())

    init {
        getItemList()
    }
    fun getItemList() {
        viewModelScope.launch {
            val result = repository.getItemList()
            when(result) {
                is Resource.Success -> {
                    itemList.value = result.data!!
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        }
    }
}