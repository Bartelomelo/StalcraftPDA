package pl.bartelomelo.stalcraftpda.data.remote.responses

data class ItemListItem(
    val name: String,
    val path: String,
    val url: String,
    val download_url: String?,
    var itemName: String?
)