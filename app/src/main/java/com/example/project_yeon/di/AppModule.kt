package com.example.project_yeon.di

import android.content.Context
import androidx.room.Room
import com.example.project_yeon.data.local.dao.HiddenPersonDao
import com.example.project_yeon.data.local.dao.PersonDao
import com.example.project_yeon.data.local.db.AppDataBase
import com.example.project_yeon.data.person.repository.PersonRepositoryImpl
import com.example.project_yeon.domain.person.repository.PersonRepository
import com.example.project_yeon.domain.person.usecase.CreatePersonUseCase
import com.example.project_yeon.domain.person.usecase.ValidatePersonDraftUseCase
import com.example.project_yeon.data.person.repository.HiddenPersonRepositoryImpl
import com.example.project_yeon.domain.person.repository.HiddenPersonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "yeon_database"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    fun providePersonDao(
        db: AppDataBase
    ): PersonDao = db.personDao()

    @Provides
    fun provideHiddenPersonDao(
        db: AppDataBase
    ): HiddenPersonDao = db.hiddenpersonDao()

    @Provides
    @Singleton
    fun providePersonRepository(
        personDao: PersonDao,
        hiddenPersonDao: HiddenPersonDao,
        appDataBase: AppDataBase
    ): PersonRepository {
        return PersonRepositoryImpl(
            personDao = personDao,
            hiddenpersonDao = hiddenPersonDao,
            appDataBase = appDataBase
        )
    }
    @Provides
    @Singleton
    fun provideHiddenPersonRepository(
        hiddenPersonDao: HiddenPersonDao,
        personDao: PersonDao,
        appDataBase: AppDataBase
    ): HiddenPersonRepository {
        return HiddenPersonRepositoryImpl(
            personDao = personDao,
            hiddenpersonDao = hiddenPersonDao,
            appDataBase = appDataBase
        )
    }

    @Provides
    fun provideCreatePersonUseCase(
        repository: PersonRepository
    ): CreatePersonUseCase {
        return CreatePersonUseCase(repository)
    }

    @Provides
    fun provideValidatePersonDraftUseCase(): ValidatePersonDraftUseCase {
        return ValidatePersonDraftUseCase()
    }
}