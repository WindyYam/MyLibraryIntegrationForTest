package com.example.mylibrarytest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.mylibrarytest.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyFABView(context: Context) : FrameLayout(context){
    private val fabview : View
    private val fabbutton : FloatingActionButton
    private val inflater = LayoutInflater.from(context)

    init{
        fabview = inflater.inflate(R.layout.fab_layout, this, true)
        fabbutton = findViewById(R.id.fab_Btn)
    }

    fun getFABButton() : FloatingActionButton {
        return fabbutton
    }
}