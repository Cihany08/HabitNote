package com.yourcompany.habitnote.ui.screens
import androidx.lifecycle.*
import com.yourcompany.habitnote.data.local.Note
import com.yourcompany.habitnote.data.repo.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NotesVM @Inject constructor(private val repo:NoteRepository): ViewModel(){
 val notes = repo.notes().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
 fun add(title:String, content:String)=viewModelScope.launch{ repo.add(Note(title=title, content=content)) }
}