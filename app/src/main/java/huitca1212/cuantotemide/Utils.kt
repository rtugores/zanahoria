package huitca1212.cuantotemide

import android.os.Build
import android.text.Html

internal fun String.fromHtml(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("deprecation")
        Html.fromHtml(this)
    }.toString()
}
