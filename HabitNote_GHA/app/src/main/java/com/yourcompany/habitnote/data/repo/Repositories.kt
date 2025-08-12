package com.yourcompany.habitnote.data.repo
import com.yourcompany.habitnote.data.local.*
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject
class HabitRepository @Inject constructor(private val dao:HabitDao){
 fun habits():Flow<List<Habit>> = dao.observeHabits()
 suspend fun addHabit(h:Habit)=dao.insertHabit(h)
 suspend fun update(h:Habit)=dao.updateHabit(h)
 suspend fun delete(h:Habit)=dao.deleteHabit(h)
 suspend fun toggleToday(h:Habit, today:LocalDate=LocalDate.now()){
   val ins=dao.insertLog(HabitLog(habitId=h.id, date=today))
   if (ins==-1L) dao.deleteLog(h.id, today)
 }
}
class NoteRepository @Inject constructor(private val dao:NoteDao){
 fun notes():Flow<List<Note>> = dao.observeNotes()
 suspend fun add(n:Note)=dao.insert(n)
 suspend fun update(n:Note)=dao.update(n)
 suspend fun delete(n:Note)=dao.delete(n)
}
class TaskRepository @Inject constructor(private val dao:TaskDao){
 fun tasks():Flow<List<Task>> = dao.observeTasks()
 suspend fun add(t:Task)=dao.insert(t)
 suspend fun update(t:Task)=dao.update(t)
 suspend fun delete(t:Task)=dao.delete(t)
}