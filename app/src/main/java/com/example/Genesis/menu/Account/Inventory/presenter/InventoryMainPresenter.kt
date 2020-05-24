package com.example.Genesis.menu.Account.Inventory.presenter

import com.example.Genesis.menu.Account.Inventory.model.EquipmentData
import com.example.Genesis.menu.Account.Inventory.model.Item
import com.example.Genesis.menu.Account.Inventory.view.InventoryMainView

class InventoryMainPresenter(private val view: InventoryMainView) : Presenter {
    private var model: ArrayList<Item> = EquipmentData.text


    override fun onCreate() {
        this.model = EquipmentData.text
    }

    fun onItemClicked(itemID: Int) {
        view.showItemDetails(itemID)
    }
}