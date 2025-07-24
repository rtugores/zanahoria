package huitca1212.cuantotemide.solution.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import huitca1212.cuantotemide.R
import huitca1212.cuantotemide.StringResourceProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

const val FINAL_SIZE_ARG = "FINAL_SIZE_ARG"

@HiltViewModel
internal class SolutionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val stringResourceProvider: StringResourceProvider,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SolutionUiState())
    val uiState: StateFlow<SolutionUiState> = _uiState.asStateFlow()

    init {
        val sizeString = savedStateHandle.get<String>(FINAL_SIZE_ARG).orEmpty()
        val size = sizeString.toFloatOrNull()

        if (size != null) {
            processSolutionForSize(size)
        } else {
            _uiState.update {
                it.copy(
                    displayText = "Error: Size not available.",
                )
            }
        }
    }

    private fun processSolutionForSize(size: Float) {
        val displayFormattedText = stringResourceProvider.get(R.string.solution_size_text, size)
        val shareSubject = stringResourceProvider.get(R.string.share_result_subject)
        val shareText = stringResourceProvider.get(R.string.share_result_text, size)

        if (size >= 13.58) {
            _uiState.update {
                it.copy(
                    displayText = displayFormattedText,
                    imageResId = R.drawable.solution_bigger,
                    solutionTextResId = R.string.solution_bigger_text,
                    soundResId = R.raw.applause,
                    shareSubject = shareSubject,
                    shareText = shareText,
                    triggerSoundEffect = SingleEvent(R.raw.applause),
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    displayText = displayFormattedText,
                    imageResId = R.drawable.solution_smaller,
                    solutionTextResId = R.string.solution_smaller_text,
                    soundResId = R.raw.boo,
                    shareSubject = shareSubject,
                    shareText = shareText,
                    triggerSoundEffect = SingleEvent(R.raw.boo),
                )
            }
        }
    }
}
