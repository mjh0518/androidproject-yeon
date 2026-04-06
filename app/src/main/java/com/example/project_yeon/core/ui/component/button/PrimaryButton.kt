package com.example.project_yeon.core.ui.component.button

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.theme.*

@Composable

fun PrimaryButton(
    text : String,
    onClick : () -> Unit,
    modifier : Modifier = Modifier,
    enabled : Boolean = true,
){
    val font_nanum_gyuri= FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = YeonTextMuted,),
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        enabled = enabled,
    ){
        Text(
            text = text,
            color = YeonOnPrimary,
            fontFamily = font_nanum_gyuri,
            fontSize = 32.sp,
        )
    }
}