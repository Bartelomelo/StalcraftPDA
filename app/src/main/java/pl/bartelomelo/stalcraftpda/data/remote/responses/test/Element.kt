package pl.bartelomelo.stalcraftpda.data.remote.responses.test

data class Element(
    val formatted: Formatted,
    val key: Key,
    val name: Name,
    val type: String,
    val value: Any,
    val text: Text
)