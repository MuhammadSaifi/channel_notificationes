package com.example.notificationess

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.notificationess.App.Companion.CHANNEL_1_ID
import com.example.notificationess.App.Companion.CHANNEL_2_ID
// here we have used channels so seee channel class App.kt and for broadcast receiver
// i have used the NotificationReceiver.kt see also it
// this type of notification only will used in sms type app otherwise not more used
// in otheres app because it generate sms like notificationes
class MainActivity : AppCompatActivity() {
    private var notificationManager: NotificationManagerCompat? = null
    private var editTextTitle: EditText? = null
    private var editTextMessage: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationManager = NotificationManagerCompat.from(this)

        editTextTitle = findViewById(R.id.edit_text_title)
        editTextMessage = findViewById(R.id.edit_text_message)
    }
// for send on channel 1 below function maked and it have all type of noti settings

    fun sendOnChannel1(v: View) {

        val title = editTextTitle!!.text.toString()
        val message = editTextMessage!!.text.toString()

        // below lines of code will show when we will click on notification then what happend


    val activityIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0)

        val broadcastIntent = Intent(this, NotificationReciever::class.java)
        broadcastIntent.putExtra("toastMessage", message)
        val actionIntent = PendingIntent.getBroadcast(this,
                0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT)


    // here largeIcon use for display our image in our notificationes
        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.lotti)
        val notification = NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
                .setContentText(message)
                // it will add icon on our notificationes

                .setLargeIcon(largeIcon)
                // there are a lot of styles included in it like bigPicture style etc.
                // it will also show expandable notificationes msg as given in line 57

                .setStyle(NotificationCompat.BigTextStyle()
                        .bigText(getString(R.string.long_dummy_text))
                        .setBigContentTitle("Big Content Title")
                        .setSummaryText("Summary Text"))
// here we can set priority high and low etc

                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build()

        notificationManager!!.notify(1, notification)
    }
// similary for channel 2 we used same line of codes
    fun sendOnChannel2(v: View) {
        val title = editTextTitle!!.text.toString()
        val message = editTextMessage!!.text.toString()

        val notification = NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(title)
                .setStyle(NotificationCompat.InboxStyle()
                        .addLine("This is line 1")
                        .addLine("This is line 2")
                        .addLine("This is line 3")
                        .addLine("This is line 4")
                        .addLine("This is line 5")
                        .addLine("This is line 6")
                        .addLine("This is line 7")
                        .setBigContentTitle("Big Content Title")
                        .setSummaryText("Summary Text"))

                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build()

        notificationManager!!.notify(2, notification)
    }


}
