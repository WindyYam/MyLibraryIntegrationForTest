package com.example.mylibrarytest

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.example.myassessmentlibrary.BuildConfig
import com.example.myassessmentlibrary.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class MyWrappedLayoutFABView(context: Context) : FrameLayout(context), View.OnClickListener{
    private val fabview : View;
    private val fabbutton : FloatingActionButton;
    private val inflater = LayoutInflater.from(context);
    private val infoview : View;
    private val infoarea : View;
    private val backarea : View;
    private var installinfo : TextView;
    private var currentinfo : TextView;
    private val START_UPDATE = 0;
    private val mhandler : Handler;
    init{
        fabview = inflater.inflate(R.layout.fab_layout, this, false);
        addView(fabview);
        infoview = inflater.inflate(R.layout.info_layout, this, false);
        addView(infoview);
        infoview.visibility = View.GONE;
        fabbutton = findViewById(R.id.fab_Btn);
        fabbutton.setOnClickListener(this);
        infoarea = findViewById(R.id.info_show)
        infoarea.setOnClickListener(this);
        backarea = findViewById(R.id.back_dim);
        backarea.setOnClickListener(this);
        installinfo = findViewById(R.id.install_info);
        val buildDate = Date(BuildConfig.TIMESTAMP);
        val dateformat = SimpleDateFormat("dd MMM yyyy");

        installinfo.text = "" + resources.getText(R.string.install_desc) + " " + dateformat.format(buildDate);
        currentinfo = findViewById(R.id.current_info);

        mhandler = object:Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when(msg.what){
                    START_UPDATE -> {
                        val simpleDateFormat = SimpleDateFormat("HH:mm:ss yyyy MMM dd")
                        val currentDateAndTime: String = simpleDateFormat.format(Date())
                        currentinfo.text = currentDateAndTime;
                        sendEmptyMessageDelayed(START_UPDATE,1000);}
                }
            }
        }
    }



    override fun onClick(v: View?) {
        when(v?.id){
            fabbutton.id -> {
                infoview.visibility = View.VISIBLE;
                fabview.visibility = View.GONE;
                startUpdating();
            }
            backarea.id -> {
                infoview.visibility = View.GONE;
                fabview.visibility = View.VISIBLE;
            }
        }
    }

    fun startUpdating(){
        mhandler.sendEmptyMessage(START_UPDATE);
    }

    fun stopUpdating(){
        mhandler.removeMessages(START_UPDATE);
    }
}