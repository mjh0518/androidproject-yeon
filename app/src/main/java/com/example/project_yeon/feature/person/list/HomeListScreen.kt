package com.example.project_yeon.feature.person.list

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.component.card.AppCard
import com.example.project_yeon.core.ui.component.card.PersonListCard
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun HomeListScreen(
    onNavigateToAdd: () -> Unit,
) {
    val font_nanum_pen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))
    val font_nanum_gyuri = FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))


    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.bg_paper_light),
                contentScale = ContentScale.Crop
            )
            .systemBarsPadding(),
    ) {
        HomeListHeader(
            fontNanumPen = font_nanum_pen,
            onNavigateToAdd = onNavigateToAdd
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
            thickness = 3.dp,
            color = YeonTextOnBackGround.copy(alpha = 0.25f)
        )
        PersonListCard(null,"테스트",3,false, {})
    }
}