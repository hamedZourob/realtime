package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btndelet.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            db.collection("person")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        db.collection("person").document(document.id).delete()
                            .addOnSuccessListener { e ->
                            }
                        getData()
                        progressBar.visibility = View.GONE
                    }
                }
        }
        btn2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        getData()
    }

    fun getData() {
        db.collection("person")
            .get()
            .addOnSuccessListener { result ->
                progressBar.visibility = View.GONE
                val arrayAdapter: ArrayAdapter<*>
                val users = ArrayList<String>()
                for (document in result) {
                    var name = document.get("name").toString()
                    var number = document.get("number").toString()
                    var address = document.get("address").toString()
                    val result = "The Person Data is \n the name:$name \n" +
                            " id :${document.id}\n number:$number \n address:$address \n"
                    users.add(result)
                }
                var myListView = findViewById<ListView>(R.id.userlist)
                arrayAdapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1, users
                )
                myListView.adapter = arrayAdapter
            }


    }
}