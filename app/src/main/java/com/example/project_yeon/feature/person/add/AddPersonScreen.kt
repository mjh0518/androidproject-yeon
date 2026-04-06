package com.example.project_yeon.feature.person.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
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
import com.example.project_yeon.core.ui.component.inputFfiled.KeywordInputField
import com.example.project_yeon.core.ui.component.inputFfiled.YeonOutlinedTextField
import com.example.project_yeon.core.ui.etc.RatingBar
import com.example.project_yeon.core.ui.theme.YeonTextMuted
import com.example.project_yeon.core.ui.theme.YeonTextOnBackGround

@Composable
fun AddPersonScreen(
    onBackClick: () -> Unit
) {
    val font_nanum_pen = FontFamily(Font(R.font.nanumpen, FontWeight.Normal))
    val font_nanum_gyuri = FontFamily(Font(R.font.nanumgyurieuilrgi, FontWeight.Normal))
    val genderOptions = listOf("남", "여")
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
    var selectedIndexOfMbti by remember { mutableStateOf(0) }
    var expandedOfPersonal by remember { mutableStateOf(false) }
    var selectedIndexOfPersonal by remember { mutableStateOf(0) }
    var temp = ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.bg_paper_light),
                contentScale = ContentScale.Crop
            ).systemBarsPadding(),
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
            // 프로필 사진 : 일단은 이미지뷰와 이벤트 없는 아이콘 버튼으로 대체 , 향후 구현에 따라 변화하는 이미지뷰로 변경
            item {
                Box(
                    modifier = Modifier.width(120.dp).height(120.dp).padding(12.dp)
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
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "프로필 이미지",
                            modifier = Modifier.size(48.dp),
                            tint = Color.DarkGray
                        )
                    }

                    IconButton(
                        onClick = { },
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
                }
            }
            //이름
            item {
                Text(
                    text = "이름",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                YeonOutlinedTextField("", {}, "", "",)
            }
            //성별
            item {
                Text(
                    text = "성별",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (temp == ""),
                            onClick = { },
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
                            selected = (temp == ""),
                            onClick = { },
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
                Text(
                    text = "생년월일",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                YeonOutlinedTextField("", {}, "", "", )
            }
            //친밀도
            item {
                Text(
                    text = "당신과의 친밀도",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                RatingBar(0, onRatingChanged = {})
            }
            //MBTI
            item {
                Text(
                    text = "MBTI",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                Box(modifier = Modifier.wrapContentSize()) {
                    // 드롭다운을 호출할 버튼/컴포넌트
                    TextButton(
                        onClick = { expandedOfMbti = true },
                        modifier = Modifier
                            .border(2.dp, Color.Black)
                            .background(Color.White)
                    ) {
                        Text(
                            text = mbtiList[selectedIndexOfMbti],
                            color = YeonTextMuted,
                            fontFamily = font_nanum_gyuri,
                            fontSize = 32.sp,
                        )
                    }

                    // 드롭다운 메뉴
                    DropdownMenu(
                        expanded = expandedOfMbti,
                        onDismissRequest = { expandedOfMbti = false }
                    ) {
                        mbtiList.forEachIndexed { index, title ->
                            DropdownMenuItem(
                                text = { Text(text = title) },
                                onClick = {
                                    selectedIndexOfMbti = index
                                    expandedOfMbti = false // 선택 후 메뉴 닫기
                                }
                            )
                        }
                    }
                }
            }
            //성격 : 키워드 선택형
            item {
                Text(
                    text = "성격",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                Box(modifier = Modifier.wrapContentSize()) {
                    // 드롭다운을 호출할 버튼/컴포넌트
                    TextButton(
                        onClick = { expandedOfPersonal = true },
                        modifier = Modifier
                            .border(2.dp, Color.Black)
                            .background(Color.White)
                    ) {
                        Text(
                            text = personalityList[selectedIndexOfPersonal],
                            color = YeonTextMuted,
                            fontFamily = font_nanum_gyuri,
                            fontSize = 32.sp,
                        )
                    }

                    // 드롭다운 메뉴
                    DropdownMenu(
                        expanded = expandedOfPersonal,
                        onDismissRequest = { expandedOfPersonal = false }
                    ) {
                        personalityList.forEachIndexed { index, title ->
                            DropdownMenuItem(
                                text = { Text(text = title) },
                                onClick = {
                                    selectedIndexOfPersonal = index
                                    expandedOfPersonal = false // 선택 후 메뉴 닫기
                                }
                            )
                        }
                    }
                }
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
                Text(
                    text = "처음 만난 날",
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
            //처음 만난 곳
            item{
                Text(
                    text = "처음 만난 곳",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                ExpandableTextField("", {}, "", "테스트P",)
            }
            //이 사람이 좋아 하는 것
            item{
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
            item{
                Text(
                    text = "좋아하는 것에 대한 상세 설명",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                ExpandableTextField("", {}, "", "테스트P", )
            }
            //이 사람이 싫어 하는 것
            item{
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
            item{
                Text(
                    text = "싫어하는 것에 대한 상세 설명",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                ExpandableTextField("", {}, "", "테스트P", )
            }
            //특징
            item{
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
            item{
                Text(
                    text = "특징에 대한 상세 설명",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                ExpandableTextField("", {}, "", "테스트P", )
            }
            //마지막 연락 날짜
            item{
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
            item{
                Text(
                    text = "최근에 만난 곳",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                ExpandableTextField("", {}, "", "테스트P", )
            }
            //기억에 남는 최근 대화
            item{
                Text(
                    text = "기억에 남는 최근 대화",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                ExpandableTextField("", {}, "", "테스트P", )
            }
            //이사람과 함께한 사진
            item{
                //현재는 디폴트 이미지뷰만 , 향 후 데이터 추가에 따라 해당 영역은 목록형으로 수정 예정
                Text(
                    text = "이 사람과 함께한 사진",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                Image( painter = painterResource(id = R.drawable.sample_add_guide),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp), // 크기 지정
                    contentScale = ContentScale.Crop,   // 비율 유지하며 꽉 채움
                    alignment = Alignment.TopCenter)
            }
            //이사람이 사는 곳
            item{
                //주소 입력폼 추후 제작 예정 , 일단은 상세입력폼으로 대체
                Text(
                    text = "이 사람이 사는 곳",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                ExpandableTextField("", {}, "", "테스트P", )
            }
            //개인 연락처
            item{
                Text(
                    text = "개인 연락처",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                //3등분 영역 InputField 고민 필요.
                YeonOutlinedTextField("", {}, "", "", )
            }
            //SNS 링크
            item{
                //하이퍼 링크 첨부폼 추후 제작 예정 , 일단은 상세입력폼으로 대체
                Text(
                    text = "SNS링크",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                ExpandableTextField("", {}, "", "테스트P", )
            }
            //직업
            item{
                Text(
                    text = "직업",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                YeonOutlinedTextField("", {}, "", "", )
            }
            //기타메모
            item{
                Text(
                    text = "기타 메모",
                    color = YeonTextMuted,
                    fontFamily = font_nanum_gyuri,
                    fontSize = 32.sp,
                )
                ExpandableTextField("", {}, "", "테스트P",)
            }
        }
        PrimaryButton(
            "인연 추가하기", {},
                Modifier.padding(horizontal = 32.dp), true
        )
    }
}