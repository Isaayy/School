package com.example.shoutbox

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


// ####################################################
// ACTIVITY FOR SINGLE ITEM
class SingleComment : AppCompatActivity() {



    @Override
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.shoutbox.R.layout.single_comment)
        incomingIntent
    }

    private val incomingIntent: Unit private get() {
            // ####################################################
            // READ DATA FROM INTENT
            if (intent.hasExtra("login") && intent.hasExtra("date") && intent.hasExtra("comment")) {
                val login = intent.getStringExtra("login")
                val date = intent.getStringExtra("date")
                val comment = intent.getStringExtra("comment")

                // ####################################################
                // INSERT THEM INTO TEXT VIEWS
                val loginTextView = findViewById<TextView>(R.id.loginTextView)
                val commentTextView = findViewById<TextView>(R.id.commentTextView)
                val dateTextView = findViewById<TextView>(R.id.dateTextView)
                loginTextView.text = login
                commentTextView.text = comment
                dateTextView.text = date

                commentTextView.isFocusable = false

                val test = this.getSharedPreferences("pref", Context.MODE_PRIVATE)
                val savedLogin = test?.getString("STRING_KEY",null)


                // ####################################################
                // allow edit if login match
                if(savedLogin == login){
                    commentTextView.isFocusableInTouchMode = true
                }
                // ####################################################
                // update recycleView

                val pullToRefresh: SwipeRefreshLayout = findViewById(R.id.pullToRefresh)
                pullToRefresh.setOnRefreshListener {
                    var newComment = commentTextView.text
                    setContentView(R.layout.fragment_chat)
                    val adapter= findViewById<RecyclerView>(R.id.id_recyclerView)
                    
                    setContentView(R.layout.single_comment)
                    val loginTextView = findViewById<TextView>(R.id.loginTextView)
                    val commentTextView = findViewById<TextView>(R.id.commentTextView)
                    val dateTextView = findViewById<TextView>(R.id.dateTextView)
                    loginTextView.text = login
                    commentTextView.text = newComment
                    dateTextView.text = date
                    pullToRefresh.isRefreshing = false
                }
            }
        }
}