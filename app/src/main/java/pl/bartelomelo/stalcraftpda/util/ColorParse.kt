package pl.bartelomelo.stalcraftpda.util

import androidx.compose.ui.graphics.Color
import pl.bartelomelo.stalcraftpda.ui.theme.LettersGray
import pl.bartelomelo.stalcraftpda.ui.theme.RankBlue
import pl.bartelomelo.stalcraftpda.ui.theme.RankGreen
import pl.bartelomelo.stalcraftpda.ui.theme.RankPink
import pl.bartelomelo.stalcraftpda.ui.theme.RankRed

fun parseTypeToColor(type: String): Color {
    return when(type) {
        "RANK_MASTER" -> RankRed
        "RANK_NEWBIE" -> RankGreen
        "DEFAULT" -> LettersGray
        "RANK_STALKER" -> RankBlue
        "RANK_VETERAN" -> RankPink
        else -> LettersGray
    }
}