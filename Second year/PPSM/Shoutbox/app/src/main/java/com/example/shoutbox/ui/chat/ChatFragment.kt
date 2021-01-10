package com.example.shoutbox.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoutbox.*
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


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        chatViewModel =
                ViewModelProvider(this).get(ChatViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chat, container, false)

        // RECYCLER
        val letList = aaa( 500)

        root.findViewById<RecyclerView>(R.id.id_recyclerView).adapter = RecycleViewAdapter(letList)
        root.findViewById<RecyclerView>(R.id.id_recyclerView).layoutManager = LinearLayoutManager(context)
        root.findViewById<RecyclerView>(R.id.id_recyclerView).setHasFixedSize(true)



        return root
    }

    private  fun aaa (size: Int): List<RecycleViewItem> {

        val list = ArrayList<RecycleViewItem>()
//
//        for (i in 0 until size) {
//
//            val item = RecycleViewItem("login", "Item $i", "Line 2")
//            list += item
//        }

        // API CALL

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
                var postList: List<PostModel>? = response.body() as List<PostModel>
                var post = arrayOfNulls<String>(postList!!.size)

                val exampleList: ArrayList<ExampleItem> = ArrayList()

                for (i in postList!!.indices)
                    exampleList.add(ExampleItem(postList!![i]!!.login.toString(), postList!![i]!!.date.toString(), postList!![i]!!.content.toString()))


                for (item in exampleList) {
                    var x = 0;
                    val i = RecycleViewItem(postList!![x]!!.login.toString(), postList!![x]!!.date.toString(), postList!![x]!!.content.toString())
                    list += i
                    x++
                }
            }

        })
        return list;
    }
}