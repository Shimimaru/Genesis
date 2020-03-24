package com.example.Genesis.menu


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R
import com.example.Genesis.menu.MainMenu.MainMenu
import com.example.Genesis.objects.Experience
import com.example.Genesis.objects.ExperienceTypes
import com.example.Genesis.objects.Quest
import com.example.Genesis.objects.Rewards
import com.example.Genesis.objects.items.Item
import com.example.Genesis.user.Account.Account
import kotlinx.android.synthetic.main.activity_quest_screen.*


class Quests : AppCompatActivity() {
    lateinit var account : Account
    var list : MutableList<Item> = mutableListOf<Item>()
    var experience: Experience = Experience(ExperienceTypes.NONE,0)
    var reward = Rewards(list,experience)
    var quest : com.example.Genesis.objects.Quest = Quest("","",reward)
    var item : Item = Item("Weapon")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest_screen)
        account = Account().accountDB.getAccount(Account.accountNumber)
        var questName = findViewById<TextView>(R.id.questNameValue)
        var questDescription = findViewById<TextView>(R.id.questDescriptionValue)
        var questRewards = findViewById<TextView>(R.id.questRewardValue)
        var questGenerateButton = findViewById<Button> (R.id.questGenerateButton)
        var completeButton = findViewById<Button> (R.id.questCompleteButton)
        questGenerateButton.setOnClickListener(){
            Generate()
            questName.text = quest.name
            questDescription.text = quest.description
            questRewards.text = quest.rewards.items.get(0).name + ", " + quest.rewards.experience.type.toString().toLowerCase().capitalize() + ":" + quest.rewards.experience.expValue
        }
        questCompleteButton.setOnClickListener(){
            account.player.playerPresenter.experienceUp(quest.rewards.experience.type,quest.rewards.experience.expValue)
            questDescriptionValue.text = account.player.strength.toString() + "\n" + account.player.strengthExperienceValue.toString() + "/" + account.player.strengthExperienceMax.toString()
            account.player.playerDB.updatePlayer(Account.accountNumber)
        }
        var backButton = findViewById<Button>(R.id.button2)
        backButton.setOnClickListener(){
            val i = Intent(this, MainMenu::class.java)
            var account = account
            //i.putExtra(intentKey.Account.toString(),account)
            startActivity(i)
        }

    }

    fun Generate()
    {
        val count = (1..3).random()
        this.list.clear()
        when(count)
        {
            1 -> {
                item = Item("Weapon")
                list.add(item)
                reward.experience.type = ExperienceTypes.CHARISMA
                reward.experience.expValue = 100
                quest = Quest("Linken's Adventure","To think all this time that the reason Linken came here was to rid us of Blazerunner... He seems like such an ordinary guy, but I did notice that he's not at all interested in any of my experiments or gadgets like most gnomes would be. He must be a true warrior at heart.\n" +
                        "\n" +
                        "Bring the totem to him. See if that helps him remember why he came here.",reward)
            }
            2 -> {
                item = Item("Armor")
                list.add(item)
                reward.experience.type = ExperienceTypes.INTELLIGENCE
                reward.experience.expValue = 50
                quest = Quest("Lost in Battle","We battled in a small tauren camp when we were separated--she held three of the Bristlebacks off by herself. But the odds began to overwhelm us. I led some away only to see her overwhelmed by newcomers. In my rage, I turned to face my enemies, but they brought me down easily with their vast numbers.\n" +
                        "\n" +
                        "I awoke to a tauren druid tending my wounds--he had come across me on the Gold Road as I fell.",reward)
            }
            3 -> {
                item = Item("Artifact")
                list.add(item)
                reward.experience.type = ExperienceTypes.STRENGTH
                reward.experience.expValue = 200
                quest = Quest("The Battle of Darrowshire","Now, <name>, you will take part in battle of Darrowshire, and you will save Joseph Redpath.\n" +
                        "\n" +
                        "Place this relic bundle in the Darrowshire town square, and the spirits of Darrowshire will rise. Join their battle and assure that these two things come to pass: Davil must survive beyond the death of Horgus, and Redpath must survive to be corrupted, then defeated.\n" +
                        "\n" +
                        "After defeating the corrupted Redpath, his spirit will be saved. Speak with him, then return to his daughter Pamela.",reward)
            }
        }

    }
}
