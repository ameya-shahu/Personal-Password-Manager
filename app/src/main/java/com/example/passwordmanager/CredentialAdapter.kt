package com.example.passwordmanager

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

//private var items: MutableList<CredentialsModel>,

class CredentialAdapter(ctx: Context, val credentialList : ArrayList<CredentialsModel>): RecyclerView.Adapter<CredentialAdapter.ViewHolder>() {
    var context = ctx

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val urlView = itemView.findViewById(R.id.urlView) as TextView
        val userNameView = itemView.findViewById(R.id.userNameView) as TextView
        val passwordView = itemView.findViewById(R.id.passwordView) as TextView
        val noteView = itemView.findViewById(R.id.noteView) as TextView
        val delButton = itemView.findViewById(R.id.delButton) as Button
        val cpyButton = itemView.findViewById(R.id.cpyButton) as Button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return credentialList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val credential: CredentialsModel = credentialList[position]
        holder.urlView.text = credential.url
        holder.userNameView.text = credential.userName
        holder.passwordView.text = credential.password
        holder.noteView.text = credential.note

        holder.delButton.setOnClickListener {
            val db = DataBaseHandler(context)
            if (db.deleteData(credential.id)) {
                credentialList.removeAt(holder.getAdapterPosition())
                if(credentialList.isEmpty()){
                    (context as MainActivity).displayEmptyText()
                }
                notifyItemRemoved(position)
            }
        }

        holder.cpyButton.setOnClickListener {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipUser = ClipData.newPlainText("username", credential.userName)
            clipboard.setPrimaryClip(clipUser)
            Toast.makeText(context, "Copied username to clipboard", Toast.LENGTH_SHORT).show()
            val clipPass = ClipData.newPlainText("password", credential.password)
            clipboard.setPrimaryClip(clipPass)
            Toast.makeText(context, "Copied password to clipboard", Toast.LENGTH_SHORT).show()
        }
    }
}