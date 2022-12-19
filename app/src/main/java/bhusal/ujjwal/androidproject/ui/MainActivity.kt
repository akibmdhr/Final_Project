package bhusal.ujjwal.androidproject.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import bhusal.ujjwal.androidproject.R
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val buttonPhoneDial = findViewById<Button>(R.id.phone)
        val btnMapofBrno = findViewById<Button>(R.id.mapbrno)
        val btnUtbSite = findViewById<Button>(R.id.utb_website)
        val btnCalendarEvent = findViewById<Button>(R.id.calendar)



        buttonPhoneDial.setOnClickListener { launchCallLog() }
        btnMapofBrno.setOnClickListener { openGoogleMap() }
        btnUtbSite.setOnClickListener { launchBrowser() }
        btnCalendarEvent.setOnClickListener { launchCalendarEvent() }
    }

    private fun launchCallLog() {
        val callIntent: Intent = Uri.parse("tel:+420731855077").let { number ->
            Intent(Intent.ACTION_DIAL, number)
        }
        startActivity(callIntent)
    }

    private fun openGoogleMap() {
        // Map point based on address
//        val mapIntent: Intent = Uri.parse("geo:49.195061,16.606836?q=" + Uri.encode("Brno"))
//            .let { location ->
//                // Or map point based on latitude/longitude
//                // val location: Uri = Uri.parse("geo:37.422219,-122.08364?z=14") // z param is zoom level
//                Intent(Intent.ACTION_VIEW, location)
//            }
//        startActivity(mapIntent)
        startActivity(Intent(this, MapsActivity::class.java))

    }

    private fun launchBrowser() {
        val webIntent: Intent = Uri.parse("https://www.utb.cz/en/").let { webpage ->
            Intent(Intent.ACTION_VIEW, webpage)
        }
        startActivity(webIntent)
    }

    private fun launchCalendarEvent() {
        val startTime = "2021-12-31T09:00:00"
        val endTime = "2021-12-31T12:00:00"

        // Parsing the date and time
        val mSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val mStartTime = mSimpleDateFormat.parse(startTime)
        val mEndTime = mSimpleDateFormat.parse(endTime)

        val mIntent = Intent(Intent.ACTION_EDIT)
        mIntent.type = "vnd.android.cursor.item/event"
        mIntent.putExtra("beginTime", mStartTime.time)
        mIntent.putExtra("time", true)
        mIntent.putExtra("rule", "FREQ=YEARLY")
        mIntent.putExtra("endTime", mEndTime.time)
        mIntent.putExtra("title", "New Year's Eve")
        startActivity(mIntent)
    }

}