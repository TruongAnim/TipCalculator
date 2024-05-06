package com.example.tipcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.Purple40
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                TipCalculatorApp()
            }
        }
    }
}

@Composable
fun RoundTip(isRound: Boolean, callback: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.round_up_tip),
            style = MaterialTheme.typography.titleMedium,
        )
        Switch(
            checked = isRound,
            onCheckedChange = callback,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}

@Composable
fun AppInput(text: String, callback: (String) -> Unit, @StringRes label: Int, action: ImeAction) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        singleLine = true,
        label = { Text(stringResource(label)) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number, imeAction = action
        ),
        onValueChange = callback
    )
}

@Composable
fun TipCalculatorApp() {
    var amount by remember {
        mutableStateOf("100")
    }
    var tipPercent by remember {
        mutableStateOf("10")
    }
    var isRound by remember {
        mutableStateOf(true)
    }
    val getTip: () -> Float = label@{
        val amountF = amount.toFloatOrNull() ?: 0f
        val tipPercentF = tipPercent.toFloatOrNull() ?: 0f
        if (isRound) {
            return@label (amountF * tipPercentF / 100 + 0.5f).toInt().toFloat()
        }
        amountF * tipPercentF / 100
    }
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 40.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = stringResource(id = R.string.calculate_tip),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(24.dp))
            AppInput(text = amount,
                label = R.string.bill_amount,
                action = ImeAction.Next,
                callback = {
                    amount = it
                })
            Spacer(modifier = Modifier.height(24.dp))
            AppInput(text = tipPercent,
                label = R.string.how_was_the_service,
                action = ImeAction.Done,
                callback = {
                    tipPercent = it
                })
            Spacer(modifier = Modifier.height(24.dp))
            RoundTip(isRound = isRound, callback = {
                isRound = !isRound
            })
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = String.format(
                    stringResource(id = R.string.tip_amount),
                    NumberFormat.getCurrencyInstance().format(getTip())
                ),
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 28.sp),
            )
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun TipCalculatorAppPreview() {
    TipCalculatorTheme {
        TipCalculatorApp()
    }
}