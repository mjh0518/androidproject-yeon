package com.example.project_yeon.core.ui.component.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.theme.YeonOutline
import com.example.project_yeon.core.ui.theme.YeonOutline_2
import com.example.project_yeon.core.ui.theme.YeonSurface
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround
import java.io.File

@Composable
fun PersonListCard(
    profileimage: String? = null,
    name: String,
    intimacy: Int,
    isExpanded: Boolean = false,
    onExpandClick: () -> Unit,
) {
    val font_nanum_gyuri = FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))

    val alphaofintimacy = when(intimacy){
        1->{
            0.2f
        }
        2->{
            0.4f
        }
        3->{
            0.6f
        }
        4->{
            0.8f
        }
        else->{
            1f
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(12.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(3.dp, YeonOutline),
        colors = CardDefaults.cardColors(
            containerColor = YeonSurface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp, end = 12.dp, top = 12.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(4.dp)
                    ),
            ) {
                AsyncImage(
                    model = if (profileimage.isNullOrBlank()) {
                        R.drawable.profile_default
                    } else {
                        File(profileimage)
                    },
                    contentDescription = "프로필 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    error = painterResource(R.drawable.profile_default),
                    fallback = painterResource(R.drawable.profile_default),
                    placeholder = painterResource(R.drawable.profile_default)
                )
            }
            Spacer(modifier = Modifier.width(30.dp))
            Text(
                text = name,
                color = YeonTextOnBackGround,
                fontFamily = font_nanum_gyuri,
                modifier = Modifier.padding(top = 8.dp),
                fontSize = 24.sp,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.2f),
                        offset = Offset(8f, 8f),
                        blurRadius = 2f
                    )
                )
            )
            Spacer(modifier = Modifier.width(30.dp))
            Row(
                modifier = Modifier.padding(top = 8.dp)
            ){
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "",
                    modifier = Modifier.size(26.dp),
                    tint = Color.Yellow.copy(alpha = alphaofintimacy)                     // 색상 변경
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "$intimacy/5",
                    color = YeonTextOnBackGround,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 22.sp,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.2f),
                            offset = Offset(8f, 8f),
                            blurRadius = 2f
                        )
                    )
                )
            }
            Spacer(modifier = Modifier.width(50.dp))
            IconButton(
                onClick = onExpandClick,
                modifier = Modifier.size(36.dp).padding(top = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "",
                    modifier = Modifier.size(36.dp),
                    tint = YeonOutline_2
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            IconButton(
                onClick = {},
                modifier = Modifier.size(36.dp).padding(top = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "",
                    modifier = Modifier.size(36.dp),
                    tint = Color.Black
                )
            }
        }
    }
}
