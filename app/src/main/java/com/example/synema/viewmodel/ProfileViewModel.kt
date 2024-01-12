package com.example.synema.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synema.Data.DataStore.DataStoreManager
import com.example.synema.controller.AppContext
import com.example.synema.model.ProfileModel
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    var context = AppContext.getInstance();


     fun logout(c: Context) {
         context.setProfileState(ProfileModel("","","","","",""));
         viewModelScope.launch {
             DataStoreManager(c).clearDataStore().let {
                 context.getNav().navigate("login")
             }
         }


    }

    fun EditProfile(){
        context.getNav().navigate("editprofile")
    }


}