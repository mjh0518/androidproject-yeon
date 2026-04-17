package com.example.project_yeon.domain.person.repository

import com.example.project_yeon.core.common.result.ResultWrapper
import com.example.project_yeon.domain.person.model.*
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    // 선택한 Person에 대한 정보를 가져올 예정
    suspend fun getPerson(id: Long): Person

    // PersonTable에 있는 각 Person에 대한 정보를 목록으로 가져올 예정
    fun getPersons(): Flow<List<Person>>

    // 인연 추가를 수행할 기능
    suspend fun addPerson(request: PersonCreateRequest) : ResultWrapper<Long>

    // 인연 수정을 수행할 기능
    suspend fun updatePerson(request: PersonUpdateRequest) : ResultWrapper<Unit>

    // 인연 삭제 - 보관함으로 이동시킬 기능
    suspend fun moveToTrash(personIds : List<Long>)

}