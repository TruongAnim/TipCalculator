package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import java.lang.NumberFormatException
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
fun TipCalculatorApp() {
    var amount by remember {
        mutableStateOf("0")
    }
    var getTip: (Int) -> Float = {
        try {
            val amountF = amount.toFloat()
            amountF * it / 100
        } catch (e: NumberFormatException) {
            println("Error")
            0f
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(all = 40.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = stringResource(id = R.string.calculate_tip))
            Spacer(modifier = Modifier.height(16.dp))
            TextField(value = amount,
                singleLine = true,
                label = { Text(stringResource(R.string.bill_amount)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = {
                    amount = it
                })
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = String.format(
                    stringResource(id = R.string.tip_amount),
                    NumberFormat.getCurrencyInstance().format(getTip(15))
                )
            )
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