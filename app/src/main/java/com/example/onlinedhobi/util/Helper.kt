package com.example.onlinedhobi.util

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog

object Helper {
    fun validateLoginCredentials(email : String, password : String, isLogin : Boolean) : Pair<Boolean,String>{
        var result = Pair(true,"")
        if(!isLogin && TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
        {
            result = Pair(false,"Please provide the credentials")
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            result = Pair(false,"Please provide correct email")
        }
        else if(password.length <= 6){
            result = Pair(false,"Password length should be greater than 6")
        }
        return result
    }
    fun validateRegisterCredentials(email : String,cnic : String,phone : String, password : String,cpassword : String) : Pair<Boolean,String>{
        var result = Pair(true,"")
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(cnic) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)|| TextUtils.isEmpty(cpassword))
        {
            result = Pair(false,"Please provide the credentials")
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            result = Pair(false,"Please provide correct email")
        }
        else if(cnic.contains('-')){
            result = Pair(false,"Please remove hyphen")
        }
        else if(phone[0] != '0' || phone[1] != '3'){
            result = Pair(false,"Please provide correct phone number")
        }
        else if(password.length <= 6){
            result = Pair(false,"Password length should be greater than 6")
        }
        else if(password != cpassword){
            result = Pair(false,"Password not matched")
        }
        return result
    }

    fun validateComplaintCredentials(title : String,phone : String,des : String) : Pair<Boolean,String>{
        var result = Pair(true,"")
        if(TextUtils.isEmpty(title) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(des))
        {
            result = Pair(false,"Please provide the credentials")
        }
        return result
    }
    fun validateEmergencyComplaintCredentials(title : String,des : String) : Pair<Boolean,String>{
        var result = Pair(true,"")
        if(TextUtils.isEmpty(title)|| TextUtils.isEmpty(des))
        {
            result = Pair(false,"Please provide the credentials")
        }
        return result
    }
    fun validatefeedbackCredentials(des : String) : Pair<Boolean,String>{
        var result = Pair(true,"")
        if(TextUtils.isEmpty(des))
        {
            result = Pair(false,"Please provide Feedback")
        }
        return result
    }

    fun errorMessage(msg: String?,context: Context) {
        SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...")
            .setContentText(msg).show()
    }

}