package com.example.jobsearchproject.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jobsearchproject.retrofit.Address
import com.example.jobsearchproject.retrofit.Experience
import com.example.jobsearchproject.retrofit.Salary
import com.example.jobsearchproject.retrofit.Vacancy

@Database(
    entities = [Vacancy::class, Address::class, Experience::class, Salary::class,],
    version = 1,
    exportSchema = false
)
abstract class FavoritesDatabase() : RoomDatabase() {
    abstract fun getFavoritesDao(): FavoritesDao


}