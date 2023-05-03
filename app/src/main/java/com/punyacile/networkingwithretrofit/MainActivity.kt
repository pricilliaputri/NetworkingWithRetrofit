package com.punyacile.networkingwithretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.punyacile.networkingwithretrofit.NewsAdapter
import com.punyacile.networkingwithretrofit.databinding.ActivityMainBinding
import com.punyacile.networkingwithretrofit.model.ResponseDataNewsItem
import com.punyacile.networkingwithretrofit.network.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataNews()
    }

    fun getDataNews(){
        RetrofitClient.instance.getAllNews().enqueue(object : retrofit2.Callback<List<ResponseDataNewsItem>>{
            override fun onResponse(
                call: Call<List<ResponseDataNewsItem>>,
                response: Response<List<ResponseDataNewsItem>>
            ) {
                if(response.isSuccessful){
                    // show data
                    binding.rvNews.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                    binding.rvNews.adapter = NewsAdapter(response.body()!!)
                } else {
                    Toast.makeText(this@MainActivity, "Failed load data", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<ResponseDataNewsItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "", Toast.LENGTH_SHORT).show()
            }
        })
    }
}