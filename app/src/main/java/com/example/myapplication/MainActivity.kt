package com.example.myapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var count:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var database=Firebase.database
        val myRef =database.getReference()
        save.setOnClickListener {
            var name = personName.text.toString()
            var id = personID.text.toString()
            var age = personAge.text.toString()
            val person = hashMapOf(
                "name" to name ,
                "number" to age,
                "address" to id
            )
            myRef.child("person").child("${count}").setValue(person)
            count++
            Toast.makeText(applicationContext,"Success",Toast.LENGTH_LONG).show()
        }
        get.setOnClickListener {
            // Read from the database
            // Read from the database
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue(String::class.java)
                    textData.text =value.toString()
                    Toast.makeText(applicationContext,"Success",Toast.LENGTH_LONG).show()

                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Toast.makeText(applicationContext,"failled" ,Toast.LENGTH_LONG).show()
                }
            })
        }
//        progBarTwo.visibility = View.GONE
//        btn3.setOnClickListener {
//            val intent = Intent(this, MainActivity2::class.java)
//            startActivity(intent)
//        }
//        btn.setOnClickListener {
//            progBarTwo.visibility = View.VISIBLE
//            var name = number.text.toString()
//            var address = address.text.toString()
//            var number = fullname.text.toString()
//
//            if (name.isEmpty() || number.isEmpty() || address.isEmpty()) {
//                progBarTwo.visibility = View.GONE
//                Toast.makeText(this, "The field is empty ", Toast.LENGTH_SHORT).show()
//            } else {
//                val person = hashMapOf(
//                    "name" to "$name",
//                    "number" to "$number",
//                    "address" to "$address"
//                )
//                db.collection("person").add(person).addOnSuccessListener { e ->
//                    progBarTwo.visibility = View.GONE
//                    Toast.makeText(this, "True", Toast.LENGTH_SHORT).show()
//                    this.fullname.text.clear()
//                    this.address.text.clear()
//                    this.number.text.clear()
//                }.addOnFailureListener { e ->
//                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
//                }
            }

        }


