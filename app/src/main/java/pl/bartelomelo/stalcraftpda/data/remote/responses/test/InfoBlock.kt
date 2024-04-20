package pl.bartelomelo.stalcraftpda.data.remote.responses.test

data class InfoBlock(
    val elements: List<Element>,
    val text: Text,
    val title: Title,
    val type: String
)