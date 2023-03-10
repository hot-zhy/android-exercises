package com.zhy.weatherrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendRequest.setOnClickListener {
            sendWithOkHttp()
        }
    }

    private fun sendWithOkHttp() {
        thread {
            try {
                val client=OkHttpClient()
                val request=Request.Builder()
                    .url("https://restapi.amap.com/v3/weather/weatherInfo?city=110000&key=65069e6e1cebabaf68efe9391db90b56&extensions=all")
                    .build()
                val response=client.newCall(request).execute()
                val responseData= response.body?.string()
                if(responseData!=null){
                    parseJSONWithJSONObject(responseData)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    private fun parseJSONWithJSONObject(responseData: String) {
        try {
            val weatherList=JSONObject(responseData)
            val casts=weatherList.getJSONArray("forecasts").getJSONObject(0)
            val city=casts.getString("city")
            val province=casts.getString("province")
            val reporttime=casts.getString("reporttime")
            val liveList=JSONArray(casts.getString("casts"))
            val stringBuilder=StringBuilder()
            for (i in 0 until liveList.length()){
                val jsonObject=liveList.getJSONObject(i)
                val date=jsonObject.getString("date")
                val week=jsonObject.getString("week")
                val dayweather=jsonObject.getString("dayweather")
                val nightweather=jsonObject.getString("nightweather")
                val daytemp=jsonObject.getString("daytemp")
                val nighttemp=jsonObject.getString("nighttemp")
                val daywind=jsonObject.getString("daywind")
                val nightwind=jsonObject.getString("nightwind")
                val daypower=jsonObject.getString("daypower")
                val nightpower=jsonObject.getString("nightpower")
                stringBuilder.append("??????:${province}\n??????:${city}\n??????:${date}\n???:${week}" +
                        "\n????????????:${dayweather}\n????????????:${nightweather}???\n????????????:${daytemp}\n????????????:${nighttemp}" +
                        "\n????????????:${daywind}\n????????????:${nightwind}\n????????????:${daypower}\n????????????:${nightpower}\n????????????:${reporttime}\n\n\n")
            }
            showResponse(stringBuilder.toString())
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun showResponse(data: String) {
        runOnUiThread {
            responseText.text=data
        }
    }
}