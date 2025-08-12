package com.yourcompany.habitnote.ui.screens
import androidx.lifecycle.*
import com.yourcompany.habitnote.data.local.Habit
import com.yourcompany.habitnote.data.repo.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
@HiltViewModel
class HabitVM @Inject constructor(private val repo:HabitRepository): ViewModel(){
 val habits = repo.habits().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
 fun add(title:String)=viewModelScope.launch{ repo.addHabit(Habit(title=title)) }
 fun toggleToday(h:Habit)=viewModelScope.launch{ repo.toggleToday(h, LocalDate.now()) }
}