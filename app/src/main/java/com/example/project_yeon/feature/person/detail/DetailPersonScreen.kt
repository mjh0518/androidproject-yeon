package com.example.project_yeon.feature.person.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
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
import com.example.project_yeon.app.navigation.AppRoute
import com.example.project_yeon.core.ui.theme.YeonOnPrimary
import com.example.project_yeon.core.ui.theme.YeonTextMuted
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPersonScreen(
    navController: NavController,
    viewModel: DetailPersonViewModel = hiltViewModel(),
) {
    val font_nanum_pen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))
    val font_nanum_gyuri = FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))
    val uiState by viewModel.uiState.collectAsState()

    val currentBackStackEntry = navController.currentBackStackEntry
    val updated by currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow("person_updated", false)
        ?.collectAsState()
        ?: remember { mutableStateOf(false) }

    LaunchedEffect(updated) {
        if (updated) {
            viewModel.reloadPersonDetail()
            currentBackStackEntry?.savedStateHandle?.set("person_updated", false)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                DetailPersonEffect.NavigateBack -> {
                    navController.popBackStack()
                }

                is DetailPersonEffect.NavigateToModify -> {
                    navController.navigate(
                        "modify_person/${effect.personId}"
                    )
                }

                DetailPersonEffect.RequestSensitiveAuth -> {
                    // FR-04에서 처리
                }

                is DetailPersonEffect.ShowSnackbar -> {
                    // 필요하면 snackbar 처리
                }
            }
        }
    }

    Column(
        modifier = Modifier.paint(
            painterResource(id = R.drawable.bg_paper_light),
            contentScale = ContentScale.Crop
        )
    ) {

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        scrolledContainerColor = Color.Transparent,
                        navigationIconContentColor = YeonTextOnBackGround,
                        titleContentColor = Color(0xFF8FA2FF),
                        actionIconContentColor = YeonTextOnBackGround
                    ),
                    title = {
                        Text(
                            text = "${uiState.person.name}님과의 기억",
                            modifier = Modifier.weight(1f),
                            color = Color(0xFF8FA2FF),
                            fontFamily = font_nanum_gyuri,
                            fontSize = 26.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { viewModel.onEvent(DetailPersonEvent.OnBackClick) }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "뒤로가기"
                            )
                        }
                    },
                    actions = {
                        OutlinedButton(
                            onClick = {viewModel.onEvent(DetailPersonEvent.OnModifyClick)},
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.45f)),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.White.copy(alpha = 0.65f),
                                contentColor = YeonTextOnBackGround
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "수정하기",
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "수정하기",
                                fontFamily = font_nanum_pen,
                                fontSize = 16.sp
                            )
                        }
                    }

                )
            }
        ) { innerPadding ->
            when {
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
                                text = "오류가 발생했습니다.",
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
                                        viewModel.onEvent(DetailPersonEvent.OnRetryClick)
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
                                        modifier = Modifier.padding(
                                            horizontal = 6.dp,
                                            vertical = 2.dp
                                        )
                                    )
                                }
                            }
                        }
                    }
                }

                uiState.person != null -> {

                    val person = uiState.person

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        item {
                            DetailProfileHeader(person = person, onModifyClick = {
                                viewModel.onEvent(
                                    DetailPersonEvent.OnModifyClick
                                )
                            })
                        }
                        item { DetailBasicInfoSection(person) }
                        item { DetailMemorySection(person) }
                        item {
                            DetailSensitiveInfoSection(
                                person = person,
                                /*unlocked = uiState.isSensitiveInfoUnlocked,
                                onUnlockClick = {
                                    viewModel.onEvent(DetailPersonEvent.OnSensitiveInfoClick)
                                }*/
                            )
                        }
                        item {
                            DetailMemoSection(memo = person.memo)
                        }
                    }
                }

                else -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("상세 정보를 불러올 수 없습니다.")
                    }
                }
            }
        }
    }
}