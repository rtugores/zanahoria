package huitca1212.cuantotemide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class VariasFunciones extends Activity {
	
	public void compartir(Context eso){
		final Intent intent=new Intent(android.content.Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		intent.putExtra(Intent.EXTRA_SUBJECT, "¡Descarga ¿Cuánto te mide?!");
		intent.putExtra(Intent.EXTRA_TEXT, "Descarga la aplicación que te servirá para saber si tu zanahoria es grande o pequeña. Disponible YA en Google Play: https://play.google.com/store/apps/details?id=huitca1212.cuantotemide");
		eso.startActivity(Intent.createChooser(intent, "Compartir mediante"));
	}
}