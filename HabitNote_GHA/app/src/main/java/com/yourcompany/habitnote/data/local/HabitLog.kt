package com.yourcompany.habitnote.data.local
import androidx.room.*
import java.time.LocalDate
@Entity(
  foreignKeys=[ForeignKey(entity=Habit::class,parentColumns=["id"],childColumns=["habitId"],onDelete=ForeignKey.CASCADE)],
  indices=[Index("habitId"), Index(value=["habitId","date"], unique=true)]
)
data class HabitLog(
  @PrimaryKey(autoGenerate=true) val id:Long=0,
  val habitId:Long,
  val date:LocalDate,
  val done:Boolean=true
)