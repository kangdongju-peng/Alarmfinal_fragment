package com.example.alarmfinal_fragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_page1_fragment.*
import java.sql.Time
import java.util.*

class Page1Fragment : Fragment(){
    lateinit var timePicker : TimePicker
    lateinit var alarmManager: AlarmManager
    lateinit var update_text : TextView
    lateinit var btnStart : Button
    lateinit var btnStop : Button
    lateinit var pendingIntent: PendingIntent
    var hour : Int =0;
    var min : Int = 0; 
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_page1_fragment, container, false)
        alarmManager = activity!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager//확실 ㄴㄴ
        timePicker = root.findViewById(R.id.timePicker2) as TimePicker
        update_text = root.findViewById(R.id.textview) as TextView
        btnStart = root.findViewById(R.id.start_button) as Button
        btnStop = root.findViewById(R.id.stop_button) as Button
        var calendar : Calendar = Calendar.getInstance()
        var myIntent : Intent = Intent(context,AlarmReceiver::class.java)
        btnStart.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    calendar.set(Calendar.HOUR_OF_DAY,timePicker.hour)
                    calendar.set(Calendar.MINUTE,timePicker.minute)
                    calendar.set(Calendar.SECOND,0)
                    calendar.set(Calendar.MILLISECOND,0)
                    hour = timePicker.hour
                    min = timePicker.minute
                }
                else{
                    calendar.set(Calendar.HOUR_OF_DAY,timePicker.currentMinute)
                    calendar.set(Calendar.MINUTE,timePicker.currentMinute)
                    calendar.set(Calendar.SECOND,0)
                    calendar.set(Calendar.MILLISECOND,0)
                    hour = timePicker.currentHour
                    min = timePicker.currentMinute
                }
                var hr_str : String = hour.toString()
                var min_str : String = min.toString()
                if(hour > 12)
                    hr_str = (hour-12).toString()
                if(min < 10)
                    min_str = "0$min"
                set_alarm_text("Alarm set to :$hr_str : $min_str")
                myIntent.putExtra("extra","on")
                pendingIntent = PendingIntent.getBroadcast(context,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT)
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pendingIntent)
            }

        })
        btnStop.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                set_alarm_text("Alarm off")
                pendingIntent = PendingIntent.getBroadcast(context,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT)
                alarmManager.cancel(pendingIntent)
                activity!!.sendBroadcast(myIntent)
                myIntent.putExtra("extra","off")

            }
        })
        return root
    }

    private fun set_alarm_text(s: String) {
        update_text.setText(s)
    }

    // 뷰 생성이 완료되면 호출되는 메소드
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    companion object {
        private const val num = "num"
        @JvmStatic
        fun newInstance(Number: Int): Page1Fragment {
            return Page1Fragment().apply {
                arguments = Bundle().apply {
                    putInt(num, Number)
                }
            }
        }
    }
}

