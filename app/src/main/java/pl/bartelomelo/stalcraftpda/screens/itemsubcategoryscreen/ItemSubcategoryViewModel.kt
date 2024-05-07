package pl.bartelomelo.stalcraftpda.screens.itemsubcategoryscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.bartelomelo.stalcraftpda.data.remote.repositories.DbRepository
import pl.bartelomelo.stalcraftpda.data.remote.responses.ItemList
import pl.bartelomelo.stalcraftpda.data.remote.responses.ItemListItem
import pl.bartelomelo.stalcraftpda.util.Resource
import pl.bartelomelo.stalcraftpda.util.armorDeviceInventory
import pl.bartelomelo.stalcraftpda.util.assultRiffleInventory
import pl.bartelomelo.stalcraftpda.util.barrelInventory
import pl.bartelomelo.stalcraftpda.util.bioArtefactInventory
import pl.bartelomelo.stalcraftpda.util.clothesInventory
import pl.bartelomelo.stalcraftpda.util.collimatorInventory
import pl.bartelomelo.stalcraftpda.util.combatInventory
import pl.bartelomelo.stalcraftpda.util.combinedInventory
import pl.bartelomelo.stalcraftpda.util.electroArtefactInventory
import pl.bartelomelo.stalcraftpda.util.forendInventory
import pl.bartelomelo.stalcraftpda.util.gravityArtefactInventory
import pl.bartelomelo.stalcraftpda.util.heavyInventory
import pl.bartelomelo.stalcraftpda.util.machineGunInventory
import pl.bartelomelo.stalcraftpda.util.magazineInventory
import pl.bartelomelo.stalcraftpda.util.meleeInventory
import pl.bartelomelo.stalcraftpda.util.otherAttachmentInventory
import pl.bartelomelo.stalcraftpda.util.pistolHandleInventory
import pl.bartelomelo.stalcraftpda.util.pistolInventory
import pl.bartelomelo.stalcraftpda.util.scientistInventory
import pl.bartelomelo.stalcraftpda.util.shotgunInventory
import pl.bartelomelo.stalcraftpda.util.sniperInventory
import pl.bartelomelo.stalcraftpda.util.submachineInventory
import pl.bartelomelo.stalcraftpda.util.thermalArtefactInventory
import pl.bartelomelo.stalcraftpda.util.weaponDeviceInventory
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
                    weaponDeviceInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "scientist" -> {
                    scientistInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "biochemical" -> {
                    bioArtefactInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "electrophysical" -> {
                    electroArtefactInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "gravity" -> {
                    gravityArtefactInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "thermal" -> {
                    thermalArtefactInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "assault_rifle" -> {
                    assultRiffleInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "heavy" -> {
                    heavyInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "machine_gun" -> {
                    machineGunInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "melee" -> {
                    meleeInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "pistol" -> {
                    pistolInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "shotgun_rifle" -> {
                    shotgunInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "sniper_rifle" -> {
                    sniperInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "submachine_gun" -> {
                    submachineInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "barrel" -> {
                    barrelInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "collimator_sights" -> {
                    collimatorInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "forend" -> {
                    forendInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "mag" -> {
                    magazineInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "other" -> {
                    otherAttachmentInventory[item.name]?.let {
                        item.itemName = it
                    }
                }
                "pistol_handle" -> {
                    pistolHandleInventory[item.name]?.let {
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
                Log.d("TEST", "SUKCES!!!!!")
                return itemSubcategoryList
            }
            is Resource.Error -> {

            }
            is Resource.Loading -> {}
        }
        return itemSubcategoryList
    }
}