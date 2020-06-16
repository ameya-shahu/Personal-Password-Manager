package com.example.passwordmanager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_details.*

class AddDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_details)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    fun saveNewDetails(view:View){
        val url = websiteUrl.text.toString()
        val userName = websiteUsername.text.toString()
        val password = websitePassword.text.toString()
        val note = websiteNote.text.toString()

        if(url.isBlank()) Toast.makeText(this,"Enter APP name or URL", Toast.LENGTH_SHORT).show()
        else if(userName.isBlank()) Toast.makeText(this,"Enter Username", Toast.LENGTH_SHORT).show()
        else if(password.isBlank()) Toast.makeText(this,"Enter Password", Toast.LENGTH_SHORT).show()
        else{
            val credential = CredentialsModel(url,userName,password,note)
            val db = DataBaseHandler(this)
            val statusFlag = db.insertData(credential)

            if(statusFlag==1){
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

        }


    }
}
