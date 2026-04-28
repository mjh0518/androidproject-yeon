package com.example.project_yeon.data.local.dao

import androidx.room.*
import com.example.project_yeon.data.local.entity.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Query("SELECT * FROM person ORDER BY createdAt DESC")
    fun getAllFlow(): Flow<List<PersonEntity>>

    @Query("SELECT * FROM person WHERE personId = :personId")
    suspend fun getById(personId: Long): PersonEntity?


    // FR - 01 추가
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entity: PersonEntity): Long

    // FR - 03 수정
    @Update
    suspend fun update(entity: PersonEntity)

    @Query(
        """
    UPDATE person
    SET pinned = :pinned,
        pinnedAt = :pinnedAt
    WHERE personId = :personId"""
    )
    suspend fun updatePinnedState(
        personId: Long,
        pinned: Boolean,
        pinnedAt: Long?
    )

    @Query("SELECT * FROM person WHERE personId IN (:ids)")
    suspend fun getPersonsByIds(ids: List<Long>): List<PersonEntity>

    // FR - 02 - 4 삭제 -> 휴지통으로 이동
    @Query("DELETE FROM person WHERE personId IN (:ids)")
    suspend fun deletePersonsByIds(ids: List<Long>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(entity: PersonEntity)
}