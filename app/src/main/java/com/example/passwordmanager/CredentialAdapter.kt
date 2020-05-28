package com.example.passwordmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CredentialAdapter(val items : List<CredentialsModel>,ctx: Context): RecyclerView.Adapter<CredentialAdapter.ViewHolder>() {

    var credentialList = items
    var context = ctx



    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val urlView = itemView.findViewById(R.id.urlView) as TextView
        val userNameView = itemView.findViewById(R.id.userNameView ) as TextView
        val passwordView = itemView.findViewById(R.id.passwordView) as TextView
        val noteView = itemView.findViewById(R.id.noteView) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return credentialList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val credential:CredentialsModel = credentialList[position]
        holder.urlView.text  = credential.url
        holder.userNameView.text  = credential.userName
        holder.passwordView.text  = credential.password
        holder.noteView.text  = credential.note


    }
}