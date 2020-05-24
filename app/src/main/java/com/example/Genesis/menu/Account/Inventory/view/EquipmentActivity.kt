package com.example.Genesis.menu.Account.Inventory.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R
import com.example.Genesis.menu.Account.Inventory.model.Item
import com.example.Genesis.menu.Account.Inventory.presenter.EquipmentPresenter
import kotlinx.android.synthetic.main.activity_inventory_item.*
import org.jetbrains.anko.imageResource

class EquipmentActivity : AppCompatActivity(), EquipmentView {
    private lateinit var presenter: EquipmentPresenter
    lateinit var backButton : Button
    lateinit var addButton : Button
    override var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_item)

        id = intent.extras?.get("id") as Int
        presenter = EquipmentPresenter(this).apply { onCreate() }
    }

    override fun showEquipment(equipment: Item) {
        this.title = equipment.name
        itemImage.imageResource = equipment.icon
        nameView.text = equipment.name
        descriptionView.text = equipment.description

        initView()
        backButton.setOnClickListener {
            val i = Intent(this, InventoryMainActivity:: class.java)
            startActivity(i)

        }


    }
    private fun initView(){
        backButton = findViewById(R.id.back_to_main)

    }

}
