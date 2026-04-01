package com.example.project_yeon.data.local.dao

import androidx.room.*
import com.example.project_yeon.data.local.entity.HiddenPersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HiddenPersonDao{
    @Query("SELECT * FROM hidden_person")
    fun getAllFlow(): Flow<List<HiddenPersonEntity>>

    @Query("SELECT * FROM hidden_person WHERE personId = :personId")
    suspend fun getById(personId: Long): HiddenPersonEntity?

    // FR - 02 - 4 삭제 시 HiddenPerson Table로 해당 데이터를 삽입해야함.
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entity: HiddenPersonEntity): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAll(entities: List<HiddenPersonEntity>): List<Long>

    // FR - 02-5 복원 후 삭제 , 최종 삭제
    @Query("DELETE FROM hidden_person WHERE personId = :personId") // 삭제 쿼리
    suspend fun deleteById(personId: Long): Int // 삭제된 행 수 반환.
}