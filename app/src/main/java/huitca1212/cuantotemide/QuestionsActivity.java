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
		if (firstOption.isChecked()) {
			size -= 0.5; //respuestas a edad
		}
		if (secondOption.isChecked()) {
			size += 0.4;
		}
		if (thirdOption.isChecked()) {
			size += 0;
		}
		firstOption.setChecked(true);
		question.setText("¿Cuánto mides?");
		firstOption.setText("Menos de 1,70 m");
		secondOption.setText("Entre 1,70 y 1,80 m");
		thirdOption.setText("Más de 1,80 m");
		nextButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (firstOption.isChecked()) {
					size -= 0.4; //respuestas a estatura
				}
				if (thirdOption.isChecked()) {
					size += 0.4;
				}
				firstOption.setChecked(true);
				question.setText("¿De qué color tienes la piel?");
				firstOption.setText("Blanca o casi blanca");
				secondOption.setText("Negra o casi negra");
				thirdOption.setText("Entre blanca y negra");
				nextButton.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						if (firstOption.isChecked()) {
							size -= 0.1; //respuestas a color piel
						}
						if (secondOption.isChecked()) {
							size += 2;
						}
						if (thirdOption.isChecked()) {
							size += 0.6;
						}
						firstOption.setChecked(true);
						question.setText("¿Qué relación tienes entre los dedos índice y anular?");
						firstOption.setText("Es más largo el índice");
						secondOption.setText("Es más largo el anular");
						thirdOption.setText("Son igual de largos");
						nextButton.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								if (firstOption.isChecked()) {
									size -= 0.2; //respuestas a dedos
								}
								if (secondOption.isChecked()) {
									size -= 0.2;
								}
								if (thirdOption.isChecked()) {
									size += 0.4;
								}
								firstOption.setChecked(true);
								question.setText("¿Comes habitualmente nueces o arándanos?");
								firstOption.setText("Sí");
								secondOption.setText("No");
								thirdOption.setText("A veces");
								nextButton.setOnClickListener(new View.OnClickListener() {
									public void onClick(View v) {
										if (firstOption.isChecked()) {
											size += 0.4; //respuestas a comida
										}
										if (secondOption.isChecked()) {
											size -= 0.3;
										}
										if (thirdOption.isChecked()) {
											size += 0.1;
										}
										firstOption.setChecked(true);
										question.setText("¿Eres fumador?");
										firstOption.setText("Sí");
										secondOption.setText("No");
										thirdOption.setText("Sólo de vez en cuando");
										nextButton.setOnClickListener(new View.OnClickListener() {
											public void onClick(View v) {
												if (firstOption.isChecked()) {
													size -= 0.5; //respuestas al fumar
												}
												if (secondOption.isChecked()) {
													;
												}
												if (thirdOption.isChecked()) {
													size -= 0.2;
												}
												firstOption.setChecked(true);
												question.setText("¿Cuántas veces a la semana haces ejercicio?");
												firstOption.setText("Una vez o más");
												secondOption.setText("Sólo hago ejercicio cuando me apetece");
												thirdOption.setText("No hago ejercicio");
												nextButton.setOnClickListener(new View.OnClickListener() {
													public void onClick(View v) {
														if (firstOption.isChecked()) {
															size += 0.5; //respuestas a ejercicio
														}
														if (secondOption.isChecked()) {
															size += 0.1;
														}
														if (thirdOption.isChecked()) {
															size -= 0.3;
														}
														SolutionActivity.startActivity(QuestionsActivity.this, Double.toString(size));
														finish();
													}
												});
											}
										});
									}
								});
							}
						});
					}
				});
			}
		});
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