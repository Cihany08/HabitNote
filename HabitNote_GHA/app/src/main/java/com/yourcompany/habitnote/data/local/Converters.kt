package com.yourcompany.habitnote.data.local
import androidx.room.TypeConverter
import java.time.*
class Converters{
 @TypeConverter fun fromDays(v:String?)=v?.split(",")?.mapNotNull{it.toIntOrNull()}?.toSet()
 @TypeConverter fun toDays(v:Set<Int>?)=v?.joinToString(",")
 @TypeConverter fun fromInstant(v:String?)=v?.let{Instant.parse(it)}
 @TypeConverter fun toInstant(v:Instant?)=v?.toString()
 @TypeConverter fun fromLocalDate(v:String?)=v?.let{LocalDate.parse(it)}
 @TypeConverter fun toLocalDate(v:LocalDate?)=v?.toString()
 @TypeConverter fun fromLocalTime(v:String?)=v?.let{LocalTime.parse(it)}
 @TypeConverter fun toLocalTime(v:LocalTime?)=v?.toString()
}