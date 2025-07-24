package huitca1212.cuantotemide.solution.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes

internal data class SolutionUiState(
    val displayText: String = "",
    @param:DrawableRes val imageResId: Int? = null,
    @param:StringRes val solutionTextResId: Int? = null,
    @param:RawRes val soundResId: Int? = null,
    val shareSubject: String = "",
    val shareText: String = "",
    val triggerSoundEffect: SingleEvent<Int>? = null
)

