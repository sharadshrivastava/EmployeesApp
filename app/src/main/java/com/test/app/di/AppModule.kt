package com.test.app.di

import android.app.Application
import androidx.room.Room
import com.test.app.data.db.EmployeesDB
import com.test.app.data.db.EmployeesDao
import com.test.app.data.network.EmployeesApi
import com.test.app.data.network.EmployeesApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideUserApi(): EmployeesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmployeesApi::class.java)
    }

    @Provides
    @Singleton
    fun dao(app: Application): EmployeesDao = Room.databaseBuilder(
        app, EmployeesDB::class.java, "app-db"
    ).build().employeesDao()
}