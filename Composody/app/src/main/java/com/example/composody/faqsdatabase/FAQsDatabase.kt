package com.example.composody.faqsdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FAQs::class], version = 1, exportSchema = false)
abstract class FAQsDatabase : RoomDatabase() {

    // Connects the database to the DAO
    abstract val faqsDatabaseDao: FAQsDatabaseDao

    /* Define a companion object.
    * Allows us to add functions on the FAQsDatabase class.
    * Clients can call 'FAQsDatabase.getInstance(context)' to instantiate a new SleepDatabase.
    */
    // Allows clients to access the methods for creating or getting the database without instantiating the class
    companion object{
        /* Declare a @Volatile INSTANCE variable.
        * INSTANCE will keep a reference to any database returned via getInstance.
        *
        * This will help us avoid repeatedly initializing the database, which is expensive.
        *
        * The value of a volatile variable will never be cached, and all writes and reads will be
        * done to and from the main memory. It means that changes made by one thread to shared data
        * are visible to other threads.
        *
        * Writes to this field are immediately made visible to other threads.
        * */
        // Changes to one thread are immediately visible to other threads
        @Volatile
        private var INSTANCE: FAQsDatabase? = null

        // Define a getInstance() method with a synchronized block
        // Returns a reference to the FAQsDatabase
        fun getInstance(context: Context): FAQsDatabase {
            // Synchronized means that only one thread of execution at a time can enter this block of code
            // which makes sure the database only get initialized once
            synchronized(this){
                // Inside the synchronized block
                // Check whether the database already exists
                // and if it does not, use Room.databaseBuilder to create it
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FAQsDatabase::class.java,
                        "faqs_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}