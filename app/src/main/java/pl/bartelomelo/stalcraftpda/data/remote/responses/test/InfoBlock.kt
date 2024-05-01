package pl.bartelomelo.stalcraftpda.data.remote.responses.test

data class InfoBlock(
    val elements: List<Element>,
    val text: Text,
    val title: Title,
    val type: String,
    val startDamage: String,
    val damageDecreaseStart: String,
    val endDamage: String,
    val damageDecreaseEnd: String,
    val maxDistance: String
)