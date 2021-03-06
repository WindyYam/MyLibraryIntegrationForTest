package com.example.mylibrarytest

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mylibrarytest.BuildConfig
import com.example.mylibrarytest.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class MyWrappedLayoutFABView(context: Context) : FrameLayout(context), View.OnClickListener {
    private val fabview: MyFABView;
    private val fabbutton: FloatingActionButton;
    private val inflater = LayoutInflater.from(context);
    private val infoview: View;
    private val infoarea: View;
    private val backarea: View;
    private val imageview: ImageView;
    private var installinfo: TextView;
    private var currentinfo: TextView;
    private val START_UPDATE = 0;
    private val mhandler: Handler;

    init {
        fabview = MyFABView(context);
        addView(fabview);
        infoview = inflater.inflate(R.layout.info_layout, this, false);
        addView(infoview);

        initViewVisibility();

        //init views
        fabbutton = fabview.getFABButton();
        fabbutton.setOnClickListener(this);
        infoarea = findViewById(R.id.info_show)
        infoarea.setOnClickListener(this);
        backarea = findViewById(R.id.back_dim);
        backarea.setOnClickListener(this);
        installinfo = findViewById(R.id.install_info);

        //get build data
        val buildDate = Utils.getDateFromLong(BuildConfig.TIMESTAMP);
        installinfo.text = "" + resources.getText(R.string.install_desc) + " " + buildDate;
        currentinfo = findViewById(R.id.current_info);

        //glide
        imageview = findViewById(R.id.imageView);
        glideImage(imageview);

        //set up handler
        mhandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when (msg.what) {
                    START_UPDATE -> {
                        val datestr = Utils.getDateTimeFromDate(Date());
                        currentinfo.text = "" + context.getText(R.string.current_time) + datestr;
                        sendEmptyMessageDelayed(START_UPDATE, 1000);
                    }
                }
            }
        }

        //open_anim.setAnimationListener()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            fabbutton.id -> {
                Log.d("test", "Fab area touch");
                setViewsClickable(false);
                infoview.animate()
                    .alpha(1f)
                    .setDuration(300)
                    .withStartAction { infoview.visibility = View.VISIBLE; };
                fabview.animate()
                    .alpha(0f)
                    .setDuration(300)
                    .withEndAction { fabview.visibility = View.GONE; setViewsClickable(true); };
                startUpdating();
            }
            backarea.id -> {
                Log.d("test", "Back area touch");
                setViewsClickable(false);
                infoview.animate()
                    .alpha(0f)
                    .setDuration(300)
                    .withEndAction { infoview.visibility = View.GONE; setViewsClickable(true); };
                fabview.animate()
                    .alpha(1f)
                    .setDuration(300)
                    .withStartAction { fabview.visibility = View.VISIBLE; };
                stopUpdating();
            }
        }
    }


    private fun initViewVisibility() {
        fabview.alpha = 1.0f;
        infoview.alpha = 0.0f;
        infoview.visibility = View.GONE;
    }

    private fun glideImage(imageView: ImageView) {
        Glide.with(context)
            .load("https://www.gnu.org/graphics/gnu-head.jpg")
            .override(512, 512)
            .into(imageview);
    }

    private fun setViewsClickable(able: Boolean) {
        fabbutton.isClickable = able;
        backarea.isClickable = able;
    }

    private fun startUpdating() {
        mhandler.sendEmptyMessage(START_UPDATE);
    }

    private fun stopUpdating() {
        mhandler.removeMessages(START_UPDATE);
    }
}