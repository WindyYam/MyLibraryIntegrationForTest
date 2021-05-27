package com.example.mylibrarytest

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog

class AppIntegrationOneLineCallbackHelper : ActivityLifecycleCallbacks {
    val hash_list = HashMap<Activity, android.view.View>();
    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity) {
        if(!hash_list.containsKey(activity)) {
            val fabview = MyWrappedLayoutFABView(activity);
            val param = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            );
            activity.addContentView(
                fabview, param
            );
            hash_list.set(activity, fabview);
        }
    }

    override fun onActivityResumed(p0: Activity) {
    }

    override fun onActivityPaused(p0: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        if(hash_list.containsKey(activity)) {
            hash_list.remove(activity);
        }
    }
}