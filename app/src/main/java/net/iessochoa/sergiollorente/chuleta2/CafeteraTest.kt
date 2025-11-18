package net.iessochoa.sergiollorente.chuleta2

import android.R.attr.enabled
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CafeteraApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.background(
            Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF8B4513),
                    Color(0xFFD2691E),
                    Color(0xFFFFE4C4)
                )
            )
        ),
        topBar = { TopAppBar(title = {Text("Cafetera")}) }
    ) {
        innerPadding ->
        CafeteraLayout(modifier = modifier.padding(innerPadding))
    }
}

@Composable
fun CafeteraLayout(modifier: Modifier = Modifier) {

    var agua by remember { mutableStateOf(50) }
    var cafesPreparados by remember { mutableStateOf(0) }
    var context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize()
        .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CoffeIntensity()

        Text(text = "Agua: $agua", style = MaterialTheme.typography.displayLarge)
        Text(text = "Cafes: $cafesPreparados", style = MaterialTheme.typography.displayLarge)


        if (agua < 20) {
            CoffeButton(
                buttonText = "Hacer Café",
                onClick = { },
                enabled = false
            )
        } else {
            CoffeButton(
                buttonText = "Hacer Café",
                onClick = {
                        cafeToast(context, "Cafe Preparado")
                        agua -= 20
                        cafesPreparados++
                },
                enabled = true
            )
        }

        CoffeButton(
            buttonText = "Añadir Agua",
            onClick = {
                if(agua == 100) {
                    cafeToast(context, "Agua al Máximo")
                } else if ((agua+20) > 100) {
                    agua = 100
                } else {
                    agua += 20
                }
            },
            enabled = true
        )

        CoffeDisplay(coffess = cafesPreparados)


    }

}

@Composable
fun CoffeButton(
    buttonText: String,
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {

    Button(
        onClick = onClick,
        modifier = modifier.width(300.dp),
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(5.dp),
        border = BorderStroke(1.dp, Color.White)
    ) {
        Text(buttonText)
    }
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    onclick: () -> Unit
    ) {
    Button(
        onClick = onclick,
    ) {
        Text(buttonText)
    }
}


@Composable
fun CoffeIntensity(modifier: Modifier = Modifier, ) {

    var intensity by remember { mutableStateOf(0) }


    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Intensidad", style = MaterialTheme.typography.displayMedium)
            CustomButton(
                buttonText = "+",
                onclick = {
                    if (intensity < 13) {
                            intensity++
                        }
                }
            )
            CustomButton(
                buttonText = "-",
                onclick = {
                    if (intensity > 0) {
                        intensity--
                    }
                }
            )
        }
        Row {
            for (i in 1..intensity) {
                Icon(imageVector = Icons.Default.Place, contentDescription = null)
            }
        }
    }
}

@Composable
fun CoffeDisplay(modifier: Modifier = Modifier, coffess:Int) {
    Row {
        for (i in 1..coffess) {
            Image(painter = painterResource(R.drawable._6_169744_coffee_png_2697305967), modifier = modifier.size(100.dp),contentDescription = null)
        }
    }
}

fun cafeToast(context: Context, text: String){

    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun previewCafetera() {
    CafeteraApp()
}