package huitca1212.cuantotemide.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object AppTheme {
    val dimens: Dimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current
}

@Composable
fun AppTheme(
    dimensions: Dimensions = Dimensions(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalDimensions provides dimensions) {
        MaterialTheme {
            content()
        }
    }
}
