package huitca1212.cuantotemide;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;

public class Utils {

	public static void share(Context ctx) {
		final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.setType("text/plain");
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		} else {
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
		}
		intent.putExtra(Intent.EXTRA_SUBJECT, "¡Descarga \"¿Cuánto te mide?\"!");
		intent.putExtra(Intent.EXTRA_TEXT,
				"Descarga la aplicación que te servirá para saber si tu zanahoria es grande o pequeña. Disponible YA en Google Play: https://play.google.com/store/apps/details?id=huitca1212.cuantotemide");
		ctx.startActivity(Intent.createChooser(intent, "Compartir mediante"));
	}

	public static void showInfoAppDialog(final Context ctx) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle("Información");
		builder.setMessage("Aplicación desarrollada por RJ Apps. " +
				"Para cualquier sugerencia, no dude en contactar.");
		builder.setPositiveButton("Contactar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822")
						.putExtra(Intent.EXTRA_EMAIL, new String[]{"huitca1212@gmail.com"});
				ctx.startActivity(Intent.createChooser(i, "Enviar mediante"));
			}
		});
		builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
}