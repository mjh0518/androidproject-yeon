package com.example.project_yeon.feature.person.add

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.project_yeon.core.ui.component.button.PrimaryButton
import com.example.project_yeon.core.ui.component.button.SecondaryButton
import com.example.project_yeon.core.ui.component.inputFfiled.DateInputField
import com.example.project_yeon.core.ui.component.inputFfiled.ExpandableTextField
import com.example.project_yeon.core.ui.component.inputFfiled.KeywordInputField
import com.example.project_yeon.core.ui.component.inputFfiled.YeonOutlinedTextField

@Composable
fun AddPersonScreen(
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DateInputField(
            label = "",
            text = "",
            placeholder = "날짜를 선택해주세요",
            onClick = {},
            modifier = Modifier.padding(24.dp)
        )
        KeywordInputField(
            label = "",
            text = "",
            placeholder = "항목을 추가해주세요",
            onClick = {},
            modifier = Modifier.padding(24.dp)
        )
        YeonOutlinedTextField("", {}, "", "테스트P",Modifier.padding(24.dp),)
        ExpandableTextField("", {}, "", "테스트P",Modifier.padding(24.dp),)
        Text("AddPerson Screen")
        PrimaryButton("인연 추가하기", {} , modifier = Modifier.padding(24.dp),true)
        SecondaryButton(onBackClick)
    }
}