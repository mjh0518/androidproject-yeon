package com.example.project_yeon.feature.person.list

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.project_yeon.R
import com.example.project_yeon.core.ui.component.button.PrimaryButton
import com.example.project_yeon.core.ui.component.card.PersonExpandedSection
import com.example.project_yeon.core.ui.component.card.PersonListCard
import com.example.project_yeon.core.ui.theme.YeonOnPrimary
import com.example.project_yeon.core.ui.theme.YeonTextMuted
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun HomeListScreen(
    navController: NavController,
    viewModel: HomeListViewModel = hiltViewModel(),
) {
    val font_nanum_pen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))
    val font_nanum_gyuri= FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))
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

    when{
        uiState.isLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(id = R.drawable.is_loading),
                        contentScale = ContentScale.Crop
                    )
                    .systemBarsPadding()
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = YeonTextMuted
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "잠시 기다려주세요.",
                        color = YeonTextOnBackGround,
                        fontFamily = font_nanum_gyuri,
                        fontSize = 28.sp,
                    )
                }
            }
        }
        uiState.errorMessage != null -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(id = R.drawable.bg_paper_light),
                        contentScale = ContentScale.Crop
                    )
                    .systemBarsPadding()
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "목록을 불러오지 못했어요.",
                        color = YeonTextOnBackGround,
                        fontFamily = font_nanum_gyuri,
                        fontSize = 30.sp,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "잠시 후 다시 시도해주세요.",
                        color = YeonTextMuted,
                        fontFamily = font_nanum_gyuri,
                        fontSize = 22.sp,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = uiState.errorMessage ?: "",
                        color = YeonTextMuted.copy(alpha = 0.8f),
                        fontFamily = font_nanum_gyuri,
                        fontSize = 16.sp,
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = {
                                viewModel.retryObservePersons()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = YeonTextMuted,
                            ),
                            shape = RoundedCornerShape(24.dp),
                        ) {
                            Text(
                                text = "다시 시도",
                                color = YeonOnPrimary,
                                fontFamily = font_nanum_gyuri,
                                fontSize = 22.sp,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }

                        OutlinedButton(
                            onClick = {
                                viewModel.onEvent(HomeListEvent.OnAddClick)
                            },
                            shape = RoundedCornerShape(24.dp),
                            border = BorderStroke(1.5.dp, YeonTextMuted)
                        ) {
                            Text(
                                text = "인연 추가",
                                color = YeonTextOnBackGround,
                                fontFamily = font_nanum_gyuri,
                                fontSize = 22.sp,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }
        uiState.isEmpty ->{
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(id = R.drawable.empty_list),
                        contentScale = ContentScale.Crop
                    )
                    .systemBarsPadding()
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 36.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(128.dp))
                    Button(
                        onClick = {viewModel.onEvent(HomeListEvent.OnAddClick)},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = YeonTextMuted,),
                        modifier = Modifier.padding(24.dp),
                        shape = RoundedCornerShape(24.dp),
                    ){
                        Text(
                            text = "인연 추가하기",
                            color = YeonOnPrimary,
                            fontFamily = font_nanum_gyuri,
                            fontSize = 32.sp,
                        )
                    }
                }
            }
        }
        else -> {
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
    }
    Log.d(
        "HomeListState",
        "isLoading=${uiState.isLoading}, error=${uiState.errorMessage}, isEmpty=${uiState.isEmpty}, size=${uiState.persons.size}"
    )
}