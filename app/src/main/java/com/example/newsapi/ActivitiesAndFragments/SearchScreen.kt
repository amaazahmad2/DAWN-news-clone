package com.example.newsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.action_bar.view.*
import kotlinx.android.synthetic.main.activity_main.*
import android.R



class SearchScreen  : Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(com.example.newsapi.R.layout.fragment_search_screen,fragment_container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        action_bar_back_button.isEnabled=true
        action_bar_back_button.visibility=View.VISIBLE
        action_bar_search_button.isEnabled=false
        action_bar_search_button.visibility=View.INVISIBLE
        action_bar_title.text="SEARCH"

        action_bar_back_button.setOnClickListener()
        {
            fragmentManager!!.popBackStack()
        }

    }




}
