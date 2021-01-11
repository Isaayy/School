package com.example.shoutbox.ui.chat

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoutbox.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ChatFragment : Fragment() {

    private lateinit var chatViewModel: ChatViewModel

    private val mRecyclerView: RecyclerView? = null
    private val mAdapter: RecyclerView.Adapter<*>? = null
    private val mLayoutManager: RecyclerView.LayoutManager? = null


    val list = ArrayList<RecycleViewItem>()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        chatViewModel =
                ViewModelProvider(this).get(ChatViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chat, container, false)

        val letList = aaa(root)

        // post
        val postBtn = root.findViewById(R.id.post_btn) as Button
        postBtn.setOnClickListener {
            // your code to perform when the user clicks on the button
            val sharedPreferences = this.getActivity()?.getSharedPreferences("pref", Context.MODE_PRIVATE)
            val savedLogin = sharedPreferences?.getString("STRING_KEY",null)

            val jsonObject = JSONObject()
            jsonObject.put("content", "Jack")
            jsonObject.put("login", "3540")


        }

        return root

    }


    private fun aaa(root: View): List<RecycleViewItem> {

        val rf = Retrofit.Builder()
                .baseUrl(ApiInterface.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var API = rf.create(ApiInterface::class.java)

        var call = API.posts

        call?.enqueue(object:Callback<List<PostModel?>?>{
            override fun onFailure(call: Call<List<PostModel?>?>, t: Throwable) {
            }

            override fun onResponse(
                    call: Call<List<PostModel?>?>,
                    response: Response<List<PostModel?>?>
            ) {
                for (comment in response.body()!!) {
                    if (comment != null) {
                        val item = RecycleViewItem(comment.login.toString(),comment.date.toString(),comment.content.toString())
                        list += item
                    }

                }
                root.findViewById<RecyclerView>(R.id.id_recyclerView).adapter = RecycleViewAdapter(list)
                root.findViewById<RecyclerView>(R.id.id_recyclerView).layoutManager = LinearLayoutManager(activity)
                root.findViewById<RecyclerView>(R.id.id_recyclerView).setHasFixedSize(true)

            }
        })

        return list;
    }
}