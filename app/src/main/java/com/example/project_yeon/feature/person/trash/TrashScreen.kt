package com.example.project_yeon.feature.person.trash

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.background
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.component.dialog.PermanentDeleteConfirmDialog
import com.example.project_yeon.domain.person.model.PersonListItem

@Composable
fun TrashScreen(
    navController: NavController,
    viewModel: TrashViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()
    val font_nanum_pen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is TrashEffect.NavigateBack -> {
                    navController.popBackStack()
                }
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.bg_paper_light),
                contentScale = ContentScale.Crop
            )
            .systemBarsPadding()
    ) {
        TrashScreenContent(
            persons = uiState.persons,
            fontNanumPen = font_nanum_pen,
            onBackClick = {
                viewModel.onEvent(TrashEvent.OnBackClick)
            },
            onDeleteClick = { personId ->
                viewModel.onEvent(TrashEvent.OnPermanentDeleteClick(personId))
            },
            onRestoreClick = { personId ->
                viewModel.onEvent(TrashEvent.OnRestoreClick(personId))
            }
        )

        if (uiState.showPermanentDeleteDialog) {
            PermanentDeleteConfirmDialog(
                fontNanumPen = font_nanum_pen,
                onDismiss = {
                    viewModel.onEvent(TrashEvent.OnDialogDismiss)
                },
                onConfirm = {
                    viewModel.onEvent(TrashEvent.OnPermanentDeleteConfirmClick)
                }
            )
        }
    }
}