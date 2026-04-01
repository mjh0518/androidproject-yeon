package com.example.project_yeon.feature.person.modify

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*

@Composable
fun ModifyPersonScreen(
    personId: Long,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("ModifyPerson Screen")
        Text("personId = $personId")

        Button(onClick = onBackClick) {
            Text("뒤로가기")
        }
    }
}