package com.example.drillar.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.drillar.model.Drill
import com.example.drillar.model.drills

@Composable
fun DrillSelectionScreen(navController: NavController) {
    var selectedDrill by remember { mutableStateOf(drills.first()) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text( text = "Select Drill", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        DropdownMenuDrill(drills, selectedDrill) { selectedDrill = it }

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = rememberAsyncImagePainter(selectedDrill.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Description:", fontWeight = FontWeight.Bold)
        Text(selectedDrill.description)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Tips:", fontWeight = FontWeight.Bold)
        Text(selectedDrill.tips)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            navController.navigate("ar_screen/${selectedDrill.name}")
        }) {
            Text(text = "Start AR Drill")
        }
    }
}

@Composable
fun DropdownMenuDrill(drillList: List<Drill>, selected: Drill, onSelect: (Drill) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(selected.name)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            drillList.forEach { drill ->
                DropdownMenuItem(
                    text = { Text(drill.name) },
                    onClick = {
                        onSelect(drill)
                        expanded = false
                    }
                )
            }
        }
    }
}