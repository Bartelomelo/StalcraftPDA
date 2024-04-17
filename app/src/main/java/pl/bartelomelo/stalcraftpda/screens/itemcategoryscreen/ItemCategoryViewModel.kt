package pl.bartelomelo.stalcraftpda.screens.itemcategoryscreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.bartelomelo.stalcraftpda.data.remote.repositories.DbRepository
import pl.bartelomelo.stalcraftpda.data.remote.responses.ItemList
import pl.bartelomelo.stalcraftpda.data.remote.responses.ItemListItem
import pl.bartelomelo.stalcraftpda.util.Resource
import pl.bartelomelo.stalcraftpda.util.armorInventory
import pl.bartelomelo.stalcraftpda.util.artefactInventory
import pl.bartelomelo.stalcraftpda.util.attachmentInventory
import pl.bartelomelo.stalcraftpda.util.backpackInventory
import pl.bartelomelo.stalcraftpda.util.bulletInventory
import pl.bartelomelo.stalcraftpda.util.containerInventory
import pl.bartelomelo.stalcraftpda.util.grenadeInventory
import pl.bartelomelo.stalcraftpda.util.medicineInventory
import pl.bartelomelo.stalcraftpda.util.miscInventory
import pl.bartelomelo.stalcraftpda.util.weaponInventory
import javax.inject.Inject

@HiltViewModel
class ItemCategoryViewModel @Inject constructor(
    private val repository: DbRepository
): ViewModel(){

    private fun sortCategories(itemList: ArrayList<ItemListItem>): ArrayList<ItemListItem> {
        for (item in itemList) {
            when (item.path.split("/")[2]) {
                "medicine" -> {
                    medicineInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "bullet" -> {
                    bulletInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "weapon" -> {
                    weaponInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "attachment" -> {
                    attachmentInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "artefact" -> {
                    artefactInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "armor" -> {
                    armorInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "backpack" -> {
                    backpackInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "misc" -> {
                    miscInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "grenade" -> {
                    grenadeInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "containers" -> {
                    containerInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
            }
        }
        itemList.sortBy { it.itemName }
        return itemList
    }
    suspend fun getItemCategoryList(itemCategory: String): Resource<ItemList> {
        val itemCategoryList = repository.getItemCategoryList(itemCategory)
        when (itemCategoryList) {
            is Resource.Success -> {
                itemCategoryList.data?.let { sortCategories(it) }
                return itemCategoryList
            }
            is Resource.Error -> {

            }
            is Resource.Loading -> {}
        }
        return itemCategoryList
    }
}

