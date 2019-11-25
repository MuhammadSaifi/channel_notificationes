package com.example.notificationess

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
// here we have used App class which is extend with android.Applicationes
// this class will be create our notificationes channel when we click it

class App : Application() {

    override fun onCreate() {
        super.onCreate()
// below line is our function that is called in main class
        createNotificationChannels()
    }
// drawback is it just work =>26 android version otherwise not worked so for check
    // versiones i have declared if condition if android == 26 then what happend
    private fun createNotificationChannels() {
    // here O is oreo which is 26 version of android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //here i have created the notificationes channel id
            // and set importance high we can set also low
            // notification manager is built functiones of our class
            // which will create the channels
            val channel1 = NotificationChannel(CHANNEL_1_ID, "Channel 1", NotificationManager.IMPORTANCE_HIGH)
            channel1.description = "This is Channel 1"
// similarly we created the channel 2
            // we can create more then two channels just changing id of its
            val channel2 = NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2", NotificationManager.IMPORTANCE_LOW
            )
            channel2.description = "This is Channel 2"
// manager will create our notificationes
            val manager = getSystemService<NotificationManager>(NotificationManager::class.java!!)
            manager!!.createNotificationChannel(channel1)
            manager.createNotificationChannel(channel2)
        }
    }

    companion object {
// its  our constructor of class
        val CHANNEL_1_ID = "channel"
        val CHANNEL_2_ID = "channel2"
    }
}


