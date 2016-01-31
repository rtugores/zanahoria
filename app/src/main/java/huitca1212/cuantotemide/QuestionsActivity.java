package huitca1212.cuantotemide;

import com.google.analytics.tracking.android.EasyTracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


public class QuestionsActivity extends OptionsActivity {

	private double answer;

	public void consultarPais() {
		Bundle bundle = this.getIntent().getExtras();
		String country = bundle.getString("SELECCION");
		if (country != null) {
			country = country.toLowerCase();

			answer = 15.49; //si es otro país
			if (country.contains("argentina") || country.contains("andorra") || country.contains("esp") || country.contains("chile") || country.contains("canadá") || country
					.contains("belice")) {
				answer = 14.18;
			}
			if (country.contains("estados unidos")) {
				answer = 12;
			}
			if (country.contains("bolivia") || country.contains("colombia") || country.contains("venezuela") || country.contains("ecuador") || country
					.contains("guinea ecuatorial")) {
				answer = 17.09;
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preguntas);

		final Button nextButton = (Button)findViewById(R.id.next_button);
		final Button homeButton = (Button)findViewById(R.id.home_button);
		final TextView question = (TextView)findViewById(R.id.question);
		final RadioButton firstOption = (RadioButton)findViewById(R.id.first_option);
		final RadioButton secondOption = (RadioButton)findViewById(R.id.second_option);
		final RadioButton thirdOption = (RadioButton)findViewById(R.id.third_option);

		homeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(QuestionsActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});

		nextButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				consultarPais();
				if (firstOption.isChecked()) {
					answer -= 0.5; //respuestas a edad
				}
				if (secondOption.isChecked()) {
					answer += 0.4;
				}
				if (thirdOption.isChecked()) {
					answer += 0;
				}
				firstOption.setChecked(true);
				question.setText("¿Cuánto mides?");
				firstOption.setText("Menos de 1,70 m");
				secondOption.setText("Entre 1,70 y 1,80 m");
				thirdOption.setText("Más de 1,80 m");
				nextButton.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						if (firstOption.isChecked()) {
							answer -= 0.4; //respuestas a estatura
						}
						if (thirdOption.isChecked()) {
							answer += 0.4;
						}
						firstOption.setChecked(true);
						question.setText("¿De qué color tienes la piel?");
						firstOption.setText("Blanca o casi blanca");
						secondOption.setText("Negra o casi negra");
						thirdOption.setText("Entre blanca y negra");
						nextButton.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								if (firstOption.isChecked()) {
									answer -= 0.1; //respuestas a color piel
								}
								if (secondOption.isChecked()) {
									answer += 2;
								}
								if (thirdOption.isChecked()) {
									answer += 0.6;
								}
								firstOption.setChecked(true);
								question.setText("¿Qué relación tienes entre los dedos índice y anular?");
								firstOption.setText("Es más largo el índice");
								secondOption.setText("Es más largo el anular");
								thirdOption.setText("Son igual de largos");
								nextButton.setOnClickListener(new View.OnClickListener() {
									public void onClick(View v) {
										if (firstOption.isChecked()) {
											answer -= 0.2; //respuestas a dedos
										}
										if (secondOption.isChecked()) {
											answer -= 0.2;
										}
										if (thirdOption.isChecked()) {
											answer += 0.4;
										}
										firstOption.setChecked(true);
										question.setText("¿Comes habitualmente nueces o arándanos?");
										firstOption.setText("Sí");
										secondOption.setText("No");
										thirdOption.setText("A veces");
										nextButton.setOnClickListener(new View.OnClickListener() {
											public void onClick(View v) {
												if (firstOption.isChecked()) {
													answer += 0.4; //respuestas a comida
												}
												if (secondOption.isChecked()) {
													answer -= 0.3;
												}
												if (thirdOption.isChecked()) {
													answer += 0.1;
												}
												firstOption.setChecked(true);
												question.setText("¿Eres fumador?");
												firstOption.setText("Sí");
												secondOption.setText("No");
												thirdOption.setText("Sólo de vez en cuando");
												nextButton.setOnClickListener(new View.OnClickListener() {
													public void onClick(View v) {
														if (firstOption.isChecked()) {
															answer -= 0.5; //respuestas al fumar
														}
														if (secondOption.isChecked()) {
															;
														}
														if (thirdOption.isChecked()) {
															answer -= 0.2;
														}
														firstOption.setChecked(true);
														question.setText("¿Cuántas veces a la semana haces ejercicio?");
														firstOption.setText("Una vez o más");
														secondOption.setText("Sólo hago ejercicio cuando me apetece");
														thirdOption.setText("No hago ejercicio");
														nextButton.setOnClickListener(new View.OnClickListener() {
															public void onClick(View v) {
																if (firstOption.isChecked()) {
																	answer += 0.5; //respuestas a ejercicio
																}
																if (secondOption.isChecked()) {
																	answer += 0.1;
																}
																if (thirdOption.isChecked()) {
																	answer -= 0.3;
																}
																Intent intent = new Intent(QuestionsActivity.this, SolutionActivity.class);
																String respuesta_s = Double.toString(answer);
																Bundle b = new Bundle();
																b.putString("TAMANYO", respuesta_s);
																intent.putExtras(b);
																startActivity(intent);
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
		});
	}

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance().activityStart(this);  // Add this method.
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance().activityStop(this);  // Add this method.
	}
}