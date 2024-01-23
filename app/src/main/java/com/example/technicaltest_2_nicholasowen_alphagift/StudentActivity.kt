package com.example.technicaltest_2_nicholasowen_alphagift

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage


class StudentActivity : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        userRecyclerView = findViewById(R.id.recyclerStudent)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<User>()
        getUserData()
    }

    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance("https://alphagift-nicholasowen-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    userArrayList.clear()

                    for (userSnapshot in snapshot.children) {
                        val userId = userSnapshot.key.toString()
                        val name = userSnapshot.child("Name").getValue(String::class.java)
                        val address = userSnapshot.child("Address").getValue(String::class.java)
                        val storageReference = FirebaseStorage.getInstance().getReference("images").child("$userId.jpg")

                        val user = User(name, address, storageReference)
                        userArrayList.add(user)
                    }

                    userRecyclerView.adapter = UserAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

}
