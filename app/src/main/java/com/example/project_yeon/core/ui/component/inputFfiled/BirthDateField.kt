package com.example.project_yeon.core.ui.component.inputFfiled

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.project_yeon.core.ui.etc.ActionFieldTrailingIcon
import java.time.LocalDate

@Composable
fun BirthDateField(
    birthDate: LocalDate?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ActionFieldContainer(
        text = birthDate?.toString().orEmpty(),
        placeholder = "생년월일을 입력하세요",
        onClick = onClick,
        icon = {
            ActionFieldTrailingIcon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "생년월일 선택"
            )
        },
        modifier = modifier.fillMaxWidth()
    )
}