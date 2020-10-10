package com.example.cinema.ui.main

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.cinema.R
import com.example.cinema.data.modelData.CinemaSearchModel
import com.example.cinema.data.remote.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    private val adapter = RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitBuilder.getService()
        //90b2f7c2
        receycler.adapter = adapter
        search.doAfterTextChanged {
            if (!it.isNullOrEmpty() && it.toString().length > 2)  // если текстбольше 2 букв
                getSearch(it.toString())  // то делаеем запрос
        }
    }




   private fun getSearch(text: String) {

      RetrofitBuilder.getService()?.getCinema("90b2f7c2",text)

          ?.enqueue(object : Callback<CinemaSearchModel>{
              override fun onResponse(
                  call: Call<CinemaSearchModel>,
                  response: Response<CinemaSearchModel>
              ) {
                  if (response.isSuccessful && response.body() != null){
                      val arrays = response.body()?.search?.map { it.title }?.toTypedArray()
                      //map создает list из Strings , нам необходим array а не list , поэтому toTypedArray() конвертирует стринги в array
                      if (response.isSuccessful && response.body() != null){
                          adapter.submitList(response.body()?.search)       // sbmitlist -> update как receyclerView для отображен списка
                      }
                  }
              }
              override fun onFailure(call: Call<CinemaSearchModel>, t: Throwable) {
                  Toast.makeText(applicationContext, t.localizedMessage , Toast.LENGTH_LONG).show()
              }
          })
    } }