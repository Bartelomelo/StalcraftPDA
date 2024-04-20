package pl.bartelomelo.stalcraftpda.data.remote.responses.test

data class ItemTest(
    val category: String,
    val color: String,
    val id: String,
    val infoBlocks: List<InfoBlock>,
    val name: Name,
    val status: Status
)