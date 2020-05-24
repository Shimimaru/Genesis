package com.example.Genesis.menu.Account.Inventory.view

import com.example.Genesis.menu.Account.Inventory.model.Item

interface EquipmentView {
    fun showEquipment(equipment: Item)
    val id: Int


}