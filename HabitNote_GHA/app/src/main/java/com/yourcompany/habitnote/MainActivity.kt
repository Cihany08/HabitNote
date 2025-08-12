package com.yourcompany.habitnote
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.yourcompany.habitnote.ui.screens.*
import dagger.hilt.android.AndroidEntryPoint
sealed class Dest(val route:String,val label:String){
  data object Habits:Dest("habits","Alışkanlıklar")
  data object Notes:Dest("notes","Notlar")
  data object Tasks:Dest("tasks","Görevler")
}
@AndroidEntryPoint
class MainActivity: ComponentActivity(){
  override fun onCreate(s:Bundle?){ super.onCreate(s)
    setContent{
      MaterialTheme{
        val nav= rememberNavController()
        Scaffold(bottomBar={BottomBar(nav)}){p->
          NavHost(nav, startDestination=Dest.Habits.route, Modifier.padding(p)){
            composable(Dest.Habits.route){ HabitsScreen() }
            composable(Dest.Notes.route){ NotesScreen() }
            composable(Dest.Tasks.route){ TasksScreen() }
          }
        }
      }
    }
  }
}
@Composable private fun BottomBar(nav:NavHostController){
  val items=listOf(Dest.Habits,Dest.Notes,Dest.Tasks)
  NavigationBar {
    val cur = nav.currentBackStackEntryAsState().value?.destination?.route
    items.forEach { d->
      val icon = when(d){ Dest.Habits->Icons.Default.List; Dest.Notes->Icons.Default.Note; else->Icons.Default.DateRange }
      NavigationBarItem(selected = cur==d.route,
        onClick={ if(cur!=d.route) nav.navigate(d.route)},
        icon={ Icon(icon, null) }, label={ Text(d.label)})
    }
  }
}