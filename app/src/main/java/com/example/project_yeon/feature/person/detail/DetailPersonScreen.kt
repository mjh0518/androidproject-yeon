package com.example.project_yeon.feature.person.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.*
@Composable
fun DetailPersonScreen(
    personId: Long,
    onBackClick: () -> Unit,
    onNavigateToModify: (Long) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("DetailPerson Screen")
        Text("personId = $personId")

        Button(onClick = { onNavigateToModify(personId) }) {
            Text("ModifyPerson으로 이동")
        }

        Button(onClick = onBackClick) {
            Text("뒤로가기")
        }
    }
}