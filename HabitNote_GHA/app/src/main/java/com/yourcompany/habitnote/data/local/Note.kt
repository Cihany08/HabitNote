package com.yourcompany.habitnote.data.local
import androidx.room.*
import java.time.Instant
@Entity data class Note(
 @PrimaryKey(autoGenerate=true) val id:Long=0,
 val title:String, val content:String, val tags:String="",
 val createdAt:Instant=Instant.now(), val updatedAt:Instant=Instant.now()
)