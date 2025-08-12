package com.yourcompany.habitnote.data.local
import androidx.room.*
import java.time.*
@Entity(indices=[Index("title")])
data class Habit(
  @PrimaryKey(autoGenerate=true) val id:Long=0,
  val title:String,
  val notes:String?=null,
  val scheduleType:String="DAILY",
  val daysOfWeek:Set<Int>?=null,
  val remindAt:LocalTime?=null,
  val createdAt:Instant=Instant.now()
)