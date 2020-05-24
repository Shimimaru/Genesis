package com.example.Genesis.menu.Account.planner

//import kotlinx.android.synthetic.main.activity_planner_screen

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.Genesis.R
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.domain.Event
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList


class Planner : AppCompatActivity() {
    lateinit var eventName : TextView
    lateinit var eventDescription : TextView
    lateinit var monthName : TextView
    var date : Date = Date(2000, 1, 1)
    var compactCalendar: CompactCalendarView? = null
    private val dateFormatMonth: SimpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy mm dd", Locale.getDefault())
    var dateFormatted = dateFormat.format(date)
    lateinit var plannerDB: PlannerDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planner_screen)
        //Database
        plannerDB = PlannerDB()
        //Tab
        var viewPager = findViewById<ViewPager>(R.id.viewPager)
        var tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        var adapter = fragmentAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        adapter.addFragment(addTab(this),"Add")
        adapter.addFragment(deleteTab(this),"Delete")
        adapter.addFragment(editTab(this),"Edit")
        adapter.addFragment(viewTab(this),"View")
        //Calendar
        this.loadEvents()
        this.monthName = findViewById<TextView>(R.id.monthName)
        this.eventName = findViewById<TextView>(R.id.eventText)
        this.eventDescription = findViewById<TextView>(R.id.eventDescription)
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false)
        }
        if (actionBar != null) {
            actionBar.setTitle(null)
        }
        compactCalendar = findViewById<View>(R.id.calendarView) as CompactCalendarView
        compactCalendar!!.setUseThreeLetterAbbreviation(true)
        compactCalendar!!.setListener(object : CompactCalendarView.CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
                date = dateClicked
                dateFormatted = dateFormat.format(date)
                refresh()

            }

            override fun onMonthScroll(firstDayOfNewMonth: Date?) {
                monthName.text = dateFormatMonth.format(firstDayOfNewMonth)
            }
        })
    }

    fun loadEvents(){
        compactCalendar?.removeAllEvents()
        var eventList = plannerDB.getAllEvent()
        for( i in 0 until eventList.size){
            var d : Date  = dateFormat.parse(eventList.get(i).date)
            var event = Event(Color.RED,d.time)
            compactCalendar?.addEvent(event)
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            if(eventList.get(i).date == currentDate )
            {
                var toast : Toast = Toast.makeText(this,"You have " + eventList.get(i).name + " today", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    fun refresh(){
        var event : EventDesc = plannerDB.getEvent(dateFormatted)
            eventName.text = event.name
            eventDescription.text = event.description
    }
    class fragmentAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){
        val fragmentList:MutableList<Fragment> = ArrayList<Fragment>()
        val fragmentTitleList:MutableList<String> = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return fragmentList.get(position)
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitleList.get(position)
        }

        fun addFragment(fragment:Fragment,title:String){
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
            this.notifyDataSetChanged()
        }
    }
}