package com.example.Genesis.menu.Account.Inventory.view

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R
import com.example.Genesis.menu.Account.Inventory.model.EquipmentData
import com.example.Genesis.menu.Account.Inventory.presenter.InventoryMainPresenter
import kotlinx.android.synthetic.main.inventory_element.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

class InventoryMainActivity : AppCompatActivity(), InventoryMainView {

    private lateinit var presenter: InventoryMainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = InventoryMainPresenter(this).apply { onCreate() }

        val arrayAdapter = EquipmentAdapter(this, EquipmentData.text)
        val itemView : ListView = findViewById(R.id.equipment_list)
        itemView.adapter = arrayAdapter



        itemView.setOnItemClickListener { parent, view, position, itemID ->
            val element =  presenter.onItemClicked(itemID.toInt())
            val intent = Intent(this, EquipmentActivity::class.java)
            startActivity(intent)

        }



    }

    override fun showItemDetails(itemID: Int) {
        startActivity(intentFor<EquipmentActivity>("id" to itemID).singleTop())

    }
}