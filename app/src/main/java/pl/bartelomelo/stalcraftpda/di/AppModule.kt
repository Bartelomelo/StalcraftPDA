package pl.bartelomelo.stalcraftpda.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.bartelomelo.stalcraftpda.data.remote.DbApi
import pl.bartelomelo.stalcraftpda.data.remote.ItemsApi
import pl.bartelomelo.stalcraftpda.data.remote.repositories.DbRepository
import pl.bartelomelo.stalcraftpda.data.remote.repositories.ItemRepository
import pl.bartelomelo.stalcraftpda.util.Constants.BASE_DB_URL
import pl.bartelomelo.stalcraftpda.util.Constants.BASE_ITEMS_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDbRepository(
        api: DbApi
    ) = DbRepository(api)

    @Singleton
    @Provides
    fun provideItemRepository(
        api: ItemsApi
    ) = ItemRepository(api)

    @Singleton
    @Provides
    fun provideDbApi(): DbApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_DB_URL)
            .build()
            .create(DbApi::class.java)
    }
    @Singleton
    @Provides
    fun provideItemsApi(): ItemsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_ITEMS_URL)
            .build()
            .create(ItemsApi::class.java)
    }
}