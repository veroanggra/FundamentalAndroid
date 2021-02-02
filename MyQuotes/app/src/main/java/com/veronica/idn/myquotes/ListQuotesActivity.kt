package com.veronica.idn.myquotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.veronica.idn.myquotes.databinding.ActivityListQuotesBinding
import com.veronica.idn.myquotes.databinding.ActivityMainBinding
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.lang.Exception

class ListQuotesActivity : AppCompatActivity() {
    companion object{
        private val TAG = ListQuotesActivity::class.java.simpleName
    }
    private lateinit var binding: ActivityListQuotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "List of Quotes"
        getListQuotes()
    }

    private fun getListQuotes() {
        binding.pbList.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://quote-api.dicoding.dev/list"
        client.get(url, object: AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                binding.pbList.visibility = View.INVISIBLE
                val listQuote = ArrayList<String>()
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        val quote = jsonObject.getString("en")
                        val author = jsonObject.getString("author")
                        listQuote.add("\n$quote\n - $author\n")
                    }
                    val adapter = ArrayAdapter(this@ListQuotesActivity, android.R.layout.simple_list_item_1, listQuote)
                    binding.lvQuotes.adapter = adapter
                }catch (e:Exception){
                    Toast.makeText(this@ListQuotesActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                binding.pbList.visibility = View.INVISIBLE
                val errorMessage = when (statusCode){
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode :Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@ListQuotesActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}