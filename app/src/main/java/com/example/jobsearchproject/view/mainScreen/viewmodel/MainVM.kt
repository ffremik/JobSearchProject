package com.example.jobsearchproject.view.mainScreen.viewmodel

import android.content.res.AssetManager
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.jobsearchproject.ApplicationProject
import com.example.jobsearchproject.retrofit.MockData
import com.example.jobsearchproject.retrofit.Offer
import com.example.jobsearchproject.retrofit.Vacancy
import com.example.jobsearchproject.room.FavoritesDao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Locale


class MainVM(
    private val favoritesDao: FavoritesDao,
    private val assetManager: AssetManager,
) : ViewModel() {

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = (this[APPLICATION_KEY]) as ApplicationProject
                MainVM(
                    context.getFavoritesDao(),
                    context.getManager()

                )
            }
        }
    }

    val vacancies = MutableStateFlow(emptyList<Vacancy>())
    val isOpenReply = MutableStateFlow(false)
    val isOpenAddContent = MutableStateFlow(false)

    var vacancy = MutableStateFlow<Vacancy?> (null)


    lateinit var offers: List<Offer>
    // lateinit var vacancies: List<Vacancy>

    private fun getJob() {
        viewModelScope.launch {
            try {
                val json = assetManager.open("mock.json").bufferedReader().use { it.readText() }
                val type: Type = object : TypeToken<MockData>() {}.type
                val mockData = Gson().fromJson<MockData>(json, type)

                if (mockData != null) {
                    offers = mockData.offers
                    vacancies.value = mockData.vacancies
                    //  vacancy.addAll(mockData.vacancies) //= mockData.vacancies

                } else {
                    Log.e("MainVM", "Ошибка")
                }
            } catch (e: IOException) {
                Log.e("MainVM", "Ошибка", e)
            }
        }
    }

    init {
        getJob()
    }


    val isShowAllItems = MutableStateFlow(false)

    fun updateIsShowAllItems() {
        isShowAllItems.value = !isShowAllItems.value
    }

    fun getLookingText(number: Int): String {
        when (number) {
            in 2..4 -> return "$number человека"
            else -> return "$number человек"
        }
    }

    fun getVacanciesText(number: Int): String {
        when (number) {
            1 -> return "$number вакансия"
            in 2..4 -> return "$number вакансии"
            else -> return "$number вакансий"
        }
    }

    fun formatDate(
        dateString: String,
        inputFormat: String = "yyyy-MM-dd",
        outputFormat: String = "dd MMMM"
    ): String {
        val inputFormatter = SimpleDateFormat(inputFormat, Locale.getDefault())
        val outputFormatter = SimpleDateFormat(outputFormat, Locale.getDefault())
        val date = inputFormatter.parse(dateString)
        return outputFormatter.format(date).replaceFirst("0", "")
    }

    fun updateIsFavorites(item: Vacancy) {
        viewModelScope.launch {
            if (!item.isFavorite) {
                item.isFavorite = !item.isFavorite
                favoritesDao.addItemFavorites(item)
            } else {
                item.isFavorite = !item.isFavorite
                favoritesDao.deleteItemFavorites(item)
            }
        }
    }
    fun updateIsOpenReply(){
        isOpenReply.value = !isOpenReply.value
        if (isOpenAddContent.value){
            isOpenAddContent.value = false
        }
    }
    fun updateIsOpenAddContent(){
        isOpenAddContent.value = !isOpenAddContent.value
    }

    val allListFavorites = favoritesDao.getAllItemsFavorites()

    fun viewVacancy(itemVacancy: Vacancy) {
        vacancy.value = itemVacancy
    }


//    val apiResponse = MutableStateFlow<ApiResponse>(ApiResponse.Loading)
//
//    fun getAll() {
//        viewModelScope.launch {
//            apiResponse.value = try {
//                val result = JobApi.interactionRetrofit.getAllJob()
//                ApiResponse.Success(result)
//            }catch (e: IOException){
//                ApiResponse.Error
//            }
//        }
//    }

}