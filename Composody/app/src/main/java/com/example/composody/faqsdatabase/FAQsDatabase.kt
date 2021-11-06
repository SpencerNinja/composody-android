package com.example.composody.faqsdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [FAQ::class], version = 1, exportSchema = false)
abstract class FAQsDatabase : RoomDatabase() {

    // Connects the database to the DAO
    abstract val faqsDatabaseDao: FAQsDatabaseDao

    companion object{

        @Volatile
        private var instance: FAQsDatabase? = null

        fun getInstance(context: Context): FAQsDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): FAQsDatabase {
            return Room.databaseBuilder(context, FAQsDatabase::class.java, "FAQsDatabase")
                .addCallback(
                    object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
//                            val request = OneTimeWorkRequestBuilder<DatabaseWorker>().build()
//                            WorkManager.getInstance
                        }
                    }
                ).build()
        }

//        fun getInstance(context: Context): FAQsDatabase {
//            synchronized(this){
//
//                var instance = INSTANCE
//
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        FAQsDatabase::class.java,
//                        "faqs_database"
//                    )
//                        .fallbackToDestructiveMigration()
//                        .build()
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
    }

}