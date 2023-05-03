package com.punyacile.networkingwithretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.punyacile.networkingwithretrofit.databinding.ActivityUpdateNewsBinding
import com.punyacile.networkingwithretrofit.viewmodel.NewsViewModel

class UpdateNewsActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener {
            var id = intent.getStringExtra("update")
            var title = binding.etUpdateTitle.text.toString()
            var image = binding.etUpdateImage.text.toString()
            var author = binding.etUpdateAuthor.text.toString()
            var description = binding.etUpdateDescription.text.toString()

            updateNews(id!!.toInt(), title, image, author, description)
        }
    }

    fun updateNews(id: Int, title: String, image: String, author: String, description: String) {
        var viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        viewModel.updateDataNews(id, title, image, author, description)
        viewModel.updateNews().observe(this) {
            if (it != null) {
                Toast.makeText(this, "Data Berhasil di Update", Toast.LENGTH_SHORT).show()
            }
        }

    }
}