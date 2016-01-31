package huitca1212.cuantotemide;

import com.google.analytics.tracking.android.EasyTracker;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class SolutionActivity extends OptionsActivity {

	protected double tamanyo_compartir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.solucion);

		ImageView img = (ImageView)findViewById(R.id.fotofinal);
		TextView txtTamanho = (TextView)findViewById(R.id.Tamanho_texto);
		TextView txtSolucion = (TextView)findViewById(R.id.Solucion_texto);
		Bundle bundle = this.getIntent().getExtras();
		String tamanyo_s = bundle.getString("TAMANYO");
		double tamanyo = Double.parseDouble(tamanyo_s);
		tamanyo_compartir = tamanyo;
		txtTamanho.setText(String.format("%.2f", tamanyo) + " cm");
		txtTamanho.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		MediaPlayer mpRes;

		if (tamanyo == 13.79) {
			tamanyo += 0.1; //nunca podrá ser igual al de la media
		}
		if (tamanyo < 13.79) {
			img.setImageResource(R.drawable.peqe);
			txtSolucion.setText("La media de una zanahoria en el mundo es de 13,79 cm.\n\n" +
					"La tienes más pequeña, pero no te preocupes.\n\n¡No es importante!");
			mpRes = MediaPlayer.create(getApplicationContext(), R.raw.abucheo);
			mpRes.start();
		} else {
			img.setImageResource(R.drawable.gran);
			txtSolucion.setText("La media de una zanahoria en el mundo es de 13,79 cm.\n\n" +
					"¡La tienes más grande!\n\n¿Sabes ya si es más grande que la de tus amigos?");
			mpRes = MediaPlayer.create(getApplicationContext(), R.raw.aplausos);
			mpRes.start();
		}

		final Button boton1 = (Button)findViewById(R.id.home_button); //PRIMER BOTON
		boton1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(SolutionActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
		final Button boton2 = (Button)findViewById(R.id.button_com); //PRIMER BOTON
		boton2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
				intent.setType("text/plain");
				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
				} else {
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
				}
				intent.putExtra(Intent.EXTRA_SUBJECT, "¡Mira cuánto me mide!");
				intent.putExtra(Intent.EXTRA_TEXT, "La aplicación \"¿Cuánto te mide?\" dice que mi zanahoria mide " +
						String.format("%.2f", tamanyo_compartir) + " cm. ¿A ti cuánto te da?: https://play.google.com/store/apps/details?id=huitca1212.cuantotemide");
				startActivity(Intent.createChooser(intent, "Compartir mediante"));
			}
		});
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