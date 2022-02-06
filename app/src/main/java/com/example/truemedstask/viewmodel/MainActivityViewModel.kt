package com.example.truemedstask.viewmodel

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.truemedstask.R
import com.example.truemedstask.core.NotNullMutableLiveData
import com.example.truemedstask.model.Article
import com.example.truemedstask.network.RetrofitService
import com.example.truemedstask.utils.Utilities
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    lateinit var job: Job

    private var timeCountInMilliSeconds = 1 * 5000.toLong()

//    private val _articleList = MutableLiveData<List<Article>>()
//    val articleList: LiveData<List<Article>>
//        get() = _articleList

    var articleList: MutableLiveData<MutableList<Article>> = NotNullMutableLiveData(mutableListOf())

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val _currentTimeString = MutableLiveData<String>()
    val currentTimeString: LiveData<String>
        get() = _currentTimeString

    private val _enableRefresh = MutableLiveData<Boolean>()
    val enableRefresh: LiveData<Boolean>
        get() = _enableRefresh

    private val _showLoader = MutableLiveData<Boolean>()
    val showLoader: LiveData<Boolean>
        get() = _showLoader

    private val _networkAvailability = MutableLiveData<Boolean>()
    val networkAvailability: LiveData<Boolean>
        get() = _networkAvailability

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private var countDownTimer: CountDownTimer? = null

    /**
     * method to start count down timer
     */
    fun startCountDownTimer() {
        countDownTimer = object : CountDownTimer(timeCountInMilliSeconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTimeString.value = String.format(getApplication<Application>().getString(R.string.reset_in), hmsTimeFormatter(millisUntilFinished))
                _currentTime.value = millisUntilFinished
                _enableRefresh.value = false

            }

            override fun onFinish() {
                // changing the timer status to stopped
                _enableRefresh.value = true
                loadArticles()
            }
        }.start()
    }

    private fun loadArticles() {
        // Do an asynchronous operation to fetch articles.
        if(Utilities.isConnectedToNetwork(getApplication())) {
            getAllData()
        } else {
            _networkAvailability.value = false
            _showLoader.value = false
            _errorMessage.value = getApplication<Application>().resources.getString(R.string.no_internet)

        }
    }


    /**
     * method to convert millisecond to time format
     *
     * @param milliSeconds
     * @return HH:mm:ss time formatted string
     */
    private fun hmsTimeFormatter(milliSeconds: Long): String {
        return String.format(
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(milliSeconds),
            TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(
                    milliSeconds
                )
            ),
            TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(
                    milliSeconds
                )
            )
        )
    }


    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getAllData() {
//        withContext()
        _showLoader.postValue(true)
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {

            val response = RetrofitService.getInstance().getAllArticle()
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {
                    val output = response.body()!!.result!!.article
                    articleList.postValue(output)
                    _showLoader.postValue(false)
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        _errorMessage.postValue(message)
        _showLoader.postValue(false)
        _enableRefresh.postValue(true)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}