package com.yourcompany.habitnote.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
@Composable fun NotesScreen(vm:NotesVM = hiltViewModel()){
  val notes by vm.notes.collectAsState()
  var show by remember{ mutableStateOf(false) }
  Scaffold(topBar={ TopAppBar(title={ Text("Notlar") }) },
    floatingActionButton={ FloatingActionButton({ show=true }){ Icon(Icons.Default.Add,null)} }){p->
    if (notes.isEmpty()) Box(Modifier.padding(p).fillMaxSize()){ Text("Not yok. + ile ekleyin.", Modifier.padding(24.dp)) }
    else LazyColumn(contentPadding=p){ items(notes, key={it.id}){ n->
      ListItem(headlineContent={ Text(n.title) }, supportingContent={ Text(n.content.take(120)) })
      Divider()
    }}
  }
  if (show) AddNoteDialog({ show=false }){ t,c-> vm.add(t,c); show=false }
}
@Composable private fun AddNoteDialog(onDismiss:()->Unit, onSave:(String,String)->Unit){
  var title by remember{ mutableStateOf("") }
  var content by remember{ mutableStateOf("") }
  AlertDialog(onDismissRequest=onDismiss, title={ Text("Yeni Not") },
    text={ Column{ OutlinedTextField(title,{title=it}, label={ Text("Başlık") })
      Spacer(Modifier.height(8.dp))
      OutlinedTextField(content,{content=it}, label={ Text("İçerik") }) } },
    confirmButton={ TextButton({ if(title.isNotBlank()) onSave(title,content) }){ Text("Kaydet") } },
    dismissButton={ TextButton(onDismiss){ Text("Vazgeç") } })
}