package com.example.project_yeon.feature.person.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_yeon.core.common.result.ResultWrapper
import com.example.project_yeon.data.local.mapper.toDetailPersonUiModel
import com.example.project_yeon.domain.person.repository.PersonRepository
import com.example.project_yeon.domain.person.usecase.GetPersonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DetailPersonViewModel @Inject constructor(
    private val getPersonDetailUseCase: GetPersonDetailUseCase,
    savedStateHandle: SavedStateHandle,
    ) : ViewModel()
{
    private val personId: Long = checkNotNull(savedStateHandle["personId"])
    private val _uiState = MutableStateFlow(
        DetailPersonUiState(
            personId = personId,
            isLoading = false,
            person = fakePerson(personId)
        )
    )
    val uiState: StateFlow<DetailPersonUiState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<DetailPersonEffect>()
    val effect = _effect.asSharedFlow()

    init {
        loadPersonDetail()
    }

    fun onEvent(event: DetailPersonEvent) {
        when (event) {
            DetailPersonEvent.OnBackClick -> emitEffect(DetailPersonEffect.NavigateBack)
            DetailPersonEvent.OnModifyClick -> emitEffect(
                DetailPersonEffect.NavigateToModify(personId)
            )
            DetailPersonEvent.OnRetryClick -> {
                // 추후 실제 재조회
            }
            DetailPersonEvent.OnSensitiveInfoClick -> {
                emitEffect(DetailPersonEffect.RequestSensitiveAuth)
            }
        }
    }
    fun reloadPersonDetail() {
        loadPersonDetail()
    }
    private fun emitEffect(effect: DetailPersonEffect) {
        viewModelScope.launch { _effect.emit(effect) }
    }
    private fun fakePerson(id: Long): DetailPersonUiModel {
        return DetailPersonUiModel(
            id = id,
            profileImageUri = null,
            name = "김연우",
            intimacy = 5,
            gender = "남성",
            birthDate = "1999.03.14",
            mbti = "INFJ",
            personality = "차분함",
            personalityDescription = "조용하고 차분한 편이지만, 친해지면 생각보다 말이 많고 세심하게 챙겨주는 성격입니다.",
            job = "개발자",
            firstMetDate = "2023년 봄",
            firstMetPlace = "학교 앞 카페",
            lastContactDate = "어제 저녁",
            recentMetPlace = "강남역",
            likes = listOf("커피", "산책", "사진 찍기"),
            dislikes = listOf("시끄러운 장소", "과한 연락"),
            traits = listOf("세심함", "배려심", "기억력 좋음"),
            recentTalk = "요즘 일이 많아서 정신이 없지만, 그래도 주말엔 잠깐이라도 쉬려고 한다고 이야기했음.",
            memo = "생일 선물은 실용적인 걸 좋아하는 편. 단 음식은 잘 안 먹고, 커피는 라떼보다 아메리카노를 더 선호함.",
            memoryImageUris = emptyList(),
            phoneMasked = "010-1234-5678",
            addressMasked = "서울시 강동구",
            snsMasked = "@yeon_memory"
        )
    }
    private fun loadPersonDetail() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            try {
                val person = getPersonDetailUseCase(personId)

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = null,
                    person = person.toDetailPersonUiModel()
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "상세 정보를 불러오지 못했습니다."
                )
            }
        }
    }
}

