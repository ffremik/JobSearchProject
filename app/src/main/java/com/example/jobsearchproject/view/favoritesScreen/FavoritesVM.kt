package com.example.jobsearchproject.view.favoritesScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.jobsearchproject.ApplicationProject
import com.example.jobsearchproject.view.mainScreen.viewmodel.MainVM
import com.example.jobsearchproject.room.FavoritesDao

class FavoritesVM(
    private val dao: FavoritesDao
) : ViewModel(){
    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as ApplicationProject
                FavoritesVM(context.getFavoritesDao())
            }
        }
    }

    val listFavorites = dao.getAllItemsFavorites()

}