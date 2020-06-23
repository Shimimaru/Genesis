package com.example.Genesis.menu.Login

import android.database.Cursor
import android.widget.Toast
import com.example.Genesis.database.Database
import com.example.Genesis.menu.Login.LoginContract.LoginDatabaseInterface
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.Genesis.database.URLs
import com.example.Genesis.database.VolleySingleton
import com.example.Genesis.user.Account.Account


class LoginDatabase(var login : Login) : LoginDatabaseInterface{
    var db : Database
    init{
        Database.db = Database(login)
        db = Database.db
        Database.dbw = Database.db.writableDatabase
        Database.dbr = Database.db.readableDatabase
        db.initQuestGenerator()
    }
    override fun checkUserName(username: String,password : String){
        var booleanState : Boolean = false
        var usernameQuery : String = "SELECT " + Database.KEY_ACCOUNT_USERNAME + " FROM " + Database.TABLE_ACCOUNT +
                " WHERE " + Database.KEY_ACCOUNT_USERNAME + " = '" + username + "';"
        var cursor : Cursor = Database.dbw.rawQuery(usernameQuery,null)
        cursor.moveToNext()
        if(cursor.count <= 0) {
            val stringRequest = object : StringRequest(Request.Method.POST, URLs.URL_LOGIN + "?apicall=username",
                Response.Listener { response ->
                    try {
                        val obj = JSONObject(response)

                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            //getting the user from the response
                            var check = obj.getBoolean("check")
                            if(check) {
                                Toast.makeText(
                                    login,
                                    obj.getString("message"),
                                    Toast.LENGTH_SHORT
                                ).show()
                                checkPassWord(username,password)
                            }
                            else {
                                Toast.makeText(
                                    login,
                                    obj.getString("message"),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(login, obj.getString("message"), Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener {
                        error -> println(error.message)//Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["username"] = username
                    return params
                }
            }
            VolleySingleton.getInstance(login).addToRequestQueue(stringRequest)
        }
        else
        {
            checkPassWord(username,password)
        }
        cursor.close()
    }

    override fun checkPassWord(username : String,password: String){
        var passwordQuery : String = "SELECT " + Database.KEY_ACCOUNT_PASSWORD + " FROM " + Database.TABLE_ACCOUNT +
                " WHERE " + Database.KEY_ACCOUNT_USERNAME + " = '" + username +
                "' AND " + Database.KEY_ACCOUNT_PASSWORD + " = '" + password + "';"
        var cursor = Database.dbw.rawQuery(passwordQuery,null)
        cursor.moveToNext()
        if(cursor.count <= 0) {
            val stringRequest = object : StringRequest(Request.Method.POST, URLs.URL_LOGIN + "?apicall=password",
                Response.Listener { response ->
                    try {
                        val obj = JSONObject(response)

                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            //getting the user from the response
                            var check = obj.getBoolean("check")
                            if(check) {
                                Account.accountNumber = obj.getInt("id")
                                Account().accountDB.getExternalAccount(login,username,password)
                            }
                            else {
                                Toast.makeText(
                                    login,
                                    obj.getString("message"),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(login, obj.getString("message"), Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener {
                        error -> println(error.message)//Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
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
        else
        {
            login.loginPresenter.showMainMenu()
            Toast.makeText(
                login,
                "Login Successful",
                Toast.LENGTH_SHORT
            ).show()
        }
        cursor.close()
    }
}