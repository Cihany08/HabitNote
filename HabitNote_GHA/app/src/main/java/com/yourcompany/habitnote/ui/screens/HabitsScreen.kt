package com.yourcompany.habitnote.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yourcompany.habitnote.data.local.Habit
@Composable fun HabitsScreen(vm:HabitVM = hiltViewModel()){
  val habits by vm.habits.collectAsState()
  var show by remember { mutableStateOf(false) }
  Scaffold(topBar={ TopAppBar(title={ Text("Alışkanlıklar") }) },
    floatingActionButton={ FloatingActionButton({ show=true }){ Icon(Icons.Default.Add,null)} }
  ){p->
    if (habits.isEmpty())
      Box(Modifier.padding(p).fillMaxSize()){ Text("Henüz alışkanlık yok. + ile ekleyin.", Modifier.padding(24.dp)) }
    else LazyColumn(contentPadding=p){ items(habits, key={it.id}){ h->
      ListItem(headlineContent={ Text(h.title) },
        supportingContent={ if(!h.notes.isNullOrBlank()) Text(h.notes!!) },
        trailingContent={ IconButton({ vm.toggleToday(h) }){ Icon(Icons.Default.CheckCircle,null) } })
      Divider()
    }}
  }
  if (show) AddHabitDialog({ show=false }){ t-> vm.add(t); show=false }
}
@Composable private fun AddHabitDialog(onDismiss:()->Unit, onSave:(String)->Unit){
  var title by remember{ mutableStateOf("") }
  AlertDialog(onDismissRequest=onDismiss, title={ Text("Yeni Alışkanlık") },
    text={ OutlinedTextField(title,{ title=it }, label={ Text("Başlık") }) },
    confirmButton={ TextButton({ if(title.isNotBlank()) onSave(title) }){ Text("Kaydet") } },
    dismissButton={ TextButton(onDismiss){ Text("Vazgeç") } })
}