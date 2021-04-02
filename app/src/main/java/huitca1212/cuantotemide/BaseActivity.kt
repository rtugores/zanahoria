package huitca1212.cuantotemide

import android.content.Intent
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_share -> share()
            R.id.menu_info -> showInfoAppDialog()
        }
        return true
    }

    private fun share() {
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

        startActivity(Intent.createChooser(intent, "Compartir mediante"))
    }

    private fun showInfoAppDialog() {
        val dialog = AlertDialog.Builder(this).apply {
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
