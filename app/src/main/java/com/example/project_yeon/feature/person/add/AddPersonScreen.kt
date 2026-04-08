package com.example.project_yeon.feature.person.add

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.project_yeon.core.ui.component.button.PrimaryButton
import com.example.project_yeon.core.ui.component.button.SecondaryButton
import com.example.project_yeon.core.ui.component.inputFfiled.DateInputField
import com.example.project_yeon.core.ui.component.inputFfiled.ExpandableTextField
import com.example.project_yeon.core.ui.component.inputFfiled.HyperlinkInputField
import com.example.project_yeon.core.ui.component.inputFfiled.KeywordInputField
import com.example.project_yeon.core.ui.component.inputFfiled.YeonOutlinedTextField
import com.example.project_yeon.core.ui.etc.DropdownSelectorField
import com.example.project_yeon.core.ui.etc.RatingBar
import com.example.project_yeon.core.ui.theme.YeonTextMuted
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround
import androidx.lifecycle.viewmodel.compose.*
import coil.compose.AsyncImage
import com.example.project_yeon.core.ui.component.dialog.BirthDatePickerDialog
import com.example.project_yeon.core.ui.component.inputFfiled.BirthDateField
import com.example.project_yeon.core.ui.component.dialog.ChoiceProfilePhotoDialog
import com.example.project_yeon.core.ui.etc.MemoryImageSectionContainer
import com.example.project_yeon.feature.person.add.model.ProfileImageState

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPersonScreen(
    onBackClick: () -> Unit,
    viewModel: AddPersonViewModel = viewModel()
) {
    val font_nanum_pen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))
    val font_nanum_gyuri = FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))

    val uiState by viewModel.uiState.collectAsState()

    val mbtiList = listOf(
        "ISTJ", "ISFJ", "INFJ", "INTJ",
        "ISTP", "ISFP", "INFP", "INTP",
        "ESTP", "ESFP", "ENFP", "ENTP",
        "ESTJ", "ESFJ", "ENFJ", "ENTJ"
    )

    val personalityList = listOf(
        "밝은", "차분한", "다정한", "활발한", "성실한", "꼼꼼한", "유쾌한", "조용한", "솔직한",
        "배려심 있는", "책임감 있는", "감성적인", "친절한", "명랑한", "적극적인", "신중한", "유머러스한", "섬세한", "자유로운",
        "상냥한", "온화한", "든든한", "진지한", "긍정적인", "낙천적인", "부드러운", "세심한", "깔끔한", "차가운", "도도한",
        "순수한", "수줍은", "당당한", "열정적인", "호기심 많은", "사교적인", "내성적인", "외향적인", "침착한", "똑부러진", "현실적인",
        "이성적인", "로맨틱한", "귀여운", "엉뚱한", "편안한", "진솔한", "성숙한", "독립적인"
    )

    var expandedOfMbti by remember { mutableStateOf(false) }
    var expandedOfPersonal by remember { mutableStateOf(false) }
    var showBirthDatePicker by rememberSaveable { mutableStateOf(false) }
    var showChoiceProfileDialog by remember { mutableStateOf(false) }

    // 프로필 사진 전용 Picker(단일 사진)
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        // 결과를 ViewModel로 올림 → 미리보기 표시
        if (uri != null) {
            viewModel.onEvent(
                AddPersonEvent.CoreInfo.ProfileImageChanged(
                    ProfileImageState.Custom(uri.toString())
                )
            )
        }
    }

    //함께한 사진 전용 Picker(다중)
    val memoryPhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 20)
    ) { uris: List<Uri> ->
        if (uris.isNotEmpty()) {
            viewModel.onEvent(
                AddPersonEvent.AdditionalInfo.MemoryImagesAdded(
                    uris.map { it.toString() }
                )
            )
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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 상단 영역 (Row -> TopBar 영역으로 이전 고민)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SecondaryButton(onBackClick, modifier = Modifier.padding(16.dp))
            Text(
                "새로운 인연 추가",
                color = YeonTextOnBackGround,
                fontFamily = font_nanum_pen,
                fontSize = 32.sp,
            )
            Spacer(modifier = Modifier.padding(16.dp))
        }
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .weight(6f)
        ) {
            //프로필 사진
            item {
                Box(
                    modifier = Modifier
                        .width(256.dp)
                        .height(256.dp)
                        .padding(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(20.dp))
                            .border(
                                width = 2.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        when (val imageState = uiState.coreInfo.profileImageUri) {
                            ProfileImageState.Default -> {
                                Image(
                                    painter = painterResource(R.drawable.profile_default),
                                    contentDescription = "프로필 이미지",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            is ProfileImageState.Custom -> {
                                AsyncImage(
                                    model = imageState.uri,
                                    contentDescription = "프로필 썸네일",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }

                    IconButton(
                        onClick = {
                            showChoiceProfileDialog = true
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(36.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(
                                width = 2.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .background(Color.White)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "이미지 추가",
                            modifier = Modifier.size(20.dp),
                            tint = Color.DarkGray
                        )
                    }
                    ChoiceProfilePhotoDialog(
                        showDialog = showChoiceProfileDialog,
                        onSelectDefaultImage = {
                            viewModel.onEvent(
                                AddPersonEvent.CoreInfo.ProfileImageChanged(
                                    ProfileImageState.Default
                                )
                            )
                        },
                        onSelectGalleryImage = {
                            photoPicker.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        },
                        onDismiss = {showChoiceProfileDialog = false}
                    )
                }
            }
            //이름
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "이름",
                        color = YeonTextMuted,
                        fontFamily = font_nanum_gyuri,
                        fontSize = 32.sp,
                    )
                    Text(
                        text = "*",
                        color = Color.Red
                    )
                }
                YeonOutlinedTextField(uiState.coreInfo.name, {viewModel.onEvent(AddPersonEvent.NameChanged(it))}, "", "")
            }
            //성별
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "성별",
                        color = YeonTextMuted,
                        fontFamily = font_nanum_gyuri,
                        fontSize = 32.sp,
                    )
                    Text(
                        text = "*",
                        color = Color.Red
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (uiState.coreInfo.gender == Gender.MALE),
                            onClick = { viewModel.onEvent(AddPersonEvent.GenderChanged(Gender.MALE))},
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color.Blue,
                            )
                        )
                        Text(
                            text = "남",
                            color = YeonTextOnBackGround,
                            fontFamily = font_nanum_pen,
                            fontSize = 24.sp,
                        )
                    }
                    Spacer(modifier = Modifier.width(80.dp))
                    Row(
                        modifier = Modifier
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (uiState.coreInfo.gender == Gender.FEMALE),
                            onClick = { viewModel.onEvent(AddPersonEvent.GenderChanged(Gender.FEMALE))},
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color.Red,
                            )
                        )
                        Text(
                            text = "여",
                            color = YeonTextOnBackGround,
                            fontFamily = font_nanum_pen,
                            fontSize = 24.sp,
                        )
                    }
                }
            }
            //생년월일
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "생년월일",
                        color = YeonTextMuted,
                        fontFamily = font_nanum_gyuri,
                        fontSize = 32.sp,
                    )
                    Text(
                        text = "*",
                        color = Color.Red
                    )
                }
                BirthDateField(
                    birthDate = uiState.coreInfo.birthDate,
                    onClick = {showBirthDatePicker = true}
                )
                if(showBirthDatePicker){
                    BirthDatePickerDialog(
                        initialDate = uiState.coreInfo.birthDate,
                        onDismiss = { showBirthDatePicker = false },
                        onConfirm = { selectedDate ->
                            viewModel.onEvent(
                                AddPersonEvent.CoreInfo.BirthDateChanged(selectedDate)
                            )
                            showBirthDatePicker = false
                        }
                    )
                }
            }
            //친밀도
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "당신과의 친밀도",
                        color = YeonTextMuted,
                        fontFamily = font_nanum_gyuri,
                        fontSize = 32.sp,
                    )
                    Text(
                        text = "*",
                        color = Color.Red
                    )
                }
                RatingBar(uiState.coreInfo.intimacy, onRatingChanged = { viewModel.onEvent(AddPersonEvent.IntimacyChanged(it))})
            }
            //MBTI
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "MBTI",
                        color = YeonTextMuted,
                        fontFamily = font_nanum_gyuri,
                        fontSize = 32.sp,
                    )
                    Text(
                        text = "*",
                        color = Color.Red
                    )
                }
                DropdownSelectorField(
                    value = uiState.coreInfo.mbti,
                    expanded = expandedOfMbti,
                    onExpandedChange = { expandedOfMbti = it },
                    items = mbtiList,
                    onItemSelected = { selected ->
                        viewModel.onEvent(AddPersonEvent.MbtiChanged(selected))
                    }
                )
            }
            //성격 : 키워드 선택형
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "성격",
                        color = YeonTextMuted,
                        fontFamily = font_nanum_gyuri,
                        fontSize = 32.sp,
                    )
                    Text(
                        text = "*",
                        color = Color.Red
                    )
                }
                DropdownSelectorField(
                    value = uiState.coreInfo.personality,
                    expanded = expandedOfPersonal,
                    onExpandedChange = { expandedOfPersonal = it },
                    items = personalityList,
                    onItemSelected = { selected ->
                        viewModel.onEvent(AddPersonEvent.PersonalityChanged(selected))
                    }
                )
            }
            //성격에 대한 상세 설명
            item {
                Text(
                    text = "성격에 대한 상세 설명",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                ExpandableTextField("", {}, "", "테스트P")
            }
            //처음 만난 날
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "처음 만난 날",
                        color = YeonTextMuted,
                        fontFamily = font_nanum_gyuri,
                        fontSize = 32.sp,
                    )
                    Text(
                        text = "*",
                        color = Color.Red
                    )
                }
                YeonOutlinedTextField(uiState.coreInfo.firstMetDate.toString(), {viewModel.onEvent(
                    AddPersonEvent.FirstMetDateChanged(it))}, "", "")

            }
            //처음 만난 곳
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "처음 만난 곳",
                        color = YeonTextMuted,
                        fontFamily = font_nanum_gyuri,
                        fontSize = 32.sp,
                    )
                    Text(
                        text = "*",
                        color = Color.Red
                    )
                }
                YeonOutlinedTextField(uiState.coreInfo.name, {viewModel.onEvent(AddPersonEvent.NameChanged(it))}, "", "")
            }
            //이 사람이 좋아 하는 것
            item {
                Text(
                    text = "이 사람이 좋아 하는 것",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                KeywordInputField(
                    text = "",
                    placeholder = "",
                    onClick = {},
                )
            }
            //좋아 하는 것에 대한 상세 설명
            item {
                Text(
                    text = "좋아하는 것에 대한 상세 설명",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                ExpandableTextField("", {}, "", "테스트P")
            }
            //이 사람이 싫어 하는 것
            item {
                Text(
                    text = "이 사람이 싫어 하는 것",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                KeywordInputField(
                    text = "",
                    placeholder = "",
                    onClick = {},
                )
            }
            //싫어 하는 것에 대한 상세 설명
            item {
                Text(
                    text = "싫어하는 것에 대한 상세 설명",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                ExpandableTextField("", {}, "", "테스트P")
            }
            //특징
            item {
                Text(
                    text = "특징",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                KeywordInputField(
                    text = "",
                    placeholder = "",
                    onClick = {},
                )
            }
            //특징에 대한 상세 설명
            item {
                Text(
                    text = "특징에 대한 상세 설명",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                ExpandableTextField("", {}, "", "테스트P")
            }
            //마지막 연락 날짜
            item {
                Text(
                    text = "마지막 연락 날짜",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                DateInputField(
                    text = "",
                    placeholder = "",
                    onClick = {},
                )
            }
            //최근에 만난 곳
            item {
                Text(
                    text = "최근에 만난 곳",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                ExpandableTextField("", {}, "", "테스트P")
            }
            //기억에 남는 최근 대화
            item {
                Text(
                    text = "기억에 남는 최근 대화",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                ExpandableTextField("", {}, "", "테스트P")
            }
            //이사람과 함께한 사진
            item {
                //현재는 디폴트 이미지뷰만 , 향 후 데이터 추가에 따라 해당 영역은 목록형으로 수정 예정
                Text(
                    text = "이 사람과 함께한 사진",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )

                MemoryImageSectionContainer(
                    imageUris = uiState.additionalInfo.memoryImageUris,
                    onAddImagesClick = {
                        memoryPhotoPicker.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                    onRemoveImageClick = {
                        viewModel.onEvent(AddPersonEvent.AdditionalInfo.MemoryImageRemoved(it))
                    }
                )
            }
            //이사람이 사는 곳
            item {
                //주소 입력폼 추후 제작 예정 , 일단은 상세입력폼으로 대체
                Text(
                    text = "이 사람이 사는 곳",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                YeonOutlinedTextField(uiState.coreInfo.name, {viewModel.onEvent(AddPersonEvent.NameChanged(it))}, "", "")
            }
            //개인 연락처
            item {
                Text(
                    text = "개인 연락처",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                //3등분 영역 InputField 고민 필요.
                YeonOutlinedTextField("", {}, "", "")
            }
            //SNS 링크
            item {
                //하이퍼 링크 첨부폼 추후 제작 예정 , 일단은 상세입력폼으로 대체
                Text(
                    text = "SNS링크",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                HyperlinkInputField(
                    text = "",
                    placeholder = "",
                    onClick = {},
                )
            }
            //직업
            item {
                Text(
                    text = "직업",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                YeonOutlinedTextField("", {}, "", "")
            }
            //기타메모
            item {
                Text(
                    text = "기타 메모",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                ExpandableTextField("", {}, "", "테스트P")
            }
        }
        PrimaryButton(
            "인연 추가하기", {
                viewModel.onEvent(AddPersonEvent.SaveClicked)
            },
            Modifier.padding(horizontal = 32.dp), uiState.canSubmit
        )
    }
}