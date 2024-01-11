package com.example.synema.Data.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.synema.model.ProfileModel
import kotlinx.coroutines.flow.map


const val AUTH_DS ="auth_datastore"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = AUTH_DS)


class DataStoreManager (val context: Context) {

    companion object {
        val ID = stringPreferencesKey("id")
        val NAME = stringPreferencesKey("name")
        val EMAIL = stringPreferencesKey("email")
        val BIO = stringPreferencesKey("bio")
        val TOKEN = stringPreferencesKey("token")
        val PROFILEPICTURE = stringPreferencesKey("profilePicture")
    }

    suspend fun saveToDataStore(profileState : ProfileModel) {
        context.dataStore.edit {
            it[ID] = profileState.id
            it[NAME] = profileState.name
            it[EMAIL] = profileState.email
            it[BIO] = profileState.bio
            it[TOKEN] = profileState.token
            it[PROFILEPICTURE]=profileState.profilePicture
        }
    }

    fun getFromDataStore() = context.dataStore.data.map {
        ProfileModel(
            it[ID]?:"",
            it[NAME]?:"",
            it[EMAIL]?:"",
            it[BIO]?:"",
            it[TOKEN]?:"",
            it[PROFILEPICTURE]?:"")
    }

    suspend fun clearDataStore() = context.dataStore.edit {
        it.clear()
    }

}