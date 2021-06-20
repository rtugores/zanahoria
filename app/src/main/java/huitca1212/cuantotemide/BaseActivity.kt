package huitca1212.cuantotemide

import android.content.Intent
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
            type = SHARE_TYPE
            addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject))
            putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text))
        }

        startActivity(Intent.createChooser(intent, getString(R.string.share_chooser)))
    }

    private fun showInfoAppDialog() {
        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.info_dialog_title))
            setMessage(getString(R.string.info_dialog_text))
            setPositiveButton(R.string.info_dialog_positive_button) { _, _ ->
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = SHARE_MESSAGE_TYPE
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.app_contact_email)))
                }
                startActivity(Intent.createChooser(intent, getString(R.string.info_dialog_chooser)))
            }
            setNegativeButton(getString(R.string.info_dialog_negative_button)) { dialog, _ -> dialog.dismiss() }
            create()
        }.show()
    }

    companion object {

        internal const val SHARE_TYPE = "text/plain"
        internal const val SHARE_MESSAGE_TYPE = "message/rfc822"
    }
}
