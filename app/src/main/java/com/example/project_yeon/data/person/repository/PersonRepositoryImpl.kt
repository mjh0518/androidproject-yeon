package com.example.project_yeon.data.person.repository

import com.example.project_yeon.core.common.result.ResultWrapper
import com.example.project_yeon.core.common.result.safeCall
import com.example.project_yeon.data.local.dao.HiddenPersonDao
import com.example.project_yeon.data.local.dao.PersonDao
import com.example.project_yeon.data.local.mapper.toDomain
import com.example.project_yeon.data.local.mapper.toEntity
import com.example.project_yeon.domain.person.model.*
import com.example.project_yeon.domain.person.repository.PersonRepository
import kotlinx.coroutines.flow.*

class PersonRepositoryImpl (
    private val personDao: PersonDao,
    private val hiddenpersonDao : HiddenPersonDao
) : PersonRepository{

    override suspend fun getPerson(id: Long) =
        personDao.getById(id)!!.toDomain()

    override fun getPersons(): Flow<List<Person>> =
        personDao.getAllFlow().map{entities ->
            entities.map{it.toDomain()}
        }

    override suspend fun addPerson(request: PersonCreateRequest) : ResultWrapper<Long> {
        val now = System.currentTimeMillis()

        return safeCall {
            personDao.insert(
                request.toEntity(
                    createdAt = now,
                    updatedAt = now
                )
            )
        }
    }

    override suspend fun updatePerson(request: PersonUpdateRequest) : ResultWrapper<Unit>{
        return safeCall {
            val oldEntity = personDao.getById(request.personId)
                ?: throw IllegalArgumentException("해당 Person이 존재하지 않습니다.\n")

            val updatedEntity = request.toEntity(oldEntity)
            personDao.update(updatedEntity)
        }
    }

    override suspend fun moveToTrash(personIds: List<Long>){
        // TODO:
        // 1. personDao.getByIds(personIds)
        // 2. PersonEntity -> HiddenPersonEntity 변환 (meta 직렬화 포함)
        // 3. hiddenPersonDao.insertAll()
        // 4. personDao.deleteByIds()
        // 5. 트랜잭션 적용
    }

    override suspend fun updatePinnedState(
        personId: Long,
        pinned: Boolean,
        pinnedAt: Long?
    ) {
        personDao.updatePinnedState(
            personId = personId,
            pinned = pinned,
            pinnedAt = pinnedAt
        )
    }
}