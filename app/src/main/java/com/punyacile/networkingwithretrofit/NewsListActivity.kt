package com.punyacile.networkingwithretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.punyacile.networkingwithretrofit.NewsAdapter
import com.punyacile.networkingwithretrofit.databinding.ActivityNewsListBinding
import com.punyacile.networkingwithretrofit.viewmodel.NewsViewModel

class NewsListActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewsListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataNews()

        binding.fabTambah.setOnClickListener {
            startActivity(Intent(this, AddNewsActivity::class.java))
        }
    }
    override fun onResume() {
        super.onResume()
        showDataNews()
    }

    fun showDataNews() {
        val viewModelNews = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModelNews.callApiNews()
        viewModelNews.liveDataNews.observe(this) {
            if (it != null) {
                binding.rvNews2.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvNews2.adapter = NewsAdapter(it)
            }
        }
    }
}