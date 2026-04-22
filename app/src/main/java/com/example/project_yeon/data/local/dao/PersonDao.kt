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

    @Delete
    suspend fun delete(entity: PersonEntity)

    // FR - 02-5 삭제 -> 휴지통으로 이동
    @Query("DELETE FROM person WHERE personId IN (:personIds)")
    suspend fun deleteByIds(personIds: List<Long>)

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
}