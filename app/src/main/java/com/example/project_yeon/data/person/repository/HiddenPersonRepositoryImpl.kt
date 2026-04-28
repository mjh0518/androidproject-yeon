package com.example.project_yeon.data.person.repository

import androidx.room.withTransaction
import com.example.project_yeon.data.local.dao.HiddenPersonDao
import com.example.project_yeon.data.local.dao.PersonDao
import com.example.project_yeon.data.local.db.AppDataBase
import com.example.project_yeon.data.local.entity.HiddenPersonEntity
import com.example.project_yeon.data.local.entity.PersonEntity
import com.example.project_yeon.data.local.mapper.toDomain
import com.example.project_yeon.domain.person.model.HiddenPerson
import com.example.project_yeon.domain.person.repository.HiddenPersonRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HiddenPersonRepositoryImpl (
    private val personDao: PersonDao,
    private val hiddenpersonDao : HiddenPersonDao,
    private val appDataBase: AppDataBase
) : HiddenPersonRepository{
    private val gson = Gson()

    override suspend fun getHiddenPerson(id: Long): HiddenPerson? =
        hiddenpersonDao.getHiddenPersonById(id)?.toDomain()

    override fun getHiddenPersons(): Flow<List<HiddenPerson>> =
        hiddenpersonDao.observeHiddenPersons().map { entities ->
            entities.map { it.toDomain() }
        }

    override suspend fun restorePersonFromTrash(personId: Long) {
        appDataBase.withTransaction {
            val hiddenPerson = hiddenpersonDao.getHiddenPersonById(personId) ?: return@withTransaction
            val restoredPerson = gson.fromJson(hiddenPerson.meta, PersonEntity::class.java)

            personDao.insertOrReplace(restoredPerson)
            hiddenpersonDao.deleteHiddenPersonById(personId)
        }
    }

    override suspend fun permanentlyDeleteFromTrash(personId: Long) {
        hiddenpersonDao.deleteHiddenPersonById(personId)
    }
}



