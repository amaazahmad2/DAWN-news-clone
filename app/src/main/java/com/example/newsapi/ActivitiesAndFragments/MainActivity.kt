package com.example.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.DataClasses.Article
import com.example.newsapi.DataClasses.NewsDataModel
import com.example.newsapp.SearchScreen
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.action_bar.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.include
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_search_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var newsArticles = ArrayList<Article>()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(action_bar)
        action_bar_back_button.isEnabled=false
        action_bar_back_button.visibility= View.INVISIBLE

        val transaction =  supportFragmentManager.beginTransaction()
        val fragment = SearchScreen()

        action_bar_search_button.setOnClickListener()
        {
            transaction.replace(R.id.fragment_container,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        recyclerView = findViewById(R.id.recycler_view)
        recycler_view.adapter = NewsAdapter(newsArticles,this)
        recyclerView.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        topNews()


        val standardBottomSheetBehavior=BottomSheetBehavior.from(bottom_sheet_contents)
        standardBottomSheetBehavior.peekHeight=125

        bottom_sheet_dawn_button.setOnClickListener ()
        {
            if(standardBottomSheetBehavior.state==BottomSheetBehavior.STATE_EXPANDED)
                standardBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
            else
                standardBottomSheetBehavior.state=BottomSheetBehavior.STATE_EXPANDED
        }

        bottom_sheet_top_button_peek.setOnClickListener()
        {
            topNews()
            action_bar_title.text="TOP NEWS"
            standardBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        }

        bottom_sheet_latest_button_peek.setOnClickListener()
        {
            latestNews()
            action_bar_title.text="LATEST"
            standardBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        }

        bottom_sheet_top_button.setOnClickListener()
        {
            topNews()
            action_bar_title.text="TOP NEWS"
            standardBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        }

        bottom_sheet_latest_button.setOnClickListener ()
        {
            latestNews()
            action_bar_title.text="TOP NEWS"
            standardBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        }

        bottom_sheet_pakistan_button.setOnClickListener()
        {
            pakistaniNews()
            action_bar_title.text="PAKISTAN NEWS"
            standardBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        }

        bottom_sheet_entertainment_button.setOnClickListener()
        {
            entertainmentNews()
            action_bar_title.text="ENTERTAINMENT NEWS"
            standardBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        }

        bottom_sheet_opinion_button.setOnClickListener()
        {
            opinions()
            action_bar_title.text="OPINIONS"
            standardBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        }

        bottom_sheet_prism_button.setOnClickListener()
        {
            prism()
            action_bar_title.text="PRISM"
            standardBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        }

        bottom_sheet_sports_button.setOnClickListener()
        {
            sports()
            action_bar_title.text="SPORT NEWS"
            standardBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        }

        bottom_sheet_world_button.setOnClickListener()
        {
            world()
            action_bar_title.text="WORLD NEWS"
            standardBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        }

        bottom_sheet_business_button.setOnClickListener()
        {
            business()
            action_bar_title.text="BUSINESS NEWS"
            standardBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        }

        bottom_sheet_magazine_button.setOnClickListener()
        {
            magazine()
            action_bar_title.text="MAGAZINE"
            standardBottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        }

        bottom_sheet_search_button.setOnClickListener()
        {
            transaction.replace(R.id.fragment_container,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


    }


    private fun topNews()
    {
        val call : Call<NewsDataModel> = ApiClient.getClient.getTopHeadlines("fd26e52a9dad4b38b037052d2b623a8f","google-news")
        call.enqueue(object : Callback<NewsDataModel>
        {

            override fun onResponse(call: Call<NewsDataModel>?, response: Response<NewsDataModel>?)
            {
                newsArticles.clear()
                newsArticles.addAll(response!!.body()!!.articles)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<NewsDataModel>?, t: Throwable?)
            {
                Toast.makeText(this@MainActivity,"FAILED TO LOAD",Toast.LENGTH_SHORT).show()
            }


        }
        )

    }

    private fun latestNews()
    {
        val call : Call<NewsDataModel> = ApiClient.getClient.getLatest("fd26e52a9dad4b38b037052d2b623a8f","publishedAt","en","cnn")
        call.enqueue(object : Callback<NewsDataModel>
        {

            override fun onResponse(call: Call<NewsDataModel>?, response: Response<NewsDataModel>?)
            {
                //Toast.makeText(this@MainActivity,response!!.body()!!.articles[0].title,Toast.LENGTH_SHORT).show()
                newsArticles.clear()
                newsArticles.addAll(response!!.body()!!.articles)
               recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<NewsDataModel>?, t: Throwable?)
            {
                Toast.makeText(this@MainActivity,"FAILED TO LOAD",Toast.LENGTH_SHORT).show()
            }
        }
        )

    }

    private fun pakistaniNews()
    {
        val call : Call<NewsDataModel> = ApiClient.getClient.getPakistani("fd26e52a9dad4b38b037052d2b623a8f","en","ary-news")
        call.enqueue(object : Callback<NewsDataModel>
        {

            override fun onResponse(call: Call<NewsDataModel>?, response: Response<NewsDataModel>?)
            {
                //Toast.makeText(this@MainActivity,response!!.body()!!.articles[0].title,Toast.LENGTH_SHORT).show()
                newsArticles.clear()
                newsArticles.addAll(response!!.body()!!.articles)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<NewsDataModel>?, t: Throwable?)
            {
                Toast.makeText(this@MainActivity,"FAILED TO LOAD",Toast.LENGTH_SHORT).show()
            }
        }
        )

    }

    private fun entertainmentNews()
    {
        val call : Call<NewsDataModel> = ApiClient.getClient.getEntertainment("fd26e52a9dad4b38b037052d2b623a8f","entertainment-weekly")
        call.enqueue(object : Callback<NewsDataModel>
        {

            override fun onResponse(call: Call<NewsDataModel>?, response: Response<NewsDataModel>?)
            {
                //Toast.makeText(this@MainActivity,response!!.body()!!.articles[0].title,Toast.LENGTH_SHORT).show()
                newsArticles.clear()
                newsArticles.addAll(response!!.body()!!.articles)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<NewsDataModel>?, t: Throwable?)
            {
                Toast.makeText(this@MainActivity,"FAILED TO LOAD",Toast.LENGTH_SHORT).show()
            }
        }
        )

    }

    private fun opinions()
    {
        val call : Call<NewsDataModel> = ApiClient.getClient.getOpinion("fd26e52a9dad4b38b037052d2b623a8f","general")
        call.enqueue(object : Callback<NewsDataModel>
        {

            override fun onResponse(call: Call<NewsDataModel>?, response: Response<NewsDataModel>?)
            {
                //Toast.makeText(this@MainActivity,response!!.body()!!.articles[0].title,Toast.LENGTH_SHORT).show()
                newsArticles.clear()
                newsArticles.addAll(response!!.body()!!.articles)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<NewsDataModel>?, t: Throwable?)
            {
                Toast.makeText(this@MainActivity,"FAILED TO LOAD",Toast.LENGTH_SHORT).show()
            }
        }
        )

    }

    private fun prism()
    {
        val call : Call<NewsDataModel> = ApiClient.getClient.getTopHeadlines("fd26e52a9dad4b38b037052d2b623a8f","ary-news")
        call.enqueue(object : Callback<NewsDataModel>
        {

            override fun onResponse(call: Call<NewsDataModel>?, response: Response<NewsDataModel>?)
            {
                //Toast.makeText(this@MainActivity,response!!.body()!!.articles[0].title,Toast.LENGTH_SHORT).show()
                newsArticles.clear()
                newsArticles.addAll(response!!.body()!!.articles)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<NewsDataModel>?, t: Throwable?)
            {
                Toast.makeText(this@MainActivity,"FAILED TO LOAD",Toast.LENGTH_SHORT).show()
            }
        }
        )

    }

    private fun sports()
    {
        val call : Call<NewsDataModel> = ApiClient.getClient.getEntertainment("fd26e52a9dad4b38b037052d2b623a8f","fox-sports")
        call.enqueue(object : Callback<NewsDataModel>
        {

            override fun onResponse(call: Call<NewsDataModel>?, response: Response<NewsDataModel>?)
            {
                //Toast.makeText(this@MainActivity,response!!.body()!!.articles[0].title,Toast.LENGTH_SHORT).show()
                newsArticles.clear()
                newsArticles.addAll(response!!.body()!!.articles)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<NewsDataModel>?, t: Throwable?)
            {
                Toast.makeText(this@MainActivity,"FAILED TO LOAD",Toast.LENGTH_SHORT).show()
            }
        }
        )

    }

    private fun world()
    {
        val call : Call<NewsDataModel> = ApiClient.getClient.getEntertainment("fd26e52a9dad4b38b037052d2b623a8f","the-globe-and-mail")
        call.enqueue(object : Callback<NewsDataModel>
        {

            override fun onResponse(call: Call<NewsDataModel>?, response: Response<NewsDataModel>?)
            {
                //Toast.makeText(this@MainActivity,response!!.body()!!.articles[0].title,Toast.LENGTH_SHORT).show()
                newsArticles.clear()
                newsArticles.addAll(response!!.body()!!.articles)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<NewsDataModel>?, t: Throwable?)
            {
                Toast.makeText(this@MainActivity,"FAILED TO LOAD",Toast.LENGTH_SHORT).show()
            }
        }
        )

    }

    private fun business()
    {
        val call : Call<NewsDataModel> = ApiClient.getClient.getOpinion("fd26e52a9dad4b38b037052d2b623a8f","business")
        call.enqueue(object : Callback<NewsDataModel>
        {

            override fun onResponse(call: Call<NewsDataModel>?, response: Response<NewsDataModel>?)
            {
                //Toast.makeText(this@MainActivity,response!!.body()!!.articles[0].title,Toast.LENGTH_SHORT).show()
                newsArticles.clear()
                newsArticles.addAll(response!!.body()!!.articles)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<NewsDataModel>?, t: Throwable?)
            {
                Toast.makeText(this@MainActivity,"FAILED TO LOAD",Toast.LENGTH_SHORT).show()
            }
        }
        )

    }

    private fun magazine()
    {
        val call : Call<NewsDataModel> = ApiClient.getClient.getEntertainment("fd26e52a9dad4b38b037052d2b623a8f","the-new-york-times")
        call.enqueue(object : Callback<NewsDataModel>
        {

            override fun onResponse(call: Call<NewsDataModel>?, response: Response<NewsDataModel>?)
            {
                //Toast.makeText(this@MainActivity,response!!.body()!!.articles[0].title,Toast.LENGTH_SHORT).show()
                newsArticles.clear()
                newsArticles.addAll(response!!.body()!!.articles)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<NewsDataModel>?, t: Throwable?)
            {
                Toast.makeText(this@MainActivity,"FAILED TO LOAD",Toast.LENGTH_SHORT).show()
            }
        }
        )

    }



}
