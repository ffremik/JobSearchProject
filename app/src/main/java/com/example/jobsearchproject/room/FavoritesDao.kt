package com.example.jobsearchproject.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jobsearchproject.retrofit.Vacancy
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItemFavorites(item: Vacancy)

    @Delete()
    suspend fun deleteItemFavorites(item: Vacancy)

    @Query("SELECT * FROM vacancies")
    fun getAllItemsFavorites(): Flow<List<Vacancy>>

}