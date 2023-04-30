package com.jonystrins.creaunacuadricula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jonystrins.creaunacuadricula.data.DataSource
import com.jonystrins.creaunacuadricula.model.Topic
import com.jonystrins.creaunacuadricula.ui.theme.CreaUnaCuadriculaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreaUnaCuadriculaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CuadriculaApp(
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CuadriculaApp(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ){
        items(DataSource.topics){
            topic -> Carta(topic = topic)
        }
    }
}

@Composable
fun Carta(topic: Topic, modifier: Modifier = Modifier){
    Card() {
        Row{
            Box{
               Image(
                   painter = painterResource(id = topic.imageRes),
                   contentDescription = null,
                   modifier = modifier
                       .size(width = 68.dp, height = 68.dp)
                       .aspectRatio(1f),
                   contentScale = ContentScale.Crop
               ) 
            }
            Column() {
                Text(
                    text = stringResource(id = topic.name),
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier.padding(
                            start = 16.dp
                        )
                    )
                    Text(
                        text = topic.availableCourses.toString(),
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.padding(
                            start = 8.dp
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CreaUnaCuadriculaTheme() {
        val topic = Topic(R.string.photography, 321, R.drawable.photography)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Carta(topic = topic)
        }
    }
}