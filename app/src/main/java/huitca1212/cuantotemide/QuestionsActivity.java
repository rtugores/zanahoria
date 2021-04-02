package huitca1212.cuantotemide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


public class QuestionsActivity extends OptionsActivity implements View.OnClickListener {

	private static final String COUNTRY_SELECTED_ARG = "CountrySelected";
	private Button nextButton;
	private Button homeButton;
	private TextView question;
	private RadioButton firstOption;
	private RadioButton secondOption;
	private RadioButton thirdOption;
	private float size;
	private int currentStatus;

	public static void startActivity(Activity activity, String countrySelected) {
		Intent intent = new Intent(activity, QuestionsActivity.class);
		Bundle b = new Bundle();
		b.putString(COUNTRY_SELECTED_ARG, countrySelected);
		intent.putExtras(b);
		activity.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questions);

		nextButton = (Button)findViewById(R.id.next_button);
		homeButton = (Button)findViewById(R.id.home_button);
		question = (TextView)findViewById(R.id.question);
		firstOption = (RadioButton)findViewById(R.id.first_option);
		secondOption = (RadioButton)findViewById(R.id.second_option);
		thirdOption = (RadioButton)findViewById(R.id.third_option);
		homeButton.setOnClickListener(this);
		nextButton.setOnClickListener(this);

		Utils.setAnalytics(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.home_button) {
			onHomeButtonClicked();
		} else if (id == R.id.next_button) {
			onNextButtonClicked();
		}
	}

	private void onHomeButtonClicked() {
		MainActivity.startActivity(QuestionsActivity.this);
		finish();
	}

	private void onNextButtonClicked() {
		size = checkSizeByCountry();
		switch (currentStatus) {
			case 0:
				updateScreen(-0.5f, 0.4f, 0f,
						"¿Cuánto mides?", "Menos de 1,70 m", "Entre 1,70 y 1,80 m", "Más de 1,80 m");
				break;
			case 1:
				updateScreen(-0.4f, 0, 0.4f,
						"¿De qué color tienes la piel?", "Blanca o casi blanca", "Negra o casi negra", "Entre blanca y negra");
				break;
			case 2:
				updateScreen(-0.1f, 2, 0.6f,
						"¿Qué relación tienes entre los dedos índice y anular?", "Es más largo el índice", "Es más largo el anular", "Son igual de largos");
				break;
			case 3:
				updateScreen(-0.2f, -0.2f, 0.4f,
						"¿Comes habitualmente nueces o arándanos?", "Sí", "No", "A veces");
				break;
			case 4:
				updateScreen(0.4f, -0.3f, 0.1f,
						"¿Eres fumador?", "Sí", "No", "Sólo de vez en cuando");
				break;
			case 5:
				updateScreen(-0.5f, 0f, -0.2f,
						"¿Cuántas veces a la semana haces ejercicio?", "Una vez o más", "Sólo hago ejercicio cuando me apetece", "No hago ejercicio");
				break;
			case 6:
				updateScreen(0.5f, 0.1f, -0.3f,
						"", "", "", "");
				SolutionActivity.startActivity(QuestionsActivity.this, Double.toString(size));
				finish();
				break;
		}
	}

	private void updateScreen(float firstOptionVar, float secondOptionVar, float thirdPositionVar,
			String questionText, String firstOptionText, String secondOptionText, String thirdOptionText) {
		if (firstOption.isChecked()) {
			size += firstOptionVar;
		} else if (secondOption.isChecked()) {
			size += secondOptionVar;
		} else if (thirdOption.isChecked()) {
			size += thirdPositionVar;
		}
		if (currentStatus != 6) {
			firstOption.setChecked(true);
			question.setText(questionText);
			firstOption.setText(firstOptionText);
			secondOption.setText(secondOptionText);
			thirdOption.setText(thirdOptionText);
		}
		currentStatus++;
	}

	public float checkSizeByCountry() {
		String country = getIntent().getExtras().getString(COUNTRY_SELECTED_ARG);
		if (country != null) {
			country = country.toLowerCase();
			if (country.contains("argentina") || country.contains("andorra") || country.contains("esp") || country.contains("chile") || country.contains("canadá") || country
					.contains("belice")) {
				return 14.18f;
			} else if (country.contains("estados unidos")) {
				return 12;
			} else if (country.contains("bolivia") || country.contains("colombia") || country.contains("venezuela") || country.contains("ecuador") || country
					.contains("guinea ecuatorial")) {
				return 17.09f;
			} else {
				return 15.49f;
			}
		} else {
			return 15.49f;
		}
	}
}