package huitca1212.cuantotemide.utils

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import huitca1212.cuantotemide.R

@Composable
fun BottomButtonRow(
    @StringRes leftButtonTextRes: Int,
    onLeftButtonClick: () -> Unit,
    @StringRes rightButtonTextRes: Int,
    onRightButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(35.dp)
    ) {
        Button(
            onClick = onLeftButtonClick,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.colorPrimary),
                contentColor = Color.White,
            ),
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
        ) {
            Text(text = stringResource(id = leftButtonTextRes))
        }

        Button(
            onClick = onRightButtonClick,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.colorPrimary),
                contentColor = Color.White,
            ),
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
        ) {
            Text(text = stringResource(id = rightButtonTextRes))
        }
    }
}
