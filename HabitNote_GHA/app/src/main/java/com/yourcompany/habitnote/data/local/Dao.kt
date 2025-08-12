package com.yourcompany.habitnote.data.local
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
@Dao interface HabitDao{
 @Query("SELECT * FROM Habit ORDER BY createdAt DESC") fun observeHabits():Flow<List<Habit>>
 @Insert suspend fun insertHabit(h:Habit):Long
 @Update suspend fun updateHabit(h:Habit)
 @Delete suspend fun deleteHabit(h:Habit)
 @Insert(onConflict=OnConflictStrategy.IGNORE) suspend fun insertLog(l:HabitLog):Long
 @Query("DELETE FROM HabitLog WHERE habitId=:hid AND date=:d") suspend fun deleteLog(hid:Long, d:LocalDate)
}
@Dao interface NoteDao{
 @Query("SELECT * FROM Note ORDER BY updatedAt DESC") fun observeNotes():Flow<List<Note>>
 @Insert suspend fun insert(n:Note):Long
 @Update suspend fun update(n:Note)
 @Delete suspend fun delete(n:Note)
}
@Dao interface TaskDao{
 @Query("SELECT * FROM Task ORDER BY (dueAt IS NULL), dueAt ASC") fun observeTasks():Flow<List<Task>>
 @Insert suspend fun insert(t:Task):Long
 @Update suspend fun update(t:Task)
 @Delete suspend fun delete(t:Task)
}