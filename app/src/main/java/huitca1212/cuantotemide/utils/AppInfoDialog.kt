package huitca1212.cuantotemide.utils

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import huitca1212.cuantotemide.R

@Composable
internal fun AppInfoDialog(
    onDismissRequest: () -> Unit,
) {
    val context = LocalContext.current

    AlertDialog(
        icon = { Icon(Icons.Filled.Info, contentDescription = stringResource(R.string.info_dialog_title)) },
        title = { Text(text = stringResource(R.string.info_dialog_title)) },
        text = { Text(text = stringResource(R.string.info_dialog_text)) },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                        data = "mailto:".toUri() // Only email apps should handle this
                        putExtra(Intent.EXTRA_EMAIL, arrayOf(context.getString(R.string.app_contact_email)))
                    }
                    try {
                        context.startActivity(
                            Intent.createChooser(emailIntent, context.getString(R.string.info_dialog_chooser))
                        )
                    } catch (_: ActivityNotFoundException) {
                        // Do nothing if no email app is available
                    }
                    onDismissRequest()
                }
            ) {
                Text(stringResource(R.string.info_dialog_positive_button))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(stringResource(R.string.info_dialog_negative_button))
            }
        }
    )
}
