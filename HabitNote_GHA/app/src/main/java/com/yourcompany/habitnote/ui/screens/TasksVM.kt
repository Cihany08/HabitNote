package com.yourcompany.habitnote.ui.screens
import androidx.lifecycle.*
import com.yourcompany.habitnote.data.local.Task
import com.yourcompany.habitnote.data.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject
@HiltViewModel
class TasksVM @Inject constructor(private val repo:TaskRepository): ViewModel(){
 val tasks = repo.tasks().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
 fun add(title:String, dueAt:Instant?)=viewModelScope.launch{ repo.add(Task(title=title, dueAt=dueAt)) }
}