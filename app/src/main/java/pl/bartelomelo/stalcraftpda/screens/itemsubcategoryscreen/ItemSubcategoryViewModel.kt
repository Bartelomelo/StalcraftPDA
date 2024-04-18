package pl.bartelomelo.stalcraftpda.screens.itemsubcategoryscreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.bartelomelo.stalcraftpda.data.remote.repositories.DbRepository
import pl.bartelomelo.stalcraftpda.data.remote.responses.ItemList
import pl.bartelomelo.stalcraftpda.data.remote.responses.ItemListItem
import pl.bartelomelo.stalcraftpda.util.Resource
import pl.bartelomelo.stalcraftpda.util.armorDeviceInventory
import pl.bartelomelo.stalcraftpda.util.clothesInventory
import pl.bartelomelo.stalcraftpda.util.combatInventory
import pl.bartelomelo.stalcraftpda.util.combinedInventory
import pl.bartelomelo.stalcraftpda.util.scientistInventory
import javax.inject.Inject

@HiltViewModel
class ItemSubcategoryViewModel @Inject constructor(
    private val repository: DbRepository
): ViewModel() {

    private fun sortCategories(itemList: ArrayList<ItemListItem>): ArrayList<ItemListItem> {
        for (item in itemList) {
            when (item.path.split("/")[3]) {
                "clothes" -> {
                    clothesInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "combat" -> {
                    combatInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "combined" -> {
                    combinedInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "device" -> {
                    armorDeviceInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "scientist" -> {
                    scientistInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
            }
        }
        itemList.sortBy { it.itemName }
        return itemList
    }
    suspend fun getItemSubcategoryList(itemCategory: String, itemSubcategory: String): Resource<ItemList> {
        val itemSubcategoryList = repository.getItemSubcategory(itemCategory, itemSubcategory)
        when (itemSubcategoryList) {
            is Resource.Success -> {
                itemSubcategoryList.data?.let { sortCategories(it) }
                return itemSubcategoryList
            }
            is Resource.Error -> {

            }
            is Resource.Loading -> {}
        }
        return itemSubcategoryList
    }
}