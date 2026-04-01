package com.example.project_yeon.data.person.repository

import com.example.project_yeon.data.local.dao.HiddenPersonDao
import com.example.project_yeon.data.local.dao.PersonDao
import com.example.project_yeon.data.local.entity.PersonEntity
import com.example.project_yeon.domain.person.model.PersonCreateRequest
import com.example.project_yeon.domain.person.model.PersonUpdateRequest
import com.example.project_yeon.domain.person.repository.PersonRepository
import kotlinx.coroutines.flow.Flow

class PersonRepositoryImpl (
    private val personDao: PersonDao,
    private val hiddenpersonDao : HiddenPersonDao
) : PersonRepository{

    override fun getPerson(id: Long) =
        personDao.getById(id)

    override fun getPersons(): Flow<List<PersonEntity>> =
        personDao.getAllFlow()

    override suspend fun addPerson(request: PersonCreateRequest) : Long{
        val now = System.currentTimeMillis()
        val entity = PersonEntity(
            name = request.name,
            gender = request.gender,
            birthDate = request.birthDate,
            closeness = request.closeness,
            mbti = request.mbti,
            personality = request.personality,
            personalityDetail = request.personalityDetail,
            firstMetDay = request.firstMetDay,
            firstMetPlace = request.firstMetPlace,
            likes = request.likes,
            dislikes = request.dislikes,
            characteristics = request.characteristics,
            lastContactAt = request.lastContactAt,
            lastMetPlace = request.lastMetPlace,
            recentConversation = request.recentConversation,
            photos = request.photos,
            address = request.address,
            phone = request.phone,
            sns = request.sns,
            job = request.job,
            memo = request.memo,
            pinned = false,
            createdAt = now,
            updatedAt = now
        )
        return personDao.insert(entity)
    }

    override suspend fun updatePerson(request: PersonUpdateRequest){
        val oldEntity = personDao.getById(request.personId)
            ?: throw IllegalArgumentException("해당 Person이 존재하지 않습니다.\n")

        val updatedEntity = oldEntity.copy(
            name = request.name,
            gender = request.gender,
            birthDate = request.birthDate,
            closeness = request.closeness,
            mbti = request.mbti,
            personality = request.personality,
            personalityDetail = request.personalityDetail,
            firstMetDay = request.firstMetDay,
            firstMetPlace = request.firstMetPlace,
            likes = request.likes,
            dislikes = request.dislikes,
            characteristics = request.characteristics,
            lastContactAt = request.lastContactAt,
            lastMetPlace = request.lastMetPlace,
            recentConversation = request.recentConversation,
            photos = request.photos,
            address = request.address,
            phone = request.phone,
            sns = request.sns,
            job = request.job,
            memo = request.memo,
            updatedAt = System.currentTimeMillis()
        )
        personDao.update(updatedEntity)
    }

    override suspend fun moveToTrash(personIds: List<Long>){
        // TODO:
        // 1. personDao.getByIds(personIds)
        // 2. PersonEntity -> HiddenPersonEntity 변환 (meta 직렬화 포함)
        // 3. hiddenPersonDao.insertAll()
        // 4. personDao.deleteByIds()
        // 5. 트랜잭션 적용
    }
}