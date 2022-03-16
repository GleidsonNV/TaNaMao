package com.example.bookappkotlin.profile.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bookappkotlin.ApplicationConstants
import com.example.bookappkotlin.helpper.AuthenticationHelper
import com.example.bookappkotlin.helpper.DatabaseAuthenticationHelper
import com.google.firebase.database.*
import org.koin.core.component.KoinComponent

interface RepositoryProfile {
    val snapshotLiveDataMutable: MutableLiveData<DataSnapshot>
    fun userData()
}

class ProfileRepository(
): RepositoryProfile, KoinComponent {

    private lateinit var database: DatabaseReference

    private lateinit var databaseAuthenticationHelper : AuthenticationHelper

    val _snapshotLiveDataMutable = MutableLiveData<DataSnapshot>()
    override val snapshotLiveDataMutable: MutableLiveData<DataSnapshot> = _snapshotLiveDataMutable


    override fun userData() {
        databaseAuthenticationHelper = DatabaseAuthenticationHelper()
        database =  databaseAuthenticationHelper.liveDatabase().getReference(ApplicationConstants.FIREBASE_USERS)

        database.database.reference.child("Users").addListenerForSingleValueEvent(
            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                }

                override fun onCancelled(error: DatabaseError) {
                }
            }
        )
        database.get().addOnSuccessListener {
            if(it.exists()){
                snapshotLiveDataMutable.postValue(it)
            }
        }
    }
}