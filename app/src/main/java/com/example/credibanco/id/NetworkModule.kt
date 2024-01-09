package com.example.credibanco.id

import com.example.credibanco.data.Constant.NAME_DATABASE

import android.content.Context
import androidx.room.Room
import com.example.credibanco.services.IEndPoint
import com.example.credibanco.repository.IRepository
import com.example.credibanco.database.TransactionDataBase
import com.example.credibanco.data.Constant
import com.example.credibanco.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideIEndPoint(retrofit: Retrofit): IEndPoint {
        return retrofit.create(IEndPoint::class.java)
    }

    @Singleton
    @Provides
    fun provideRoom(
        @ApplicationContext context: Context): TransactionDataBase {
        return Room.databaseBuilder(
            context,
            TransactionDataBase::class.java,
            NAME_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(repository: Repository): IRepository {
        return repository
    }

}