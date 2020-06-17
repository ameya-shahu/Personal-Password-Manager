package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        val db = DataBaseHandler(this)
        val detailsData = ArrayList(db.readCredentials())
        val adapter = CredentialAdapter(this,detailsData)
        recyclerView.adapter = adapter
        if(detailsData.isEmpty()){
            recyclerView.visibility = View.INVISIBLE
            findViewById<TextView>(R.id.emptyView).visibility = View.VISIBLE
        }
        else{
            recyclerView.visibility = View.VISIBLE
            findViewById<TextView>(R.id.emptyView).visibility = View.INVISIBLE
        }
    }

//    fun deleteClick(credential: CredentialsModel){
//        val db = DataBaseHandler(this)
//        if(db.deleteData(credential.id)){
//            //adapter.notifyItemRemoved(position)
//            Toast.makeText(applicationContext,"Deleted", Toast.LENGTH_SHORT).show()
//        }
//    }

    fun addNewCredentials(view : View){
        print("hello world")
        val intent = Intent(this, AddDetailActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}


