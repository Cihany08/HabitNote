package com.yourcompany.habitnote.data.local
import androidx.room.*
@Database(entities=[Habit::class,HabitLog::class,Note::class,Task::class], version=1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase(){
 abstract fun habitDao():HabitDao
 abstract fun noteDao():NoteDao
 abstract fun taskDao():TaskDao
}