package com.example.lab2;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Artwork(
    val imageRes: Int,
    val title: String,
    val author: String,
    val year: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceApp()
        }
    }
}

@Composable
fun ArtSpaceApp() {
    val artworks = listOf(
        Artwork(R.drawable.painting1, "Мона Лиза", "Леонардо да Винчи", 1503),
        Artwork(R.drawable.painting2, "Сатурн, пожирающий своего сына", "Франсиско Гойя", 1819),
        Artwork(R.drawable.painting3, "Девушка с жемчужной серёжкой", "Ян Вермеер", 1665),
        Artwork(R.drawable.painting4, "Девочка с персиками", "Валентин Серов", 1887),
        Artwork(R.drawable.painting5, "Крик", "Эдвард Мунк", 1893)
    )

    var currentIndex by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkDisplay(artworks[currentIndex])
        Spacer(modifier = Modifier.height(16.dp))
        ArtworkInfo(artworks[currentIndex])
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavigationButtons(
            onNext = { currentIndex = (currentIndex + 1) % artworks.size },
            onPrevious = { currentIndex = (currentIndex - 1 + artworks.size) % artworks.size }
        )
    }
}

@Composable
fun ArtworkDisplay(artwork: Artwork) {
    val painter: Painter = painterResource(id = artwork.imageRes)
    Image(
        painter = painter,
        contentDescription = artwork.title,
        modifier = Modifier
            .fillMaxHeight(0.7f)
            .aspectRatio(1f)
            .padding(8.dp)
    )
}

@Composable
fun ArtworkInfo(artwork: Artwork) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = artwork.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "${artwork.author} (${artwork.year})",
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun NavigationButtons(onNext: () -> Unit, onPrevious: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPrevious,
            modifier = Modifier.padding(8.dp).width(128.dp),
            shape = RoundedCornerShape(8.dp)

        ) {
            Text("Назад")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = onNext,
            modifier = Modifier.padding(8.dp).width(128.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Вперёд")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceApp()
}