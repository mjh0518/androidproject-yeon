package com.example.project_yeon.feature.person.trash

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround
import com.example.project_yeon.feature.person.trash.model.TrashPersonUiModel

@Composable
fun TrashScreenContent(
    persons: List<TrashPersonUiModel>,
    fontNanumPen: FontFamily,
    onBackClick: () -> Unit,
    onDeleteClick: (Long) -> Unit,
    onRestoreClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        TrashScreenHeader(
            fontNanumPen = fontNanumPen,
            onBackClick = onBackClick
        )


        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 4.dp),
            thickness = 3.dp,
            color = YeonTextOnBackGround.copy(alpha = 0.25f)
        )

        TrashScreenGuideText(
            fontNanumPen = fontNanumPen
        )

        LazyColumn(
            modifier = Modifier.padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = persons,
                key = { it.personId }
            ) { person ->
                TrashPersonCard(
                    person = person,
                    fontNanumPen = fontNanumPen,
                    onDeleteClick = { onDeleteClick(person.personId) },
                    onRestoreClick = { onRestoreClick(person.personId) }
                )
            }
        }
    }
}