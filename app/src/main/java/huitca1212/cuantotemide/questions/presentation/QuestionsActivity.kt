package huitca1212.cuantotemide.questions.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import huitca1212.cuantotemide.solution.presentation.SolutionActivity
import huitca1212.cuantotemide.ui.theme.AppTheme

@AndroidEntryPoint
class QuestionsActivity : AppCompatActivity() {

    private val viewModel: QuestionsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val countrySize = intent.extras?.getFloat(COUNTRY_SIZE_ARG) ?: 0f
        val userName = intent.extras?.getString(USER_NAME_ARG).orEmpty()
        viewModel.initialize(countrySize, userName)

        setContent {
            AppTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                // Check if we should navigate to solution
                if (uiState.questionData.questionTextRes == 0) {
                    SolutionActivity.startActivity(
                        this@QuestionsActivity,
                        uiState.size.toString()
                    )
                    finish()
                } else {
                    QuestionsScreen(
                        uiState = uiState,
                        onNextClicked = { selectedOption ->
                            viewModel.onNextButtonClicked(selectedOption)
                        },
                        onHomeClicked = { finish() }
                    )
                }
            }
        }
    }

    companion object {

        private const val COUNTRY_SIZE_ARG = "COUNTRY_SIZE_ARG"
        private const val USER_NAME_ARG = "USER_NAME_ARG"

        fun startActivity(activity: Activity, countrySize: Float, userName: String) {
            val intent = Intent(activity, QuestionsActivity::class.java)
            Bundle().run {
                putFloat(COUNTRY_SIZE_ARG, countrySize)
                putString(USER_NAME_ARG, userName)
                intent.putExtras(this)
            }
            activity.startActivity(intent)
        }
    }
}
