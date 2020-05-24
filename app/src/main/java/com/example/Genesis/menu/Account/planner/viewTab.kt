package com.example.Genesis.menu.Account.planner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import com.example.Genesis.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [viewTab.newInstance] factory method to
 * create an instance of this fragment.
 */
class viewTab(var planner : Planner) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = inflater.inflate(R.layout.fragment_view_tab, container, false)
            var eventViewButton = view.findViewById<Button>(R.id.eventViewButton)
            var eventViewNameValue = view.findViewById<EditText>(R.id.eventViewNameText)
            eventViewButton?.setOnClickListener(){
                var event = planner.plannerDB.getFullEvent(eventViewNameValue.text.toString())
                planner.eventName.text = event.name
                planner.eventDescription.text = event.description
                planner.refresh()
        }
        return view
    }
}
