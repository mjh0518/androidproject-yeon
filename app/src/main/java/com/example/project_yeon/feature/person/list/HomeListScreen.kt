package com.example.project_yeon.feature.person.list

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.component.card.PersonExpandedSection
import com.example.project_yeon.core.ui.component.card.PersonListCard
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun HomeListScreen(
    navController: NavController,
    viewModel: HomeListViewModel = hiltViewModel(),
) {
    val font_nanum_pen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeListEffect.NavigateToAdd ->{
                    navController.navigate("add_person")
                }
                is HomeListEffect.NavigateToDetail -> {
                    navController.navigate("detail_Person/${effect.personId}")
                }
            }
        }
    }

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
            onNavigateToAdd = {viewModel.onEvent(HomeListEvent.OnAddClick)}
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            thickness = 3.dp,
            color = YeonTextOnBackGround.copy(alpha = 0.25f)
        )
        LazyColumn(modifier = Modifier.padding(12.dp)) {
            items(
                items = uiState.persons,
                key = { it.personId }
            ) { person ->
                Log.d("HomeListImage", "profileImageUri = ${person.profileImageUri}")
                PersonListCard(
                    profileimage = person.profileImageUri,
                    name = person.name,
                    intimacy = person.intimacy,
                    isExpanded = uiState.expandedPersonId == person.personId,
                    onExpandClick = {
                        viewModel.onEvent(HomeListEvent.OnExpandClick(person.personId))
                    }
                )
                if (uiState.expandedPersonId == person.personId) {
                    PersonExpandedSection(
                        person = person,
                        onMoreDetailClick = {
                            viewModel.onEvent(HomeListEvent.OnMoreDetailClick(person.personId))
                        }
                    )
                }
            }
        }
    }
}