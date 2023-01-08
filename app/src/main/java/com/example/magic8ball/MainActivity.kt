package com.example.magic8ball

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.magic8ball.ui.theme.Magic8BallTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Magic8BallTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldComposable()
                }
            }
        }
    }
}

@Composable
fun ScaffoldComposable() {
    Scaffold(
        topBar = { AppBar()},
        content = { Body() }
    )
}

@Composable
fun AppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Magic-8-Ball",
                style = MaterialTheme.typography.h1
            )
        }
    )
}



@Composable
fun Body() {
    
    val localConfiguration = LocalConfiguration.current
    val width = localConfiguration.screenWidthDp
//    val height = localConfiguration.screenHeightDp

    var index by remember { mutableStateOf(8) }

    fun getReponse(): Pair<Int, String> {
        return when (index) {
            0 -> R.drawable.good_emoji to "Bien sûr"
            1 -> R.drawable.negativ_emoji to "Certainement pas"
            2 -> R.drawable.faut_pas_rever_emoji to "Faut pas rêver"
            3-> R.drawable.essaie_plus_tard_emoji to "Essaie plus tard"
            4 -> R.drawable.maybe_emoji to "Peut-être"
            5 -> R.drawable.probable_emoji  to "C'est très probable"
            6 -> R.drawable.try_again_emoji to "Clique encore"
            7 -> R.drawable.sais_pas_emoji to "Je sais pas"
            else -> R.drawable.galaxy to "Vous etes pret !"
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Posez votre question ?",
            modifier = Modifier.padding(16.dp),
        )
        Image(
            painter = painterResource(id = R.drawable.magic_8_ball),
            contentDescription = null,
            modifier = Modifier
                .width((width / 2).dp)
                .padding(18.dp)
                .clip(RoundedCornerShape(35))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TextButton(onClick = {
                index = (0..7).random()
            }) {
                Text(
                    text = "C'est parti !!",
                    style = MaterialTheme.typography.body1
                )
            }
            IconButton(onClick = { index = 8 }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                )
            }
        }
        Spacer(modifier = Modifier.height(80.dp))
        Image(
            painter = painterResource(id = getReponse().first),
            contentDescription = null,
            modifier = Modifier
                .padding(20.dp),
            contentScale = ContentScale.Crop,
        )
        Text(text= getReponse().second)
      }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Magic8BallTheme {
        ScaffoldComposable()
    }
}