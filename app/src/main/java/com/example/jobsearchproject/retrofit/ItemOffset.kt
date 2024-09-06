package com.example.jobsearchproject.retrofit

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type


data class MockData(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>,
)

data class Offer(
    val id: String? = null,
    val title: String,
    val button: Button? = null,
    val link: String
)

data class Button(
    val text: String
)


@Entity(tableName = "vacancies")
@TypeConverters(StringTypeConverter::class)
data class Vacancy(
    @PrimaryKey()
    val id: String,
    val lookingNumber: Int? = 0,
    val title: String,
    @Embedded
    val address: Address,
    val company: String,
    @Embedded
    val experience: Experience,
    val publishedDate: String,
    var isFavorite: Boolean,

    @Embedded
    val salary: Salary,


    val schedules: List<String>,
    val questions: List<String>,
//
    val appliedNumber: Int? = null,
    val description: String? = null,
    val responsibilities: String
)


@Entity(tableName = "addresses")
data class Address(
    @PrimaryKey()
    val town: String,
    val street: String,
    val house: String
)

@Entity(tableName = "experiences")
data class Experience(
    @PrimaryKey()
    val previewText: String,
    val text: String
)

@Entity(tableName = "salaries")
data class Salary(
    val short: String? = null,
    @PrimaryKey()
    val full: String,
)

class StringTypeConverter() {
    @TypeConverter
    fun stringListToString(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun stringToStringList(value: String): List<String> {
        return value.split(",").toList()
    }
}



