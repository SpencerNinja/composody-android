package com.example.composody.faqsdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "faqs_table")
data class FAQ(

    @PrimaryKey(autoGenerate = true)
    var faqsId: Long = 0L,

    @ColumnInfo(name = "question")
    val question: String = "",

    @ColumnInfo(name = "answer")
    val answer: String = ""

)