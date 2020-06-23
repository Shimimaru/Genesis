package com.example.Genesis.user.Account

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.Genesis.database.Database
import com.example.Genesis.database.URLs
import com.example.Genesis.database.VolleySingleton
import com.example.Genesis.menu.Login.Login
import com.example.Genesis.menu.Quest.QuestMenu.QuestMenu
import com.example.Genesis.menu.Register.Register
import com.example.Genesis.user.Person.Person
import com.example.Genesis.user.Player.Player
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap

class AccountDatabase(var account: Account) : AccountContract.AccountDatabaseInterface {

    override fun insertAccount(person: Person, player: Player, register: Register) {
        var test = true
        var query: String =
            "SELECT " + Database.KEY_ACCOUNT_ID + " FROM " + Database.TABLE_ACCOUNT + " ORDER BY ID DESC LIMIT 1"
        var cursor: Cursor = Database.dbw.rawQuery(query, null)
        if (test) {
            val stringRequest = object : StringRequest(
                Request.Method.POST, URLs.URL_ACCOUNT + "?apicall=insert",
                Response.Listener { response ->
                    try {
                        val obj = JSONObject(response)
                        var cv: ContentValues = ContentValues()
                        cv.put(Database.KEY_ACCOUNT_ID, obj.getInt("accountid"))
                        cv.put(Database.KEY_ACCOUNT_USERNAME, account.username)
                        cv.put(Database.KEY_ACCOUNT_PASSWORD, account.password)
                        cv.put(Database.KEY_ACCOUNT_QUEST, 0)
                        cv.put(Database.KEY_ACCOUNT_PERSONID, obj.getInt("personid"))
                        cv.put(Database.KEY_ACCOUNT_PLAYERID, obj.getInt("playerid"))
                        var re: Long = Database.dbw.insert(Database.TABLE_ACCOUNT, null, cv)
                        Account.accountNumber = obj.getInt("accountid")
                        account.accNum = obj.getInt("accountid")
                        account.player.playerID = obj.getInt("playerid")
                        account.person.personid = obj.getInt("personid")
                        println(obj.getInt("accountid"))
                        println(obj.getInt("playerid"))
                        println(obj.getInt("personid"))
                        register.showMainMenu()
                    } catch (e: JSONException) {
                        Toast.makeText(register, "Username Taken", Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(register, error.message, Toast.LENGTH_SHORT).show()
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["username"] = account.username
                    params["password"] = account.password
                    params["player"] = player.toString()
                    params["person"] = person.toString()
                    return params
                }
            }
            VolleySingleton.getInstance(register).addToRequestQueue(stringRequest)
        } else {

        }
    }

    fun getExternalAccount(login: Login, username: String, password: String) {
        var test = true
        if (test) {
            val stringRequest = object : StringRequest(
                Request.Method.POST, URLs.URL_ACCOUNT + "?apicall=get",
                Response.Listener { response ->
                    try {
                        val obj = JSONObject(response)
                        var cv: ContentValues = ContentValues()
                        cv.put(Database.KEY_ACCOUNT_ID, obj.getInt("accountid"))
                        cv.put(Database.KEY_ACCOUNT_USERNAME, username)
                        cv.put(Database.KEY_ACCOUNT_PASSWORD, password)
                        cv.put(Database.KEY_ACCOUNT_QUEST, obj.getInt("quest"))
                        cv.put(Database.KEY_ACCOUNT_PERSONID, obj.getInt("personid"))
                        cv.put(Database.KEY_ACCOUNT_PLAYERID, obj.getInt("playerid"))
                        var re: Long = Database.dbw.insert(Database.TABLE_ACCOUNT, null, cv)
                        Account.accountNumber = obj.getInt("accountid")
                        account.accNum = obj.getInt("accountid")
                        account.player.playerID = obj.getInt("playerid")
                        account.person.personid = obj.getInt("personid")
                        QuestMenu.trackedQuest = obj.getInt("quest")
                        var cv1: ContentValues = ContentValues()
                        cv1.put(Database.KEY_PLAYER_ID, obj.getInt("playerid"))
                        cv1.put(Database.KEY_PLAYER_LEVEL, obj.getInt("level"))
                        cv1.put(
                            Database.KEY_PLAYER_LEVEL_EXPERIENCE_VALUE,
                            obj.getInt("levelExperienceValue")
                        )
                        cv1.put(
                            Database.KEY_PLAYER_LEVEL_EXPERIENCE_MAX,
                            obj.getInt("levelExperienceMax")
                        )
                        cv1.put(Database.KEY_PLAYER_STRENGTH, obj.getInt("strength"))
                        cv1.put(
                            Database.KEY_PLAYER_STRENGTH_EXPERIENCE_VALUE,
                            obj.getInt("strengthExperienceValue")
                        )
                        cv1.put(
                            Database.KEY_PLAYER_STRENGTH_EXPERIENCE_MAX,
                            obj.getInt("strengthExperienceMax")
                        )
                        cv1.put(Database.KEY_PLAYER_AGILITY, obj.getInt("agility"))
                        cv1.put(
                            Database.KEY_PLAYER_AGILITY_EXPERIENCE_VALUE,
                            obj.getInt("agilityExperienceValue")
                        )
                        cv1.put(
                            Database.KEY_PLAYER_AGILITY_EXPERIENCE_MAX,
                            obj.getInt("agilityExperienceMax")
                        )
                        cv1.put(Database.KEY_PLAYER_ENDURANCE, obj.getInt("endurance"))
                        cv1.put(
                            Database.KEY_PLAYER_ENDURANCE_EXPERIENCE_VALUE,
                            obj.getInt("enduranceExperienceValue")
                        )
                        cv1.put(
                            Database.KEY_PLAYER_ENDURANCE_EXPERIENCE_MAX,
                            obj.getInt("enduranceExperienceMax")
                        )
                        cv1.put(Database.KEY_PLAYER_INTELLIGENCE, obj.getInt("intelligence"))
                        cv1.put(
                            Database.KEY_PLAYER_INTELLIGENCE_EXPERIENCE_VALUE,
                            obj.getInt("intelligenceExperienceValue")
                        )
                        cv1.put(
                            Database.KEY_PLAYER_INTELLIGENCE_EXPERIENCE_MAX,
                            obj.getInt("intelligenceExperienceMax")
                        )
                        cv1.put(Database.KEY_PLAYER_WISDOM, obj.getInt("wisdom"))
                        cv1.put(
                            Database.KEY_PLAYER_WISDOM_EXPERIENCE_VALUE,
                            obj.getInt("wisdomExperienceValue")
                        )
                        cv1.put(
                            Database.KEY_PLAYER_WISDOM_EXPERIENCE_MAX,
                            obj.getInt("wisdomExperienceMax")
                        )
                        cv1.put(Database.KEY_PLAYER_CHARISMA, obj.getInt("charisma"))
                        cv1.put(
                            Database.KEY_PLAYER_CHARISMA_EXPERIENCE_VALUE,
                            obj.getInt("charismaExperienceValue")
                        )
                        cv1.put(
                            Database.KEY_PLAYER_CHARISMA_EXPERIENCE_MAX,
                            obj.getInt("charismaExperienceMax")
                        )
                        re = Database.dbw.insert(Database.TABLE_PLAYER, null, cv1)
                        var cv2: ContentValues = ContentValues()
                        cv2.put(Database.KEY_PERSON_ID, obj.getInt("personid"))
                        cv2.put(Database.KEY_PERSON_FIRSTNAME, obj.getString("firstName"))
                        cv2.put(Database.KEY_PERSON_LASTNAME, obj.getString("lastName"))
                        cv2.put(Database.KEY_PERSON_EMAIL, obj.getString("email"))
                        cv2.put(Database.KEY_PERSON_PHONE, obj.getString("phone"))
                        re = Database.dbw.insert(Database.TABLE_PERSON, null, cv2)
                        Toast.makeText(
                            login,
                            obj.getString("message"),
                            Toast.LENGTH_SHORT
                        ).show()
                        login.loginPresenter.initAccount(Account.accountNumber)
                        login.loginPresenter.showMainMenu()
                    } catch (e: JSONException) {
                        Toast.makeText(
                            login,
                            "JSON" + e.message,
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(login, "Volley" + error.message, Toast.LENGTH_SHORT).show()
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["username"] = username
                    params["password"] = password
                    return params
                }
            }
            VolleySingleton.getInstance(login).addToRequestQueue(stringRequest)
        }
        test = false
    }

    fun getAccount(accNum: Int): Account {
        var accountQuery: String =
            "SELECT * FROM " + Database.TABLE_ACCOUNT + " WHERE " + Database.KEY_ACCOUNT_ID + " = " + accNum
        var cursor: Cursor = Database.dbw.rawQuery(accountQuery, null)
        cursor.moveToNext()
        account.username = cursor.getString(cursor.getColumnIndex(Database.KEY_ACCOUNT_USERNAME))
        account.password = cursor.getString(cursor.getColumnIndex(Database.KEY_ACCOUNT_PASSWORD))
        account.player = Account.player.playerDB.getPlayer(Account.player.playerID)
        account.person = Account.person.personDB.getPerson(Account.person.personid)
        return account
    }
}