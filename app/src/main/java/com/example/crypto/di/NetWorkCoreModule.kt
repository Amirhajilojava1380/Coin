package com.example.crypto.di
import com.example.crypto.data.api.CryptoApi
import com.example.crypto.utils.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkCoreModule {

    @Singleton
    @Provides
    fun MoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }


    @Singleton
    @Provides
    fun okHttps():OkHttpClient{
        return OkHttpClient
            .Builder()
            .connectTimeout(15 , TimeUnit.SECONDS)
            .readTimeout(15 , TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun buildRetrofit(okHttpClient: OkHttpClient , moshiConverterFactory: MoshiConverterFactory):Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpClient)
            .build()
    }


    @Singleton
    @Provides
    fun apiService(retrofit: Retrofit): CryptoApi {
        return retrofit.create(CryptoApi::class.java)
    }

}