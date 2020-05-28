package com.example.passwordmanager

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


const val  DATABASE_NAME = "Credentials"
const val  TABLE_NAME = "details"
const val COL_URL = "url"
const val COL_USERNAME = "userName"
const val COL_PASSWORD = "password"
const val COL_NOTE = "note"
const val COL_ID = "id"


class DataBaseHandler(var context : Context): SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE "+ TABLE_NAME+" ("+
                COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL_USERNAME + " VARCHAR(30), "+
                COL_PASSWORD + " VARCHAR(30), "+
                COL_URL +  " VARCHAR(50), "+
                COL_NOTE + " VARCHAR(500) );"

        try{
            db?.execSQL(createTable)
        }catch (e: Exception){
            print(e.stackTrace)
        }




    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $DATABASE_NAME")
        onCreate(db)
    }

    fun insertData(credential : CredentialsModel): Int {
        val db = this.writableDatabase
        var statusFlag:Int = 1

        var contentValue = ContentValues()
        contentValue.put(COL_URL,credential.url)
        contentValue.put(COL_USERNAME,credential.userName)
        contentValue.put(COL_PASSWORD,credential.password)
        contentValue.put(COL_NOTE,credential.note)
       var result =  db.insert(TABLE_NAME,null,contentValue)
        if(result==-1.toLong()){
            Toast.makeText(context,"Failed to save",Toast.LENGTH_SHORT).show()
            statusFlag = 0
        }
        else{
            Toast.makeText(context,"saved",Toast.LENGTH_SHORT).show()
        }
        db.close()
        return statusFlag
    }

    fun readCredentials() : MutableList<CredentialsModel>{
        var list : MutableList<CredentialsModel> = ArrayList()
        val db = this.readableDatabase

        val getDataQuery = "SELECT * FROM "+ TABLE_NAME
        val result = db.rawQuery(getDataQuery,null)
        if(result.moveToFirst()){
            do{
                var credential = CredentialsModel()
                credential.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                credential.url = result.getString(result.getColumnIndex(COL_URL))
                credential.userName = result.getString(result.getColumnIndex(COL_USERNAME))
                credential.password = result.getString(result.getColumnIndex(COL_PASSWORD))
                credential.note = result.getString(result.getColumnIndex(COL_NOTE))

                list.add(credential)

            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }
}