package huitca1212.cuantotemide;

import com.google.analytics.tracking.android.EasyTracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends OptionsActivity implements View.OnClickListener {
	Spinner countrySpinner;
	Button startButton;
	String[] dataArray = {"Selecciona país...",
			"Andorra", "Argentina", "Belice", "Bolivia", "Brasil", "Canadá", "Chile", "Colombia", "Costa Rica", "Cuba", "Ecuador", "El Salvador",
			"España", "Estados Unidos", "Francia", "Guatemala", "Guinea Ecuatorial", "Haití", "Honduras", "Marruecos", "México", "Nicaragua", "OTRO", "Panamá",
			"Paraguay", "Perú", "Puerto Rico", "República Dominicana", "Trinidad y Tobago", "Uruguay", "Venezuela"};
	String selection = "Selecciona país...";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		countrySpinner = (Spinner)findViewById(R.id.country_spinner);
		startButton = (Button)findViewById(R.id.start_button);

		ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dataArray);
		countrySpinner.setAdapter(adaptador);
		countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				selection = dataArray[i];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		startButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.start_button) {
			if (selection.equals("Selecciona país...")) {
				Toast.makeText(getApplicationContext(), "¡Selecciona país!", Toast.LENGTH_SHORT).show();
			} else {
				Intent intent = new Intent(MainActivity.this, QuestionsActivity.class);
				Bundle b = new Bundle();
				b.putString("SELECCION", selection);
				intent.putExtras(b);
				startActivity(intent);
			}
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance().activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance().activityStop(this);
	}
}