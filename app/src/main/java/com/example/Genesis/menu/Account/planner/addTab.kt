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

class addTab(var planner : Planner) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)!!
        var eventNameValue = view.findViewById<EditText>(R.id.eventNameValue)
        var eventDescriptionValue = view.findViewById<EditText>(R.id.eventDescriptionValue)
        var eventNoteValue = view.findViewById<EditText>(R.id.eventNotesValue)
        var eventAddButton = view.findViewById<Button>(R.id.eventAddButton)
        eventAddButton.setOnClickListener(){
            var event = EventDesc(0,
                eventNameValue.text.toString(),
                eventDescriptionValue.text.toString(),
                eventNoteValue.text.toString(),
                planner.dateFormatted
            )
            planner.plannerDB.insertEvent(event)
            planner.refresh()
        }
        return view
    }
}

