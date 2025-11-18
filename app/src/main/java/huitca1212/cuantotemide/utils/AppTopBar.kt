package huitca1212.cuantotemide.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import huitca1212.cuantotemide.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    containerColor: Int = R.color.colorPrimary,
    onShareClicked: (() -> Unit)? = null,
    onInfoClicked: (() -> Unit)? = null,
) {
    val context = LocalContext.current

    CenterAlignedTopAppBar(
        modifier = Modifier.shadow(elevation = 4.dp),
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.welcome_headline),
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = stringResource(id = R.string.welcome_subline),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        },
        actions = {
            IconButton(onClick = { onShareClicked?.invoke() ?: shareApp(context) }) {
                Icon(
                    tint = Color.White,
                    imageVector = Icons.Filled.Share,
                    contentDescription = stringResource(R.string.share_chooser)
                )
            }
            IconButton(onClick = { onInfoClicked?.invoke() }) {
                Icon(
                    tint = Color.White,
                    imageVector = Icons.Filled.Info,
                    contentDescription = stringResource(R.string.info_dialog_chooser)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = containerColor),
            titleContentColor = Color.White,
        ),
    )
}