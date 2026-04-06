package com.example.project_yeon.core.ui.component.inputFfiled

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.project_yeon.core.ui.etc.ActionFieldTrailingIcon

@Composable
fun AddressInputField(
    text: String?,
    placeholder: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .padding(top = 8.dp)
        .padding(horizontal = 8.dp)
        .height(60.dp),
    enabled: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        ActionFieldContainer(
            text = text ?: "",
            placeholder = placeholder,
            onClick = onClick,
            enabled = enabled,
            icon = {
                ActionFieldTrailingIcon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "거주지 선택"
                )
            }
        )
    }
}