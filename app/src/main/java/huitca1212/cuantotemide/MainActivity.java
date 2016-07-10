package huitca1212.cuantotemide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends OptionsActivity implements View.OnClickListener {
	private Spinner countrySpinner;
	private Button startButton;
	private String[] dataArray = {"Selecciona país...",
			"Andorra", "Argentina", "Belice", "Bolivia", "Brasil", "Canadá", "Chile", "Colombia", "Costa Rica", "Cuba", "Ecuador", "El Salvador",
			"España", "Estados Unidos", "Francia", "Guatemala", "Guinea Ecuatorial", "Haití", "Honduras", "Marruecos", "México", "Nicaragua", "OTRO", "Panamá",
			"Paraguay", "Perú", "Puerto Rico", "República Dominicana", "Trinidad y Tobago", "Uruguay", "Venezuela"};
	private String countrySelected = dataArray[0];

	public static void startActivity(Activity activity) {
		Intent intent = new Intent(activity, MainActivity.class);
		activity.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		countrySpinner = (Spinner)findViewById(R.id.country_spinner);
		startButton = (Button)findViewById(R.id.start_button);

		startButton.setOnClickListener(this);
		Utils.setAnalytics(this);
		setCountrySpinner();
	}

	private void setCountrySpinner() {
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dataArray);
		countrySpinner.setAdapter(adapter);
		countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				countrySelected = dataArray[i];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.start_button) {
			onStartButtonClicked();
		}
	}

	private void onStartButtonClicked() {
		if (countrySelected.equals(dataArray[0])) {
			Toast.makeText(getApplicationContext(), "¡Selecciona país!", Toast.LENGTH_SHORT).show();
		} else {
			QuestionsActivity.startActivity(MainActivity.this, countrySelected);
		}
	}
}