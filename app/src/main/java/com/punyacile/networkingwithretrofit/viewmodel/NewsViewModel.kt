package com.punyacile.networkingwithretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.punyacile.networkingwithretrofit.NewsAdapter
import com.punyacile.networkingwithretrofit.model.DataNews
import com.punyacile.networkingwithretrofit.model.ResponseAddNews
import com.punyacile.networkingwithretrofit.model.ResponseDataNewsItem
import com.punyacile.networkingwithretrofit.model.ResponseUpdateNews
import com.punyacile.networkingwithretrofit.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {

    val liveDataNews = MutableLiveData<List<ResponseDataNewsItem>>()
    private val postDataNews = MutableLiveData<ResponseAddNews>()
    private val updateDataNews = MutableLiveData<List<ResponseUpdateNews>>()


    fun getDataNews(): MutableLiveData<List<ResponseDataNewsItem>> {
        return liveDataNews
    }

    fun postNews(): MutableLiveData<ResponseAddNews> {
        return postDataNews
    }

    fun updateNews(): MutableLiveData<List<ResponseUpdateNews>> {
        return updateDataNews
    }

    fun callApiNews() {
        RetrofitClient.instance.getAllNews()
            .enqueue(object : Callback<List<ResponseDataNewsItem>> {
                override fun onResponse(
                    call: Call<List<ResponseDataNewsItem>>,
                    response: Response<List<ResponseDataNewsItem>>
                ) {
                    if (response.isSuccessful) {
                        liveDataNews.postValue(response.body())
                    } else {
                        liveDataNews.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataNewsItem>>, t: Throwable) {
                    liveDataNews.postValue(null)
                }
            })
    }

    fun addDataNews(title: String, image: String, author: String, description: String) {
        RetrofitClient.instance.postDataNews(DataNews(title, image, author, description))
            .enqueue(object : Callback<ResponseAddNews> {
                override fun onResponse(
                    call: Call<ResponseAddNews>,
                    response: Response<ResponseAddNews>
                ) {
                    if (response.isSuccessful) {
                        postDataNews.postValue(response.body())
                    } else {
                        postDataNews.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseAddNews>, t: Throwable) {
                    postDataNews.postValue(null)
                }
            })
    }

    fun updateDataNews(id: Int, title: String, image: String, author: String, description: String) {
        RetrofitClient.instance.updateDataNews(id, DataNews(title, image, author, description))
            .enqueue(object : Callback<List<ResponseUpdateNews>> {
                override fun onResponse(
                    call: Call<List<ResponseUpdateNews>>,
                    response: Response<List<ResponseUpdateNews>>
                ) {
                    if (response.isSuccessful) {
                        updateDataNews.postValue(response.body())
                    } else {
                        updateDataNews.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseUpdateNews>>, t: Throwable) {
                    updateDataNews.postValue(null)
                }
            })
    }
}
