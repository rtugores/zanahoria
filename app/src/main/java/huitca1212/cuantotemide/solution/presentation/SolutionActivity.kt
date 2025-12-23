package huitca1212.cuantotemide.solution.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import huitca1212.cuantotemide.R
import huitca1212.cuantotemide.ui.theme.AppTheme

@AndroidEntryPoint
internal class SolutionActivity : AppCompatActivity() {

    private val viewModel: SolutionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                SolutionScreen(
                    viewModel = viewModel,
                    onHomeButtonClicked = ::onHomeButtonClicked,
                    onShareButtonClicked = ::onShareButtonClicked
                )
            }
        }
    }

    private fun onHomeButtonClicked() {
        finish()
    }

    private fun onShareButtonClicked() {
        val currentUiState = viewModel.uiState.value
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = SHARE_TYPE
            addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
            putExtra(Intent.EXTRA_SUBJECT, currentUiState.shareSubject)
            putExtra(Intent.EXTRA_TEXT, currentUiState.shareText)
        }
        startActivity(Intent.createChooser(intent, getString(R.string.share_chooser)))
    }

    companion object {

        private const val SHARE_TYPE = "text/plain"

        fun startActivity(
            activity: Activity,
            finalSize: Float
        ) {
            val intent = Intent(activity, SolutionActivity::class.java)
            intent.putExtra(FINAL_SIZE_ARG, finalSize)
            activity.startActivity(intent)
        }
    }
}
