package com.example.project_yeon.data.local.dao

import androidx.room.*
import com.example.project_yeon.data.local.entity.HiddenPersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HiddenPersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHiddenPersons(persons: List<HiddenPersonEntity>)

    @Query("SELECT * FROM hidden_person ORDER BY deletedAt DESC")
    fun observeHiddenPersons(): Flow<List<HiddenPersonEntity>>

    @Query("SELECT * FROM hidden_person WHERE personId = :personId")
    suspend fun getHiddenPersonById(personId: Long): HiddenPersonEntity?

    @Query("SELECT * FROM hidden_person WHERE personId IN (:ids)")
    suspend fun getHiddenPersonsByIds(ids: List<Long>): List<HiddenPersonEntity>

    @Query("DELETE FROM hidden_person WHERE personId = :personId")
    suspend fun deleteHiddenPersonById(personId: Long)

    @Query("DELETE FROM hidden_person WHERE personId IN (:ids)")
    suspend fun deleteHiddenPersonsByIds(ids: List<Long>)
}