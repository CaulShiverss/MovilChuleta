package net.iessochoa.sergiollorente.chuleta2

import android.R.attr.name
import android.R.attr.rating
import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class PokeCard( // 1
    val name: String,
    val image: Painter,
    var rating: Int,
    val onRatingChange: (Int) -> Unit = {}
)

@Composable
fun CartasTest() {

    val imageMap = mapOf(
        "Dewot" to R.drawable.dewot,
        "Espeon" to R.drawable.espeon,
        "Evee" to R.drawable.evee,
        "Turtin" to R.drawable.turtin,
        "Gigas" to R.drawable.regis
    )

    val cardListStr = imageMap.keys

    val cardList = cardListStr.map {
            card ->
        var rating by remember { mutableIntStateOf(0) }
        PokeCard(
            name = card,
            image = painterResource(imageMap[card] ?: R.drawable.ic_launcher_foreground),
            rating = rating,
            onRatingChange = {rating = it}
        )
    }

    var selectedStr by remember { mutableStateOf("") }
    val selectedCard = cardList.find { it.name == selectedStr }

    Column(modifier = Modifier.fillMaxSize().background(            Brush.verticalGradient(
        colors = listOf(
            Color(0xFF135B8B), // Marrón oscuro (saddle brown)
            Color(0xFF3F51B5), // Marrón medio (chocolate)
            Color(0xFFBEF4FC)  // Crema claro
        )
    )), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {

        CardList(cardList, onClick = {selectedStr = it})
        CardRating(selectedCard ?: cardList[0])

    }



}

@Composable
fun CardList(cards: List<PokeCard>, onClick: (String) -> Unit, modifier: Modifier = Modifier) {

    LazyColumn(modifier = Modifier.fillMaxWidth().height(200.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        items(cards) {
            card ->
            Button(
                onClick = { onClick(card.name) },
                modifier = modifier.width(300.dp),
                enabled = true,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EE),
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(5.dp),
                border = BorderStroke(1.dp, Color.White)
            ) {
                Text(card.name)
            }
        }
    }

}

@Composable
fun CardRating(card: PokeCard ,modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
        Image(painter = card.image, contentDescription = "Carta ${card.name}", modifier = Modifier.size(450.dp).padding(10.dp))
        Text(text = card.name, style = MaterialTheme.typography.displayLarge)
        RatingBar(
            currentRating = card.rating,
            onRatingChanged = card.onRatingChange,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCartasTest() {
    CartasTest()
}