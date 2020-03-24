package com.example.Genesis.menu.Account.planner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.example.Genesis.R
import com.google.android.material.tabs.TabLayout
//import kotlinx.android.synthetic.main.activity_planner_screen
import java.util.*
import kotlin.collections.ArrayList


class Planner : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planner_screen)
        var eventsList : MutableList<Event> = mutableListOf<Event>()
        var calendarView = findViewById<CalendarView>(R.id.calendarView)
        //Tab
        var viewPager = findViewById<ViewPager>(R.id.viewPager)
        var tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        var adapter = fragmentAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        adapter.addFragment(addTab(),"Add")
        adapter.addFragment(deleteTab(),"Delete")
        adapter.addFragment(editTab(),"Edit")
        adapter.addFragment(viewTab(),"View")
        //var dayText = findViewById<TextView>(R.id.day)
        //var monthText = findViewById<TextView>(R.id.month)
        //var yearText = findViewById<TextView>(R.id.year)
        val calendars: List<Calendar> = ArrayList()
        //descView.text = calendarView.firstSelectedDate.time.day.toString()
        calendarView?.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                val clickedDayCalendar = eventDay.calendar
                //descView.text = eventDay.calendar.get(Calendar.DAY_OF_MONTH).toString()
            }
        })
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