package com.example.passwordmanager

class CredentialsModel {
    var id : Int = 0
    var url : String = ""
    var userName : String = ""
    var password : String = ""
    var note : String = ""

    constructor(url:String, userName:String, password: String, note : String){
        this.url = url
        this.userName = userName
        this.password = password
        this.note = note
    }

    constructor(){} //empty constructor
}