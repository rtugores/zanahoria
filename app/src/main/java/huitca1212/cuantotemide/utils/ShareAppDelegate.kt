package huitca1212.cuantotemide.utils

import android.content.Context
import android.content.Intent
import huitca1212.cuantotemide.R

const val SHARE_TYPE = "text/plain"

internal fun shareApp(context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = SHARE_TYPE
        addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.share_subject))
        putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_text))
    }
    context.startActivity(Intent.createChooser(intent, context.getString(R.string.share_chooser)))
}
