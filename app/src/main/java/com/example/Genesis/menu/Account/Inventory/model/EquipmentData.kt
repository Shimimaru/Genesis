package com.example.Genesis.menu.Account.Inventory.model

import com.example.Genesis.R

class EquipmentData {
    companion object {
        val text = arrayListOf(
            Item(
                name = "Armour",
                description = "the metal coverings worn to protect the body in a battle.",
                icon = R.drawable.armour_image
            ),
            Item(
                name = "Artifact",
                description = "an object made by a human being, typically one of cultural or historical interest.",
                icon = R.drawable.artifact_image
            ),
            Item(
                name = "Weapon",
                description = "An Item that can be used for self defence.",
                icon = R.drawable.weapon_image
            ),

            Item(
                name = "Sword",
                description = "A weapon helpful to fight enemies in battle. ",
                icon = R.drawable.sword_image
            ),
            Item(
                name = "Dagger",
                description = "A weapon helpful to fight enemies in battle.",
                icon = R.drawable.dagger_image
            ),
            Item(
                name = "Jewels",
                description = "An item with a monetary value.",
                icon = R.drawable.jewel_image
            )
        )
    }
}