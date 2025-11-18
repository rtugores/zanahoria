package huitca1212.cuantotemide.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Dimensions(
    val sideMargin: Dp = 16.dp,
    val standard20sp: TextUnit = 20.sp
)

val LocalDimensions = staticCompositionLocalOf { Dimensions() }
