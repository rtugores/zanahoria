package huitca1212.cuantotemide

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AlertDialog

object Utils {

    fun share(context: Context) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
            } else {
                addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
            }
            putExtra(Intent.EXTRA_SUBJECT, """¡Descarga "¿Cuánto te mide?"!""")
            putExtra(
                Intent.EXTRA_TEXT,
                "Descarga la aplicación que te servirá para saber si tu zanahoria es grande o pequeña. " +
                    "Disponible YA en Google Play: https://play.google.com/store/apps/details?id=huitca1212.cuantotemide"
            )
        }

        context.startActivity(Intent.createChooser(intent, "Compartir mediante"))
    }

    fun showInfoAppDialog(context: Context) {
        val dialog = AlertDialog.Builder(context).apply {
            setTitle("Información")
            setMessage(
                "Aplicación desarrollada por RJ Apps. " +
                    "Para cualquier sugerencia, no dude en contactar."
            )
            setPositiveButton("Contactar") { _, _ ->
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "message/rfc822"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("huitca1212@gmail.com"))
                }
                context.startActivity(Intent.createChooser(intent, "Enviar mediante"))
            }
            setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }
            create()
        }

        dialog.show()
    }
}
