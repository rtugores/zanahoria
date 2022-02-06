package huitca1212.cuantotemide

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import huitca1212.cuantotemide.MainActivity.Companion.startActivity
import huitca1212.cuantotemide.databinding.ActivityQuestionsBinding
import java.util.Locale

class QuestionsActivity : BaseActivity() {

    private lateinit var binding: ActivityQuestionsBinding
    private var size = 0f
    private var currentStatus = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        binding = ActivityQuestionsBinding.bind(findViewById(R.id.questionsMainContainer))
        setSupportActionBar(binding.appTopBarLayout.appTopBar)

        binding.homeButton.setOnClickListener { onHomeButtonClicked() }
        binding.nextButton.setOnClickListener { onNextButtonClicked() }

        loadAds()
    }

    private fun loadAds() {
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    private fun onHomeButtonClicked() {
        startActivity(this@QuestionsActivity)
        finish()
    }

    private fun onNextButtonClicked() {
        size = checkSizeByCountry()
        when (currentStatus) {
            0 -> updateScreen(
                -0.5f,
                0.4f,
                0f,
                getString(R.string.second_question_title),
                getString(R.string.second_question_option_one),
                getString(R.string.second_question_option_two),
                getString(R.string.second_question_option_three)
            )
            1 -> updateScreen(
                -0.4f,
                0f,
                0.4f,
                getString(R.string.third_question_title),
                getString(R.string.third_question_option_one),
                getString(R.string.third_question_option_two),
                getString(R.string.third_question_option_three)
            )
            2 -> updateScreen(
                -0.1f,
                2f,
                0.6f,
                getString(R.string.fourth_question_title),
                getString(R.string.fourth_question_option_one),
                getString(R.string.fourth_question_option_two),
                getString(R.string.fourth_question_option_three)
            )
            3 -> updateScreen(
                -0.2f,
                -0.2f,
                0.4f,
                getString(R.string.fifth_question_title),
                getString(R.string.fifth_question_option_one),
                getString(R.string.fifth_question_option_two),
                getString(R.string.fifth_question_option_three)
            )
            4 -> updateScreen(
                0.4f,
                -0.3f,
                0.1f,
                getString(R.string.sixth_question_title),
                getString(R.string.sixth_question_option_one),
                getString(R.string.sixth_question_option_two),
                getString(R.string.sixth_question_option_three)
            )
            5 -> updateScreen(
                -0.5f,
                0f,
                -0.2f,
                getString(R.string.seventh_question_title),
                getString(R.string.seventh_question_option_one),
                getString(R.string.seventh_question_option_two),
                getString(R.string.seventh_question_option_three)
            )

            6 -> {
                updateScreen(
                    0.5f,
                    0.1f,
                    -0.3f,
                    EMPTY_TEXT,
                    EMPTY_TEXT,
                    EMPTY_TEXT,
                    EMPTY_TEXT
                )
                SolutionActivity.startActivity(this@QuestionsActivity, size.toString())
                finish()
            }
        }
    }

    private fun updateScreen(
        firstOptionVar: Float,
        secondOptionVar: Float,
        thirdPositionVar: Float,
        questionText: String,
        firstOptionText: String,
        secondOptionText: String,
        thirdOptionText: String
    ) {
        with(binding) {
            when {
                firstOption.isChecked -> size += firstOptionVar
                secondOption.isChecked -> size += secondOptionVar
                thirdOption.isChecked -> size += thirdPositionVar
            }
            if (currentStatus != 6) {
                firstOption.isChecked = true
                questionTextView.text = questionText
                firstOption.text = firstOptionText
                secondOption.text = secondOptionText
                thirdOption.text = thirdOptionText
            }
            currentStatus++
        }
    }

    private fun checkSizeByCountry(): Float {
        val country = intent.extras?.getString(COUNTRY_SELECTED_ARG)
        return country?.lowercase(Locale.getDefault())?.run {
            if (contains("argentina") ||
                contains("andorra") ||
                contains("esp") ||
                contains("chile") ||
                contains("canad") ||
                contains("belice")
            ) {
                14.18f
            } else if (contains("estados unidos")) {
                12f
            } else if (contains("bolivia") ||
                contains("colombia") ||
                contains("venezuela") ||
                contains("ecuador") ||
                contains("guinea ecuatorial")
            ) {
                17.09f
            } else {
                15.49f
            }
        } ?: 15.49f
    }

    companion object {

        private const val COUNTRY_SELECTED_ARG = "CountrySelected"
        private const val EMPTY_TEXT = ""

        fun startActivity(activity: Activity, countrySelected: String?) {
            val intent = Intent(activity, QuestionsActivity::class.java)
            Bundle().run {
                putString(COUNTRY_SELECTED_ARG, countrySelected)
                intent.putExtras(this)
            }

            activity.startActivity(intent)
        }
    }
}
