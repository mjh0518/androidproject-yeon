package com.example.project_yeon.data.person.repository

import com.example.project_yeon.data.local.dao.HiddenPersonDao
import com.example.project_yeon.data.local.dao.PersonDao
import com.example.project_yeon.data.local.entity.HiddenPersonEntity
import com.example.project_yeon.domain.person.repository.HiddenPersonRepository
import kotlinx.coroutines.flow.Flow

class HiddenPersonRepositoryImpl (
    private val personDao: PersonDao,
    private val hiddenpersonDao : HiddenPersonDao
) : HiddenPersonRepository{
    override suspend fun getHiddenPerson(id: Long) =
        hiddenpersonDao.getById(id)

    override fun getHiddenPersons(): Flow<List<HiddenPersonEntity>> =
        hiddenpersonDao.getAllFlow()

    override suspend fun restorePersonFromTrash(personId: Long) {
        // TODO:
        // 1. hiddenPersonDao.getById(personId)
        // 2. meta 역직렬화 → PersonEntity 복원
        // 3. personDao.insert()
        // 4. hiddenPersonDao.deleteById()
        // 5. 트랜잭션 적용
    }

    override suspend fun permanentlyDeleteFromTrash(personId: Long) {
            // TODO:
        // 1. hiddenPersonDao.deleteById()
        // (복구 불가, 단순 삭제)
    }
}



