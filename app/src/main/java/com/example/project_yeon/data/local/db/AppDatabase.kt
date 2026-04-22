package com.example.project_yeon.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.project_yeon.data.local.dao.*
import com.example.project_yeon.data.local.entity.*

@Database(
    entities = [
        PersonEntity::class,
        HiddenPersonEntity::class,
     //   SecurityConfigEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase(){
    abstract fun personDao() : PersonDao
    abstract fun hiddenpersonDao() : HiddenPersonDao
    //abstract fun securityDao() : SecurityConfigDao
}