package com.harsh.drc_practical.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harsh.drc_practical.data.APIResult
import com.harsh.drc_practical.data.ApiClient
import com.harsh.drc_practical.data.ApiInterface
import com.harsh.drc_practical.model.Article
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private var api: ApiInterface
    val newsLiveData = MutableLiveData<APIResult<ArrayList<Article>>>()

    init {
        api = getApi()
    }

    /**
     * Get news headlines data from the server
     */
    fun getNewsHeadlines(source: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = api.getHeadLines(source, apiKey)
                /**
                 * Check response is correct or not
                 */
                if (response.isSuccess) {
                    /**
                     * Check response is not null if it is then return with  error message
                     */
                    response.data?.let {
                        newsLiveData.value = APIResult.Success(it)
                    } ?: kotlin.run {
                        newsLiveData.value =
                            APIResult.Error(IllegalStateException("Something went wrong."))
                    }
                } else {
                    newsLiveData.value =
                        APIResult.Error(IllegalStateException("Something went wrong."))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                newsLiveData.value = APIResult.Error(IllegalStateException("Something went wrong."))
            }
        }
    }

    /**
     * Get API request methods
     */
    private fun getApi(): ApiInterface {
        return ApiClient.getInstance().create(ApiInterface::class.java)
    }
}