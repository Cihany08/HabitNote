package com.yourcompany.habitnote.data.local
import androidx.room.*
import java.time.Instant
@Entity data class Task(
 @PrimaryKey(autoGenerate=true) val id:Long=0,
 val title:String, val dueAt:Instant?=null, val repeatRule:String?=null, val done:Boolean=false
)