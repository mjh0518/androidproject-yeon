package com.example.project_yeon.data.person.repository

import androidx.room.withTransaction
import com.example.project_yeon.core.common.result.ResultWrapper
import com.example.project_yeon.core.common.result.safeCall
import com.example.project_yeon.data.local.dao.HiddenPersonDao
import com.example.project_yeon.data.local.dao.PersonDao
import com.example.project_yeon.data.local.db.AppDataBase
import com.example.project_yeon.data.local.entity.HiddenPersonEntity
import com.example.project_yeon.data.local.mapper.toDomain
import com.example.project_yeon.data.local.mapper.toEntity
import com.example.project_yeon.data.local.mapper.toHiddenEntity
import com.example.project_yeon.domain.person.model.*
import com.example.project_yeon.domain.person.repository.PersonRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.*

class PersonRepositoryImpl (
    private val personDao: PersonDao,
    private val hiddenpersonDao : HiddenPersonDao,
    private val appDataBase : AppDataBase,
) : PersonRepository{

    private val gson = Gson()

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

    override suspend fun moveToTrash(personIds: List<Long>) {
        appDataBase.withTransaction {
            val persons = personDao.getPersonsByIds(personIds)
            if (persons.isEmpty()) return@withTransaction

            val now = System.currentTimeMillis()
            val expiryAt = now + 30L * 24L * 60L * 60L * 1000L

            val hiddenPersons = persons.map { person ->
                person.toHiddenEntity(
                    deletedAt = now,
                    expiryAt = expiryAt,
                    meta = gson.toJson(person)
                )
            }

            hiddenpersonDao.insertHiddenPersons(hiddenPersons)
            personDao.deletePersonsByIds(personIds)
        }
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