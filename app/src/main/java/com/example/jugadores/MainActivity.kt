package com.example.jugadores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jugadores.ui.theme.JugadoresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JugadoresTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JugadorList(jugadores = getListaJugadores())
                }
            }
        }
    }
}

@Composable
fun JugadorList(jugadores: List<Jugador>) {
    val selectedJugador = remember { mutableStateOf<Jugador?>(null) }

    LazyColumn {
        items(jugadores) { jugador ->
            JugadorCard(jugador = jugador) {
                selectedJugador.value = jugador
            }
        }
    }

    selectedJugador.value?.let { jugador ->
        JugadorDetail(jugador = jugador) {
            selectedJugador.value = null
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JugadorCard(jugador: Jugador, onClick: () -> Unit) {
    Card(
        onClick = onClick
    ) {
        Column {
            Image(
                painter = painterResource(id = jugador.imagenId),
                contentDescription = jugador.nombre,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
            )
            Text(text = jugador.nombre)
            Text(text = jugador.posicion)
            Text(text = jugador.equipo)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JugadorDetail(jugador: Jugador, onClose: () -> Unit) {
    Card(
        onClick = onClose
    ) {
        Column {
            Image(
                painter = painterResource(id = jugador.imagenId),
                contentDescription = jugador.nombre,
                modifier = Modifier
                    .width(400.dp)
                    .height(400.dp)
            )
            Text(text = jugador.nombre)
            Text(text = jugador.posicion)
            Text(text = jugador.equipo)
        }
    }
}

@Preview
@Composable
fun PreviewJugadorList() {
    val jugadores = getListaJugadores()
    JugadorList(jugadores = jugadores)
}

// Declaración de la clase Jugador
data class Jugador(val nombre: String, val posicion: String, val equipo: String, val imagenId: Int)

// Ejemplo de función para obtener una lista de jugadores (puedes reemplazarla con tus datos)
fun getListaJugadores(): List<Jugador> {
    return listOf(
        Jugador("Lionel Messi", "Delantero", "FC Barcelona", R.drawable.messi),
        Jugador("Cristiano Ronaldo", "Delantero", "Manchester United", R.drawable.cr7),
        Jugador("Neymar Jr.", "Delantero", "Paris Saint-Germain", R.drawable.neymar),
        Jugador("Kylian Mbappé", "Delantero", "Paris Saint-Germain", R.drawable.mbappe),
        Jugador("Robert Lewandowski", "Delantero", "Bayern Munich", R.drawable.lewadosli)
    )
}