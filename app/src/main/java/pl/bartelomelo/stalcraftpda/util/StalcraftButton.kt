package pl.bartelomelo.stalcraftpda.util

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class StalcraftButton(): Shape
{
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val rightCorner = with(density) { 35.dp.toPx() }
        val rightIndentation = with(density) { 320.dp.toPx() }
        val secondRightIndentation = with(density) { 310.dp.toPx() }
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width, rightCorner)
            lineTo(rightIndentation, rightCorner)
            lineTo(secondRightIndentation, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}