package com.example.jobsearchproject

import android.app.Application
import android.content.res.AssetManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.room.Room
import com.example.jobsearchproject.room.FavoritesDao
import com.example.jobsearchproject.room.FavoritesDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ApplicationProject() : Application(){
    private lateinit var favoritesDatabase: FavoritesDatabase

    override fun onCreate() {
        super.onCreate()
        favoritesDatabase = Room.databaseBuilder(this, FavoritesDatabase::class.java, "stock_base").build()

    }


    fun getFavoritesDao(): FavoritesDao {
        return favoritesDatabase.getFavoritesDao()
    }

    fun getManager(): AssetManager{
        return this.assets
    }

}