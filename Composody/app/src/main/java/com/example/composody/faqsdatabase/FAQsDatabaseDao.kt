package com.example.composody.faqsdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FAQsDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFAQs(faq: List<FAQ>)

    @Update
    suspend fun update(faq: FAQ)

    @Query("SELECT * from faqs_table")
    suspend fun getFAQ(): FAQ?

    @Query("SELECT * FROM faqs_table ORDER BY faqsId DESC")
    fun getAllFAQs(): LiveData<List<FAQ>>

}