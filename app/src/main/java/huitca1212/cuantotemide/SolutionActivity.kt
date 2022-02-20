package huitca1212.cuantotemide

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import huitca1212.cuantotemide.databinding.ActivitySolutionBinding

class SolutionActivity : BaseActivity() {

    private lateinit var binding: ActivitySolutionBinding
    private var sizeShare: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solution)
        binding = ActivitySolutionBinding.bind(findViewById(R.id.solutionMainContainer))
        setSupportActionBar(binding.solutionAppTopBarLayout.appTopBar)

        binding.homeButton.setOnClickListener { onHomeButtonClicked() }
        binding.shareButton.setOnClickListener { onShareButtonClicked() }

        sizeShare = intent.extras?.getString(FINAL_SIZE_ARG)?.toFloat()
        sizeShare?.let {
            binding.sizeText.text = String.format(getString(R.string.solution_size_text), it)
            if (it >= 13.79) {
                setSolutionParameters(R.drawable.solution_bigger, R.string.solution_bigger_text, R.raw.applause)
            } else {
                setSolutionParameters(R.drawable.solution_smaller, R.string.solution_smaller_text, R.raw.boo)
            }
        }
    }

    private fun setSolutionParameters(solutionDrawableId: Int, solutionTextId: Int, solutionRawId: Int) {
        binding.finalPhoto.setImageResource(solutionDrawableId)
        binding.solutionText.setText(solutionTextId)
        MediaPlayer
            .create(applicationContext, solutionRawId)
            .start()
    }

    private fun onHomeButtonClicked() {
        finish()
    }

    private fun onShareButtonClicked() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = SHARE_TYPE
            addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_result_subject))
            putExtra(Intent.EXTRA_TEXT, getString(R.string.share_result_text, sizeShare))
        }

        startActivity(Intent.createChooser(intent, getString(R.string.share_chooser)))
    }

    companion object {

        private const val FINAL_SIZE_ARG = "FINAL_SIZE_ARG"

        fun startActivity(activity: Activity, countrySelected: String?) {
            val intent = Intent(activity, SolutionActivity::class.java)
            Bundle().run {
                putString(FINAL_SIZE_ARG, countrySelected)
                intent.putExtras(this)
            }
            activity.startActivity(intent)
        }
    }
}
