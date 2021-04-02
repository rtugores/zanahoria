package huitca1212.cuantotemide

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import huitca1212.cuantotemide.MainActivity.Companion.startActivity
import huitca1212.cuantotemide.databinding.ActivitySolutionBinding
import java.util.Locale

class SolutionActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySolutionBinding
    private var sizeShare: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solution)
        binding = ActivitySolutionBinding.bind(findViewById(R.id.solutionMainContainer))
        setSupportActionBar(binding.appTopBarLayout.appTopBar)

        binding.homeButton.setOnClickListener(this)
        binding.shareButton.setOnClickListener(this)
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
        val mpRes: MediaPlayer = MediaPlayer.create(applicationContext, solutionRawId)
        mpRes.start()
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.homeButton) {
            onHomeButtonClicked()
        } else if (id == R.id.share_button) {
            onShareButtonClicked()
        }
    }

    private fun onHomeButtonClicked() {
        startActivity(this@SolutionActivity)
        finish()
    }

    private fun onShareButtonClicked() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
            } else {
                addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
            }
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject))
            putExtra(Intent.EXTRA_TEXT, String.format(Locale.getDefault(), getString(R.string.share_text), sizeShare))
        }

        startActivity(Intent.createChooser(intent, getString(R.string.share_chooser)))
    }

    companion object {

        private const val FINAL_SIZE_ARG = "FinalSizeArg"

        fun startActivity(activity: Activity, countrySelected: String?) {
            val intent = Intent(activity, SolutionActivity::class.java)
            val b = Bundle()
            b.putString(FINAL_SIZE_ARG, countrySelected)
            intent.putExtras(b)
            activity.startActivity(intent)
        }
    }
}
