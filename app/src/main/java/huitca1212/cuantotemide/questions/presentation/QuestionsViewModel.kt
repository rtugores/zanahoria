package huitca1212.cuantotemide.questions.presentation

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import huitca1212.cuantotemide.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class QuestionsUiState(
    val size: Float = 0f,
    val questionIndex: Int = 0,
    val questionData: QuestionsViewModel.QuestionData,
)

@HiltViewModel
class QuestionsViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(
        QuestionsUiState(
            size = 0f,
            questionIndex = 0,
            questionData = getQuestionData(0)
        )
    )
    val uiState: StateFlow<QuestionsUiState> = _uiState.asStateFlow()

    fun initialize(countrySize: Float, userName: String) {
        _uiState.value = QuestionsUiState(
            size = getInitialSize(countrySize, userName),
            questionIndex = 0,
            questionData = getQuestionData(0)
        )
    }

    fun onNextButtonClicked(selectedOption: Int) {
        val state = _uiState.value
        val questionData = getQuestionData(state.questionIndex)
        val delta = when (selectedOption) {
            0 -> questionData.firstOptionDelta
            1 -> questionData.secondOptionDelta
            2 -> questionData.thirdOptionDelta
            else -> 0f
        }
        val newSize = state.size + delta
        val nextQuestionIndex = state.questionIndex + 1
        _uiState.value = state.copy(
            size = newSize,
            questionIndex = nextQuestionIndex,
            questionData = getQuestionData(nextQuestionIndex)
        )
    }

    private fun getInitialSize(countrySize: Float, userName: String): Float {
        val userNameAmount = userName.length / 100f
        return userNameAmount + countrySize
    }

    private fun getQuestionData(questionIndex: Int): QuestionData {
        return when (questionIndex) {
            0 -> QuestionData(
                -0.5f,
                0.4f,
                0f,
                R.string.second_question_title,
                R.string.second_question_option_one,
                R.string.second_question_option_two,
                R.string.second_question_option_three
            )

            1 -> QuestionData(
                -0.4f,
                0f,
                0.4f,
                R.string.third_question_title,
                R.string.third_question_option_one,
                R.string.third_question_option_two,
                R.string.third_question_option_three
            )

            2 -> QuestionData(
                -0.1f,
                2f,
                0.6f,
                R.string.fourth_question_title,
                R.string.fourth_question_option_one,
                R.string.fourth_question_option_two,
                R.string.fourth_question_option_three
            )

            3 -> QuestionData(
                -0.2f,
                -0.2f,
                0.4f,
                R.string.fifth_question_title,
                R.string.fifth_question_option_one,
                R.string.fifth_question_option_two,
                R.string.fifth_question_option_three
            )

            4 -> QuestionData(
                0.4f,
                -0.3f,
                0.1f,
                R.string.sixth_question_title,
                R.string.sixth_question_option_one,
                R.string.sixth_question_option_two,
                R.string.sixth_question_option_three
            )

            5 -> QuestionData(
                -0.5f,
                0f,
                -0.2f,
                R.string.seventh_question_title,
                R.string.seventh_question_option_one,
                R.string.seventh_question_option_two,
                R.string.seventh_question_option_three
            )

            else -> QuestionData(
                0.5f,
                0.1f,
                -0.3f,
                0,
                0,
                0,
                0
            )
        }
    }

    data class QuestionData(
        val firstOptionDelta: Float,
        val secondOptionDelta: Float,
        val thirdOptionDelta: Float,
        @param:StringRes val questionTextRes: Int,
        @param:StringRes val firstOptionTextRes: Int,
        @param:StringRes val secondOptionTextRes: Int,
        @param:StringRes val thirdOptionTextRes: Int
    )
}
