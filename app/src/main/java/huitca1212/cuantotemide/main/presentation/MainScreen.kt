package huitca1212.cuantotemide.main.presentation // Your package name

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import huitca1212.cuantotemide.R
import huitca1212.cuantotemide.questions.data.model.Country
import huitca1212.cuantotemide.utils.AppInfoDialog
import huitca1212.cuantotemide.utils.shareApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(
    uiState: MainUiState,
    onUserNameChanged: (String) -> Unit,
    onCountrySelected: (Country?) -> Unit,
    onStartClicked: () -> Unit
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    var showInfoDialog by remember { mutableStateOf(false) }
    var showOverflowMenu by remember { mutableStateOf(false) }

    val welcomeTextParsed = remember(context) {
        HtmlCompat.fromHtml(context.getString(R.string.welcome_text), HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.shadow(elevation = 4.dp),
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontWeight = FontWeight.Bold,
                    )
                },
                actions = {
                    IconButton(onClick = { shareApp(context) }) {
                        Icon(
                            tint = Color.White,
                            imageVector = Icons.Filled.Share,
                            contentDescription = stringResource(R.string.share_chooser)
                        )
                    }
                    IconButton(onClick = { showInfoDialog = true }) {
                        Icon(
                            tint = Color.White,
                            imageVector = Icons.Filled.Info,
                            contentDescription = stringResource(R.string.info_dialog_chooser)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(id = R.color.colorPrimary),
                    titleContentColor = Color.White,
                ),
            )
        },
        containerColor = colorResource(id = R.color.background_color),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .imePadding()
                .verticalScroll(scrollState)
                .padding(horizontal = dimensionResource(id = R.dimen.side_margin)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = welcomeTextParsed.toString(), // Use the parsed HTML
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                lineHeight = 32.sp, // Approximate 1.3 line spacing multiplier for 20sp
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(vertical = 16.dp)
            )

            OutlinedTextField(
                value = uiState.userName,
                onValueChange = { onUserNameChanged(it.take(25)) }, // Enforce maxLength
                label = { Text(stringResource(id = R.string.welcome_add_name_text)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.side_margin))
                    .border(
                        width = 0.dp,
                        color = Color.Transparent, // To remove default OutlinedTextField border if background handles it
                        shape = RoundedCornerShape(8.dp)
                    ),
                //                colors = TextFieldDefaults.outlinedTextFieldColors(
                //                    focusedBorderColor = Color.Transparent,
                //                    unfocusedBorderColor = Color.Transparent,
                //                    textColor = Color.Black, // Adjust as per your @drawable/layout_corners_shape
                //                    cursorColor = MaterialTheme.colorScheme.primary,
                //                    containerColor = Color.Transparent // Background is handled by modifier
                //                )
            )

            CountryDropDown(
                countries = uiState.countries,
                selectedCountry = uiState.selectedCountry,
                onCountrySelected = onCountrySelected,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.side_margin))
            )

            Button(
                onClick = onStartClicked,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.colorPrimary), // Define this color
                    contentColor = Color.White,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.side_margin), top = 8.dp) // Adjust top padding
                    .height(50.dp)
            ) {
                Text(text = stringResource(id = R.string.start).uppercase())
            }
        }
        if (showInfoDialog) {
            AppInfoDialog(onDismissRequest = { showInfoDialog = false })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CountryDropDown(
    countries: List<Country>,
    selectedCountry: Country?,
    onCountrySelected: (Country?) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val shape = RoundedCornerShape(8.dp) // For consistency

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedCountry?.name ?: stringResource(R.string.welcome_chooser_text),
            onValueChange = {}, // Not directly editable
            readOnly = true,
            label = { Text(stringResource(R.string.welcome_chooser_text)) }, // Add a label string
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true)
                .fillMaxWidth()
                .border(0.dp, Color.Transparent, shape),
            //            colors = TextFieldDefaults.outlinedTextFieldColors(
            //                focusedBorderColor = Color.Transparent,
            //                unfocusedBorderColor = Color.Transparent,
            //                textColor = if (selectedCountry != null) Color.Black else Color.Gray, // Adjust hint color
            //                containerColor = Color.Transparent
            //            ),
            shape = shape
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            //            modifier = Modifier.background(colorResource(id = R.color.dropdown_background_color)) // Define this color
        ) {
            countries.forEach { country ->
                DropdownMenuItem(
                    text = { Text(country.name) },
                    onClick = {
                        onCountrySelected(country)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        MainScreen(
            uiState = MainUiState(
                countries = listOf(
                    Country("1", "España", 1.0f),
                    Country("2", "France", 1.2f)
                ),
                userName = "Android User"
            ),
            onStartClicked = {},
            onUserNameChanged = {},
            onCountrySelected = {},
        )
    }
}

