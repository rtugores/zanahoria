package huitca1212.cuantotemide

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class OptionsActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_share -> Utils.share(this)
            R.id.menu_info -> Utils.showInfoAppDialog(this)
        }
        return true
    }
}
