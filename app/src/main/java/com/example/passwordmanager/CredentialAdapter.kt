package com.example.passwordmanager

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_layout.view.*

class CredentialAdapter(
    private val items: List<CredentialsModel>,
    ctx: Context, val clickListener: (CredentialsModel) -> Unit
): RecyclerView.Adapter<CredentialAdapter.ViewHolder>() {
    var context = ctx
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(credential: CredentialsModel,clickListener: (CredentialsModel) -> Unit){
            itemView.urlView.text = credential.url
            itemView.userNameView.text = credential.userName
            itemView.passwordView.text = credential.password
            itemView.noteView.text = credential.note
            itemView.delButton.setOnClickListener{clickListener(credential)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val credential:CredentialsModel = items[position]
        holder.bind(credential,clickListener)
    }

}