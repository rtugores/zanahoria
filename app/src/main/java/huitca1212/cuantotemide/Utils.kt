package huitca1212.cuantotemide

import android.os.Build
import android.text.Html

internal fun htmlToPlainString(source: String): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(source)
    }.toString()
}
