package com.example.Genesis.menu.Account.Inventory.presenter

import com.example.Genesis.menu.Account.Inventory.model.EquipmentData
import com.example.Genesis.menu.Account.Inventory.model.Item
import com.example.Genesis.menu.Account.Inventory.view.EquipmentView


class EquipmentPresenter(private val view: EquipmentView) : Presenter {
    lateinit private var model: Item

    override fun onCreate() {
        this.model = EquipmentData.text[view.id]
        view.showEquipment(model)
    }

}