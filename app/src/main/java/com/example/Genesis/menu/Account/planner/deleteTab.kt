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
 * Use the [deleteTab.newInstance] factory method to
 * create an instance of this fragment.
 */

class deleteTab(var planner : Planner) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_delete_tab, container, false)
        var eventDeleteButton = view.findViewById<Button>(R.id.deleteBUtton)
        var eventDeleteNameValue = view.findViewById<EditText>(R.id.deleteName)
        eventDeleteButton?.setOnClickListener(){
            var arrayList = planner.plannerDB.getAllEvent()
            var eventID : Int? = null;
            for( i in 0 until arrayList.size){
                if(arrayList.get(i).name == eventDeleteNameValue.text.toString()){
                    eventID = arrayList.get(i).id
                }
            }
            if (eventID != null) {
                planner.plannerDB.deleteEvent(eventID)
                planner.refresh()
            }
        }
        return view
    }
}
