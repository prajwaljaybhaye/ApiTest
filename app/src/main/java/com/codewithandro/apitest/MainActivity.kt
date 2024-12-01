package com.codewithandro.apitest

import android.app.ProgressDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: ProdAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //featch  api -> retrofitBuilder
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(prodApiInterface::class.java)

        //featch the interface inside the created own function acesss
        val retrofitData = retrofitBuilder.prodGetData()

        //check the api response
        val prograsBar = ProgressDialog(this@MainActivity)
        prograsBar.setMessage("Please Wait While Data is Featch")
        prograsBar.show()

        //start the getting data
        retrofitData.enqueue(object :Callback<prodMainFile>{
            override fun onResponse(p0: Call<prodMainFile>, res: Response<prodMainFile>) {

               prograsBar.dismiss()//closed
                //if api call sucessfully then getting data
                //response.body
                val retrofitBody = res.body()
                //the inside the retrofitBody the productList get
                val proList = retrofitBody?.products
                //then the arrayList pass the Adapter to set the views data

                //data ready

                recyclerView = findViewById(R.id.recyclerView)

                myAdapter = ProdAdapter(this@MainActivity,proList)

                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = myAdapter
            }

            override fun onFailure(p0: Call<prodMainFile>, t: Throwable) {
                //else the api fails
                prograsBar.dismiss()
            }

        })



    }
}