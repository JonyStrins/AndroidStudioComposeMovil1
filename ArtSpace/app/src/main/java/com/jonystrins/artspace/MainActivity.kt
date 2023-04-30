package com.jonystrins.artspace

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jonystrins.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    val primerObra = R.drawable.ghost_rider
    val segundaObra = R.drawable.god_of_war
    val tercerObra = R.drawable.god_of_war_ii
    val cuartaObra = R.drawable.manhunt
    val quintaObra = R.drawable.grand_theft_auto_sa

    var titulo by remember {
        mutableStateOf(R.string.primer_titulo)
    }

    var anio by remember{
        mutableStateOf(R.string.primer_anio)
    }

    var artePresentado by remember {
        mutableStateOf(primerObra)
    }

//    var imagenPresentada by remember {
//        mutableStateOf(artePresentado)
//    }
    
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArteDesplegado(artePresentado = artePresentado)
        Spacer(modifier = modifier.size(16.dp))
        TituloDelArte(titulo = titulo, anio = anio)
        Spacer(modifier = modifier.size(25.dp))
        Row(
            modifier = modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = {
                    when (artePresentado) {
                        primerObra -> {
                            artePresentado = quintaObra
                            titulo = R.string.quinto_titulo
                            anio = R.string.quinto_anio
                        }
                        segundaObra -> {
                            artePresentado = primerObra
                            titulo = R.string.primer_titulo
                            anio = R.string.primer_anio
                        }
                        tercerObra -> {
                            artePresentado = segundaObra
                            titulo = R.string.segundo_titulo
                            anio = R.string.segundo_anio
                        }
                        cuartaObra -> {
                            artePresentado = tercerObra
                            titulo = R.string.tercer_titulo
                            anio = R.string.tercer_anio
                        }
                        else -> {
                            artePresentado = cuartaObra
                            titulo = R.string.cuarto_titulo
                            anio = R.string.cuarto_anio
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.gray_900)
                ),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp
                )
            ) {
                Text(
                    text = "Previous",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.blue_300)
                )
            }
            
            Button(
                onClick = {
                    when (artePresentado) {
                        primerObra -> {
                            artePresentado = segundaObra
                            titulo = R.string.segundo_titulo
                            anio = R.string.segundo_anio
                        }
                        segundaObra -> {
                            artePresentado = tercerObra
                            titulo = R.string.tercer_titulo
                            anio = R.string.tercer_anio
                        }
                        tercerObra -> {
                            artePresentado = cuartaObra
                            titulo = R.string.cuarto_titulo
                            anio = R.string.cuarto_anio
                        }
                        cuartaObra -> {
                            artePresentado = quintaObra
                            titulo = R.string.quinto_titulo
                            anio = R.string.quinto_anio
                        }
                        else -> {
                            artePresentado = primerObra
                            titulo = R.string.primer_titulo
                            anio = R.string.primer_anio
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.blue_300)
                ),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp
                )
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.gray_900)
                )
            }
        }
    }
}

@Composable
fun ArteDesplegado(
    modifier: Modifier = Modifier,
    @DrawableRes artePresentado: Int
){
    Image(
        painter = painterResource(id = artePresentado),
        contentDescription = stringResource(id = R.string.segundo_titulo),
        modifier = modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun TituloDelArte(
    @StringRes titulo: Int,
    @StringRes anio: Int
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = titulo),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.blue_100),
            fontSize = 32.sp
        )
        Text(
            text = stringResource(id = anio),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.gray_300)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}