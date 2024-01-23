package com.example.technicaltest_2_nicholasowen_alphagift

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

class UserAdapter(private val userList: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.student_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.name.text = currentItem.Name
        holder.address.text = currentItem.Address
        loadImageFromStorageReference(currentItem.imgUri, holder.imgItem)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.nameStudent)
        val address: TextView = itemView.findViewById(R.id.addressStudent)
        val imgItem: ImageView = itemView.findViewById(R.id.imageProfile)
    }

    fun loadImageFromStorageReference(imgUri: StorageReference?, imageView: ImageView) {
        imgUri?.downloadUrl?.addOnSuccessListener { uri ->
            val picasso = Picasso.get()
            picasso.load(uri)
                .into(imageView)
        }?.addOnFailureListener {
        }
    }

}
