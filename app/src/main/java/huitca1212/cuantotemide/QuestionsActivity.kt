package huitca1212.cuantotemide

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import huitca1212.cuantotemide.databinding.ActivityQuestionsBinding

class QuestionsActivity : BaseActivity() {

    private lateinit var binding: ActivityQuestionsBinding
    private var size = 0f
    private var currentStatus = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        binding = ActivityQuestionsBinding.bind(findViewById(R.id.questionsMainContainer))
        setSupportActionBar(binding.questionsAppTopBarLayout.appTopBar)

        val countrySize = intent.extras?.getFloat(COUNTRY_SIZE_ARG) ?: 0f
        val userName = intent.extras?.getString(USER_NAME_ARG).orEmpty()

        size = getInitSize(countrySize, userName)

        binding.homeButton.setOnClickListener { onHomeButtonClicked() }
        binding.nextButton.setOnClickListener { onNextButtonClicked() }

        loadAds()
    }

    private fun loadAds() {
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    private fun onHomeButtonClicked() {
        finish()
    }

    private fun onNextButtonClicked() {
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

    private fun getInitSize(countrySize: Float, userName: String): Float {
        val userNameAmount = userName.length / 100f
        return userNameAmount + countrySize
    }

    companion object {

        private const val COUNTRY_SIZE_ARG = "COUNTRY_SIZE_ARG"
        private const val USER_NAME_ARG = "USER_NAME_ARG"
        private const val EMPTY_TEXT = ""

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
