package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        val db = DataBaseHandler(this)
        val detailsData = db.readCredentials()
        val adapter = CredentialAdapter(detailsData,this,{credentialsModel: CredentialsModel->deleteClick(credentialsModel)})
        recyclerView.adapter = adapter
    }

    fun deleteClick(credential: CredentialsModel){
        val db = DataBaseHandler(this)
        if(db.deleteData(credential.id)){
            //adapter.notifyItemRemoved(position)
            Toast.makeText(applicationContext,"Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    fun addNewCredentials(view : View){
        print("hello world")
        val intent = Intent(this, AddDetailActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}


