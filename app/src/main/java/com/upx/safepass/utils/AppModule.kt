package com.upx.safepass.utils

import android.app.Application
import androidx.room.Room
import com.upx.safepass.data.PasswordDao
import com.upx.safepass.data.PasswordDatabase
import com.upx.safepass.domain.PasswordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): PasswordDatabase {
        return Room.databaseBuilder(
            app,
            PasswordDatabase::class.java,
            "password_db"
        ).build()
    }

    @Provides
    fun providePasswordDao(db: PasswordDatabase): PasswordDao {
        return db.passwordDao()
    }

    @Provides
    fun providePasswordRepository(dao: PasswordDao): PasswordRepository {
        return PasswordRepository(dao)
    }
}