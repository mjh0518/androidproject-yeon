package com.example.project_yeon.feature.person.list

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*

@Composable
fun HomeListScreen(
    onNavigateToAdd: () -> Unit,
    onNavigateToDetail: (Long) -> Unit,
    onNavigateToTrash: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("HomeList Screen")

        Button(onClick = onNavigateToAdd) {
            Text("AddPerson으로 이동")
        }

        Button(onClick = { onNavigateToDetail(1L) }) {
            Text("DetailPerson(1)으로 이동")
        }

        Button(onClick = onNavigateToTrash) {
            Text("Trash로 이동")
        }
    }
}