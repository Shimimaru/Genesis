package com.example.Genesis.menu.Account.Inventory.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.Genesis.R
import com.example.Genesis.menu.Account.Inventory.model.Item
import kotlinx.android.synthetic.main.inventory_element.view.*
import org.jetbrains.anko.imageResource

class EquipmentAdapter(context: Context, items: ArrayList<Item>): ArrayAdapter<Item>(context, 0, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = this.getItem(position)

        var inflateView = convertView ?: this.inflateLayout(context, parent)

        inflateView.apply {
            if (item != null) {
                itemImage.imageResource = item.icon
            }
            if (item != null) {
                itemName.text = item.name
            }



        }

        return inflateView
    }

    private fun inflateLayout(context: Context, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.inventory_element, parent, false)

    }
}