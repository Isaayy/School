package com.example.shoutbox.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shoutbox.ApiInterface
import com.example.shoutbox.ExampleItem
import com.example.shoutbox.PostModel
import com.example.shoutbox.R
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

//        val textView: TextView = root.findViewById(R.id.result)

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
//                    textView.text = postList!![i]!!.content;
//                    post[i] = postList!![i]!!.content

//                mRecyclerView = root.findViewById(R.id.recyclerView)

//                mRecyclerView = findViewById(R.id.recyclerView);
//                mRecyclerView.setHasFixedSize(true);
//                mLayoutManager = new LinearLayoutManager(this);
//                mAdapter = new ExampleAdapter(exampleList);
//                mRecyclerView.setLayoutManager(mLayoutManager);
//                mRecyclerView.setAdapter(mAdapter);

//                var adapter = ArrayAdapter<String>(exampleList,android.R.layout.simple_dropdown_item_1line,post)
//                listview.adapter = adapter


//                textView.text = "oo tak"
            }

        })

        return root
    }
}