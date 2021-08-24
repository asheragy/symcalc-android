package org.cerion.symcalcapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.cerion.symcalcapp.ui.theme.SymCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SymCalcTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Calculator()
                }
            }
        }
    }
}

@Composable
fun Calculator(viewModel: MainViewModel = MainViewModel()) {

    val display: String by viewModel.display.observeAsState(viewModel.display.value!!)
    val preview: String by viewModel.preview.observeAsState(viewModel.preview.value!!)

    Column(Modifier.background(Color.DarkGray, RectangleShape)) {
        Text(text = display,
            Modifier.fillMaxWidth(),
            fontSize = 40.sp,
            textAlign = TextAlign.Right, color = Color.White)
        Text(text = preview,
            Modifier.fillMaxWidth(),
            fontSize = 30.sp,
            textAlign = TextAlign.Right, color = Color.LightGray)
        KeyPad {
            viewModel.onKey(it)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SymCalcTheme {
        Calculator(MainViewModel("2+3"))
    }
}