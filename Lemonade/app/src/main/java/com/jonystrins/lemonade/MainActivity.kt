package com.jonystrins.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jonystrins.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp(){
    var preparacion by remember {
        mutableStateOf(1)
    }
    var cont by remember {
        mutableStateOf(1)
    }
    var rand = (1..6).random()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (preparacion) {
            1 -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(
                        text = stringResource(id = R.string.tap_the_lemon_tree)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.lemon_tree),
                        contentDescription = stringResource(id = R.string.lemon_tree),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                preparacion = 2
                            }
                    )
                }
            }
            2 -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(
                        text = stringResource(id = R.string.keep_tapping_the_lemon)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.lemon_squeeze),
                        contentDescription = stringResource(id = R.string.lemon),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                if(rand==cont){
                                    preparacion = 3
                                    cont = 1
                                    rand = (1..6).random()
                                }else{
                                    cont++
                                }
                            }
                        )
                }
            }
            3 -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(
                        text = stringResource(id = R.string.tap_the_lemonade)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.lemon_drink),
                        contentDescription = stringResource(id = R.string.glass_of_lemonade),
                        modifier = Modifier.wrapContentSize().clickable {
                            preparacion = 4
                        }
                    )
                }
            }
            4 -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(
                        text = stringResource(id = R.string.tap_the_empty_glass)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.lemon_restart),
                        contentDescription = stringResource(id = R.string.empty_glass),
                        modifier = Modifier.wrapContentSize().clickable {
                            preparacion = 1
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}