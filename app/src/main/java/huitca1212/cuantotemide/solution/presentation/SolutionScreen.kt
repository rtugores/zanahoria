package huitca1212.cuantotemide.solution.presentation

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import huitca1212.cuantotemide.R
import huitca1212.cuantotemide.ui.theme.AppTheme
import huitca1212.cuantotemide.utils.AppTopBar
import huitca1212.cuantotemide.utils.BottomButtonRow

@Composable
internal fun SolutionScreen(
    viewModel: SolutionViewModel,
    onHomeButtonClicked: () -> Unit,
    onShareButtonClicked: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(uiState.triggerSoundEffect) {
        uiState.triggerSoundEffect?.getContentIfNotHandled()?.let { soundResId ->
            val mediaPlayer = MediaPlayer.create(context, soundResId)
            mediaPlayer?.apply {
                setOnCompletionListener { it.release() }
                start()
            }
        }
    }

    SolutionScreenContent(
        uiState = uiState,
        onHomeButtonClicked = onHomeButtonClicked,
        onShareButtonClicked = onShareButtonClicked
    )
}

@Composable
internal fun SolutionScreenContent(
    uiState: SolutionUiState,
    onHomeButtonClicked: () -> Unit,
    onShareButtonClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppTopBar(
                containerColor = R.color.colorPrimary,
                onShareClicked = null,
                onInfoClicked = null
            )
        },
        containerColor = colorResource(id = R.color.background_color)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(colorResource(id = R.color.background_color)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = uiState.displayText,
                fontSize = 46.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                uiState.imageResId?.let { imageRes ->
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = stringResource(id = R.string.welcome_headline),
                        modifier = Modifier
                            .width(123.dp)
                            .height(220.dp)
                            .padding(end = 8.dp)
                    )
                }

                uiState.solutionTextResId?.let { textResId ->
                    Text(
                        text = stringResource(id = textResId),
                        fontSize = 20.sp,
                        color = Color.White,
                        lineHeight = 24.sp,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }

            BottomButtonRow(
                leftButtonTextRes = R.string.go_to_start,
                onLeftButtonClick = onHomeButtonClicked,
                rightButtonTextRes = R.string.menu_share,
                onRightButtonClick = onShareButtonClicked,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SolutionScreenPreview() {
    AppTheme {
        SolutionScreenContent(
            uiState = SolutionUiState(
                displayText = "15.5 cm",
                imageResId = R.drawable.solution_bigger,
                solutionTextResId = R.string.solution_bigger_text,
                soundResId = null,
                shareSubject = "",
                shareText = ""
            ),
            onHomeButtonClicked = {},
            onShareButtonClicked = {}
        )
    }
}
