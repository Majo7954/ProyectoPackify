package com.ucb.deliveryapp.ui.screens.home

import androidx.compose.foundation.Image
import com.ucb.deliveryapp.ui.screens.MapLibreView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextOverflow
import kotlinx.coroutines.launch
import com.ucb.deliveryapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var origin by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var size by remember { mutableStateOf("") }
    var quotedPrice by remember { mutableStateOf("") }
    var withinDepartment by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Página Principal") })
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(12.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    MapLibreView(modifier = Modifier.fillMaxSize())
                }

                Spacer(modifier = Modifier.height(12.dp))

                FieldCard(label = "Lugar de origen", value = origin, onClick = {  }) { origin = "" }
                Spacer(modifier = Modifier.height(8.dp))
                FieldCard(label = "Lugar de destino", value = destination, onClick = {  }) { destination = "" }
                Spacer(modifier = Modifier.height(8.dp))
                FieldCard(label = "Peso", value = weight, onClick = {  }) { weight = "" }
                Spacer(modifier = Modifier.height(8.dp))
                FieldCard(label = "Tamaño", value = size, onClick = {  }) { size = "" }
                Spacer(modifier = Modifier.height(8.dp))
                FieldCard(label = "Precio Cotizado", value = quotedPrice, onClick = {  }) { quotedPrice = "" }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Envío dentro del departamento:")
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(checked = withinDepartment, onCheckedChange = { withinDepartment = it })
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        scope.launch {
                            val msg = if (origin.isNotBlank() && destination.isNotBlank()) {
                                "Envío confirmado"
                            } else {
                                "Completa origen y destino"
                            }
                            snackbarHostState.showSnackbar(msg)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text("Confirmar")
                }
            }
        }
    )
}

@Composable
private fun FieldCard(
    label: String,
    value: String,
    onClick: () -> Unit,
    onClear: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = label, style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.height(4.dp))
                Text(
                    text = if (value.isBlank()) "Agregar $label" else value,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            IconButton(onClick = onClear) {
                Icon(Icons.Default.Close, contentDescription = "Limpiar $label")
            }
        }
    }
}
