package com.example.fail



 //https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=eee7855586eb4ffcac2847b8b2386891

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL="https://newsapi.org/v2/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerview= findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager=LinearLayoutManager(this)
        //val values=fetchData()
        //val adapter = NewsListAdapter(values)
        //recyclerview.adapter=adapter
        fetchData()
    }

    private fun fetchData() {
        val retrofitbuilder= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)
        val retrofitData=retrofitbuilder.getNews("us","business","eee7855586eb4ffcac2847b8b2386891")

        retrofitData.enqueue(object : Callback<MainDataItem>{
            override fun onResponse(call: Call<MainDataItem>, response: Response<MainDataItem>) {
                val responseBody=response.body()!!
                val adapter=NewsListAdapter(responseBody.articles)
                adapter.notifyDataSetChanged()
                val recyclerview= findViewById<RecyclerView>(R.id.recyclerView)
                recyclerview.adapter=adapter
            }

            override fun onFailure(call: Call<MainDataItem>, t: Throwable) {
                Log.d("TAG","Message")
            }

        })


    }
}