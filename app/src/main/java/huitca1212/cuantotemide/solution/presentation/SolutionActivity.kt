package huitca1212.cuantotemide.solution.presentation

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import huitca1212.cuantotemide.BaseActivity
import huitca1212.cuantotemide.R
import huitca1212.cuantotemide.databinding.ActivitySolutionBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class SolutionActivity : BaseActivity() {

    private lateinit var binding: ActivitySolutionBinding
    private val viewModel: SolutionViewModel by viewModels() // Use Hilt to inject ViewModel
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solution)
        binding = ActivitySolutionBinding.bind(findViewById(R.id.solutionMainContainer))

        setSupportActionBar(binding.solutionAppTopBarLayout.appTopBar)

        binding.homeButton.setOnClickListener { onHomeButtonClicked() }
        binding.shareButton.setOnClickListener { onShareButtonClicked() }

        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.sizeText.text = uiState.displayText
                    uiState.imageResId?.let { binding.finalPhoto.setImageResource(it) }
                    uiState.solutionTextResId?.let { binding.solutionText.setText(it) }

                    uiState.triggerSoundEffect?.getContentIfNotHandled()?.let { soundResId ->
                        playSoundEffect(soundResId)
                    }
                }
            }
        }
    }

    private fun playSoundEffect(soundResId: Int) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(applicationContext, soundResId)?.apply {
            setOnCompletionListener { mp -> mp.release() }
            start()
        }
    }

    private fun onHomeButtonClicked() {
        finish()
    }

    private fun onShareButtonClicked() {
        val currentUiState = viewModel.uiState.value
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = SHARE_TYPE
            putExtra(Intent.EXTRA_SUBJECT, currentUiState.shareSubject)
            putExtra(Intent.EXTRA_TEXT, currentUiState.shareText)
        }
        startActivity(Intent.createChooser(intent, getString(R.string.share_chooser)))
    }

    override fun onDestroy() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }

    companion object {

        private const val SHARE_TYPE = "text/plain"

        fun startActivity(
            activity: Activity,
            finalSize: String?
        ) {
            val intent = Intent(activity, SolutionActivity::class.java)
            intent.putExtra(FINAL_SIZE_ARG, finalSize)
            activity.startActivity(intent)
        }
    }
}
