package com.example.newsapi.ActivitiesAndFragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapi.R
import com.example.newsapp.SearchScreen
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.news_item_full_article_1.*


class FullArticle :AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.news_item_full_article_1)

        fullArticle_title.text = intent.getStringExtra("title")
        fullArticle_publisherName.text=intent.getStringExtra("source")
        fullArticle_updateStatus.text=intent.getStringExtra("publishDate")
        fullArticle_description.text=intent.getStringExtra("content")

        val options = RequestOptions()
            .centerCrop()
            .apply(RequestOptions.fitCenterTransform())
        val imgUrl = intent.getStringExtra("image")

        Glide.with(this!!).load(imgUrl).apply(options).into(fullArticle_topImage!!)


        action_bar_search_button.setOnClickListener()
        {
            val transaction =  supportFragmentManager.beginTransaction()
            val fragment = SearchScreen()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        action_bar_back_button.setOnClickListener()
        {
            onBackPressed()
        }
    }
}
