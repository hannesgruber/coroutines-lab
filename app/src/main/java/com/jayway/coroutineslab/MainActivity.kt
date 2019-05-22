package com.jayway.coroutineslab

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.jayway.coroutineslab.model.Product
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.DividerItemDecoration


class MainActivity : AppCompatActivity() {

    val LOG_TAG = "MainActivity"
    val api = Api.create()
    val searchResult = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search.setOnEditorActionListener { textView, id, event ->
            if (id == EditorInfo.IME_ACTION_SEARCH && textView != null) {
                search(textView.text.toString())
                closeKeyboard()
                true
            } else {
                false
            }
        }

        recyclerView.adapter = ProductAdapter(searchResult)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun search(s : String) {
        api.searchProducts(s).enqueue(object : Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>?, t: Throwable?) {
                Log.d(LOG_TAG, "Search failed with error " + t.toString())
            }

            override fun onResponse(call: Call<List<Product>>?, response: Response<List<Product>>?) {
                if (response?.code() == 200) {
                    Log.d(LOG_TAG, "Search succeeded with size " + response.body().size)
                    searchResult.clear()
                    searchResult.addAll(response.body())
                    recyclerView.adapter?.notifyDataSetChanged()
                } else {
                    Log.d(LOG_TAG, "Search failed with code " + response?.code())
                }
            }
        })
    }

    private fun closeKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(search.windowToken, 0)
    }
}
