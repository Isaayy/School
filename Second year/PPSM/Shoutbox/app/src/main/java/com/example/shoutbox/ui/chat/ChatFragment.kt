package com.example.shoutbox.ui.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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


    val list = ArrayList<RecycleViewItem>()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        chatViewModel =
                ViewModelProvider(this).get(ChatViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chat, container, false)

        val letList = apiCallFun(root)

        // ####################################################
        // pull to refresh
        val pullToRefresh: SwipeRefreshLayout = root.findViewById(R.id.pullToRefresh)
        pullToRefresh.setOnRefreshListener {
            Log.i("test","pull dziala")
            pullToRefresh.isRefreshing = false
        }

        return root

    }

    // ####################################################
    // api call
    private fun apiCallFun(root: View): List<RecycleViewItem> {

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
                        // ####################################################
                        // add elements from API to RecycleView
                        val commentShort = cutString(comment.content.toString(),20)
                        val item = RecycleViewItem(comment.login.toString(),comment.date.toString(),commentShort)
                        list += item
                    }

                }
                // ####################################################
                // set up RecycleView
                root.findViewById<RecyclerView>(R.id.id_recyclerView).adapter = RecycleViewAdapter(list)
                root.findViewById<RecyclerView>(R.id.id_recyclerView).layoutManager = LinearLayoutManager(activity)
                root.findViewById<RecyclerView>(R.id.id_recyclerView).setHasFixedSize(true)

            }
        })

        return list;
    }
}

// ####################################################
// shorter string
public fun cutString(str: String, maxSize: Int):String{
    var s = str;
    s = s.substring(0, Math.min(s.length, maxSize));
    if(s.length > maxSize)
    s+="..."
    return s;
}
