package com.example.onlinedhobi.util

import android.content.Context
import com.example.onlinedhobi.model.UserResponse
import com.example.onlinedhobi.util.Constants.PREFS_TOKEN_FILE
import com.example.onlinedhobi.util.Constants.USER_INFO
import com.example.onlinedhobi.util.Constants.USER_TOKEN
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UtilManager @Inject constructor(@ApplicationContext context: Context) {
    private var prefs = context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)

    fun saveToken(token : String?){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN,token)
        editor.apply()
    }
    fun saveUser(user: UserResponse){
        val editor = prefs.edit()
        editor.putString(USER_INFO, Gson().toJson(user).toString())
        editor.apply()
    }
    fun saveLanding(){
        val editor = prefs.edit()
        editor.putString("Landing", "Yes")
        editor.apply()
    }

    fun getToken() : String?{
        return prefs.getString(USER_TOKEN,null)
    }
    fun getUser() : String? {
        return prefs.getString(USER_INFO,null)
    }
    fun getLanding() : String? {
        return prefs.getString("Landing",null)
    }
}