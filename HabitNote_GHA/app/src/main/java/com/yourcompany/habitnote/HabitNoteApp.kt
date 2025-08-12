package com.yourcompany.habitnote
import android.app.*
import android.os.Build
import dagger.hilt.android.HiltAndroidApp
@HiltAndroidApp
class HabitNoteApp: Application() {
  override fun onCreate() { super.onCreate()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val ch = NotificationChannel("reminders","Hatırlatmalar", NotificationManager.IMPORTANCE_DEFAULT)
      getSystemService(NotificationManager::class.java).createNotificationChannel(ch)
    }
  }
}