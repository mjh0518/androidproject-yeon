package com.example.project_yeon.core.ui.component.inputFfiled

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.project_yeon.core.ui.theme.YeonOutline
import com.example.project_yeon.core.ui.theme.YeonSurface

@Composable
fun DateInputField(
    text: String?,
    placeholder: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.padding(top = 8.dp).padding(horizontal = 8.dp).height(60.dp),
    enabled: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ActionFieldContainer(
            text = text ?: "",
            placeholder = placeholder,
            onClick = onClick,
            enabled = enabled,
            icon = {
                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null
                )
            }
        )
    }
}