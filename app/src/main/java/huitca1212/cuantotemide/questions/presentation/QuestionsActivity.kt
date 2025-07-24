package huitca1212.cuantotemide.questions.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.ads.AdRequest
import dagger.hilt.android.AndroidEntryPoint
import huitca1212.cuantotemide.BaseActivity
import huitca1212.cuantotemide.R
import huitca1212.cuantotemide.databinding.ActivityQuestionsBinding
import huitca1212.cuantotemide.solution.presentation.SolutionActivity
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuestionsActivity : BaseActivity() {

    private lateinit var binding: ActivityQuestionsBinding

    private val viewModel: QuestionsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        binding = ActivityQuestionsBinding.bind(findViewById(R.id.questionsMainContainer))
        setSupportActionBar(binding.questionsAppTopBarLayout.appTopBar)

        val countrySize = intent.extras?.getFloat(COUNTRY_SIZE_ARG) ?: 0f
        val userName = intent.extras?.getString(USER_NAME_ARG).orEmpty()
        viewModel.initialize(countrySize, userName)

        binding.homeButton.setOnClickListener { finish() }
        binding.nextButton.setOnClickListener { onNextButtonClicked() }

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                val data = state.questionData
                if (data.questionTextRes == 0) {
                    SolutionActivity.Companion.startActivity(
                        this@QuestionsActivity,
                        state.size.toString()
                    )
                    finish()
                } else {
                    binding.questionTextView.setText(data.questionTextRes)
                    binding.firstOption.setText(data.firstOptionTextRes)
                    binding.secondOption.setText(data.secondOptionTextRes)
                    binding.thirdOption.setText(data.thirdOptionTextRes)
                    binding.firstOption.isChecked = true
                }
            }
        }

        loadAds()
    }

    private fun loadAds() {
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    private fun onNextButtonClicked() {
        val selectedOption = when {
            binding.firstOption.isChecked -> 0
            binding.secondOption.isChecked -> 1
            binding.thirdOption.isChecked -> 2
            else -> 0
        }
        viewModel.onNextButtonClicked(selectedOption)
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
