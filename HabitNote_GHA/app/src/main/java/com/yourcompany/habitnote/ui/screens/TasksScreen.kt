package com.yourcompany.habitnote.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
@Composable fun TasksScreen(vm:TasksVM = hiltViewModel()){
  val tasks by vm.tasks.collectAsState()
  var show by remember{ mutableStateOf(false) }
  Scaffold(topBar={ TopAppBar(title={ Text("Görevler") }) },
    floatingActionButton={ FloatingActionButton({ show=true }){ Icon(Icons.Default.Add,null)} }){p->
    if (tasks.isEmpty()) Box(Modifier.padding(p).fillMaxSize()){ Text("Görev yok. + ile ekleyin.", Modifier.padding(24.dp)) }
    else LazyColumn(contentPadding=p){ items(tasks, key={it.id}){ t->
      ListItem(headlineContent={ Text(t.title) }, supportingContent={ Text(t.dueAt?.toString() ?: "Tarih yok") },
        trailingContent={ Icon(Icons.Default.Check,null) })
      Divider()
    }}
  }
  if (show) AddTaskDialog({ show=false }){ title-> vm.add(title, null); show=false }
}
@Composable private fun AddTaskDialog(onDismiss:()->Unit, onSave:(String)->Unit){
  var title by remember{ mutableStateOf("") }
  AlertDialog(onDismissRequest=onDismiss, title={ Text("Yeni Görev") },
    text={ OutlinedTextField(title,{title=it}, label={ Text("Başlık") }) },
    confirmButton={ TextButton({ if(title.isNotBlank()) onSave(title) }){ Text("Kaydet") } },
    dismissButton={ TextButton(onDismiss){ Text("Vazgeç") } })
}