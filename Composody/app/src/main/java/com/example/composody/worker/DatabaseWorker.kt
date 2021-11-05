package com.example.composody.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.composody.faqsdatabase.FAQ
import com.example.composody.faqsdatabase.FAQsDatabase
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

//class DatabaseWorker(
//    context: Context,
//    workerParams: WorkerParameters
//) : CoroutineWorker(context, workerParams) {
//    override suspend fun doWork(): Result = coroutineScope {
//        val database = FAQsDatabase.getInstance(applicationContext)
//
//        try {
//            val userTest = JsonAssetReader.readList(
//                "users.json", applicationContext, Array<FAQ>::class.java
//            )
//            database.faqsDatabaseDao.insert(userTest)
//            Result.success()
//        } catch (ex: Exception) {
//            Log.i("note", "Error seeding FAQs into database")
//            Result.failure()
//        }
//    }
//
//    companion object {
//        private const val TAG = "SeedDatabaseWorker"
//    }
//}