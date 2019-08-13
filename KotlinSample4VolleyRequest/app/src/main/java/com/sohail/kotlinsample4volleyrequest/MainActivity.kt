package com.sohail.kotlinsample4volleyrequest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //fetch data on button click
        fetchData.setOnClickListener { getData() }
    }

    fun getData() {

        //add request to queue
        val queue=Volley.newRequestQueue(this)
        val url="https://api.github.com/search/users?q=eyehunt"

        val req= StringRequest(
            Request.Method.GET,url, Response.Listener { response ->
                var resp=response.toString()
                var jsonObj=JSONObject(resp)
                var jsonArr=jsonObj.getJSONArray("items")
                for(i in 0 until jsonArr.length()){
                    var jsonItem=jsonArr.getJSONObject(i)
                    datatxt.text=jsonItem.toString()
                }
            },
            Response.ErrorListener { datatxt.text="Unable to fetch the data" })

        //Add request to queue
        queue.add(req)

    }
}