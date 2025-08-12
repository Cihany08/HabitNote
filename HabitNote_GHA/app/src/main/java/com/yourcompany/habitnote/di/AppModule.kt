package com.yourcompany.habitnote.di
import android.content.Context
import androidx.room.Room
import com.yourcompany.habitnote.data.local.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module @InstallIn(SingletonComponent::class)
object AppModule{
 @Provides @Singleton fun provideDb(@ApplicationContext ctx:Context)=
  Room.databaseBuilder(ctx, AppDatabase::class.java, "habitnote.db").build()
 @Provides fun provideHabitDao(db:AppDatabase)=db.habitDao()
 @Provides fun provideNoteDao(db:AppDatabase)=db.noteDao()
 @Provides fun provideTaskDao(db:AppDatabase)=db.taskDao()
}