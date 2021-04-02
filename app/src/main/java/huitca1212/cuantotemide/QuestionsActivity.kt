package huitca1212.cuantotemide

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import huitca1212.cuantotemide.MainActivity.Companion.startActivity
import huitca1212.cuantotemide.QuestionsActivity
import java.util.Locale

class QuestionsActivity : OptionsActivity(), View.OnClickListener {

    private var nextButton: Button? = null
    private var homeButton: Button? = null
    private var question: TextView? = null
    private var firstOption: RadioButton? = null
    private var secondOption: RadioButton? = null
    private var thirdOption: RadioButton? = null
    private var size = 0f
    private var currentStatus = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        nextButton = findViewById(R.id.next_button)
        homeButton = findViewById(R.id.home_button)
        question = findViewById(R.id.question)
        firstOption = findViewById(R.id.first_option)
        secondOption = findViewById(R.id.second_option)
        thirdOption = findViewById(R.id.third_option)
        homeButton?.setOnClickListener(this)
        nextButton?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.home_button) {
            onHomeButtonClicked()
        } else if (id == R.id.next_button) {
            onNextButtonClicked()
        }
    }

    private fun onHomeButtonClicked() {
        startActivity(this@QuestionsActivity)
        finish()
    }

    private fun onNextButtonClicked() {
        size = checkSizeByCountry()
        when (currentStatus) {
            0 -> updateScreen(
                -0.5f, 0.4f, 0f,
                "¿Cuánto mides?", "Menos de 1,70 m", "Entre 1,70 y 1,80 m", "Más de 1,80 m"
            )
            1 -> updateScreen(
                -0.4f, 0f, 0.4f,
                "¿De qué color tienes la piel?", "Blanca o casi blanca", "Negra o casi negra", "Entre blanca y negra"
            )
            2 -> updateScreen(
                -0.1f,
                2f,
                0.6f,
                "¿Qué relación tienes entre los dedos índice y anular?",
                "Es más largo el índice",
                "Es más largo el anular",
                "Son igual de largos"
            )
            3 -> updateScreen(
                -0.2f, -0.2f, 0.4f,
                "¿Comes habitualmente nueces o arándanos?", "Sí", "No", "A veces"
            )
            4 -> updateScreen(
                0.4f, -0.3f, 0.1f,
                "¿Eres fumador?", "Sí", "No", "Sólo de vez en cuando"
            )
            5 -> updateScreen(
                -0.5f,
                0f,
                -0.2f,
                "¿Cuántas veces a la semana haces ejercicio?",
                "Una vez o más",
                "Sólo hago ejercicio cuando me apetece",
                "No hago ejercicio"
            )

            6 -> {
                updateScreen(
                    0.5f, 0.1f, -0.3f,
                    "", "", "", ""
                )
                SolutionActivity.startActivity(this@QuestionsActivity, java.lang.Double.toString(size.toDouble()))
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
        when {
            firstOption!!.isChecked -> size += firstOptionVar
            secondOption!!.isChecked -> size += secondOptionVar
            thirdOption!!.isChecked -> size += thirdPositionVar
        }
        if (currentStatus != 6) {
            firstOption!!.isChecked = true
            question!!.text = questionText
            firstOption!!.text = firstOptionText
            secondOption!!.text = secondOptionText
            thirdOption!!.text = thirdOptionText
        }
        currentStatus++
    }

    private fun checkSizeByCountry(): Float {
        val country = intent.extras!!.getString(COUNTRY_SELECTED_ARG)
        return country?.toLowerCase(Locale.getDefault())?.let {
            if (country.contains("argentina") ||
                country.contains("andorra") ||
                country.contains("esp") ||
                country.contains("chile") ||
                country.contains("canadá") ||
                country.contains("belice")) {
                14.18f
            } else if (country.contains("estados unidos")) {
                12f
            } else if (country.contains("bolivia") ||
                country.contains("colombia") ||
                country.contains("venezuela") ||
                country.contains("ecuador") ||
                country.contains("guinea ecuatorial")) {
                17.09f
            } else {
                15.49f
            }
        } ?: 15.49f
    }

    companion object {

        private const val COUNTRY_SELECTED_ARG = "CountrySelected"

        fun startActivity(activity: Activity, countrySelected: String?) {
            val intent = Intent(activity, QuestionsActivity::class.java)
            val b = Bundle()
            b.putString(COUNTRY_SELECTED_ARG, countrySelected)
            intent.putExtras(b)
            activity.startActivity(intent)
        }
    }
}
