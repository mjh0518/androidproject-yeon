package com.example.project_yeon.domain.person.repository

import com.example.project_yeon.domain.person.model.HiddenPerson
import kotlinx.coroutines.flow.Flow

interface HiddenPersonRepository {

    // 복원대상 Person에 대한 정보를 가져올 예정
    suspend fun getHiddenPerson(id: Long): HiddenPerson?

    // PersonTable에 있는 각 Person에 대한 정보를 목록으로 가져올 예정
    fun getHiddenPersons(): Flow<List<HiddenPerson>>

    //복원 후 원래의 Person 테이블로 복원
    suspend fun restorePersonFromTrash(personId: Long)

    //영구 삭제
    suspend fun permanentlyDeleteFromTrash(personId: Long)
}