package huitca1212.cuantotemide.questions.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import huitca1212.cuantotemide.R
import huitca1212.cuantotemide.ui.theme.AppTheme
import huitca1212.cuantotemide.utils.AppInfoDialog
import huitca1212.cuantotemide.utils.AppTopBar
import huitca1212.cuantotemide.utils.BannerAdView
import huitca1212.cuantotemide.utils.BottomButtonRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun QuestionsScreen(
    uiState: QuestionsUiState,
    onNextClicked: (Int) -> Unit,
    onHomeClicked: () -> Unit
) {
    var showInfoDialog by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            AppTopBar(
                containerColor = R.color.green,
                onInfoClicked = { showInfoDialog = true }
            )
        },
        containerColor = colorResource(id = R.color.background_color),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = AppTheme.dimens.sideMargin),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp)
                    .padding(top = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = uiState.questionData.questionTextRes),
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 36.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                RadioOption(
                    text = stringResource(id = uiState.questionData.firstOptionTextRes),
                    selected = selectedOption == 0,
                    onClick = { selectedOption = 0 }
                )
                RadioOption(
                    text = stringResource(id = uiState.questionData.secondOptionTextRes),
                    selected = selectedOption == 1,
                    onClick = { selectedOption = 1 }
                )
                RadioOption(
                    text = stringResource(id = uiState.questionData.thirdOptionTextRes),
                    selected = selectedOption == 2,
                    onClick = { selectedOption = 2 }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                BottomButtonRow(
                    leftButtonTextRes = R.string.go_to_start,
                    onLeftButtonClick = onHomeClicked,
                    rightButtonTextRes = R.string.next,
                    onRightButtonClick = { onNextClicked(selectedOption) }
                )

                Spacer(modifier = Modifier.height(AppTheme.dimens.sideMargin))

                BannerAdView(
                    adUnitId = stringResource(id = R.string.ads_banner_id),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }
        }

        if (showInfoDialog) {
            AppInfoDialog(onDismissRequest = { showInfoDialog = false })
        }
    }
}

@Composable
private fun RadioOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onClick
            )
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = colorResource(id = R.color.colorPrimary),
                unselectedColor = Color.White
            )
        )
        Text(
            text = text,
            color = Color.White,
            fontSize = AppTheme.dimens.standard20sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionsScreenPreview() {
    val previewQuestionData = QuestionsViewModel.QuestionData(
        firstOptionDelta = -0.3f,
        secondOptionDelta = 0f,
        thirdOptionDelta = 0.3f,
        questionTextRes = R.string.fourth_question_title,
        firstOptionTextRes = R.string.fourth_question_option_one,
        secondOptionTextRes = R.string.fourth_question_option_two,
        thirdOptionTextRes = R.string.fourth_question_option_three
    )

    AppTheme {
        QuestionsScreen(
            uiState = QuestionsUiState(
                size = 15.0f,
                questionIndex = 0,
                questionData = previewQuestionData
            ),
            onNextClicked = {},
            onHomeClicked = {}
        )
    }
}
