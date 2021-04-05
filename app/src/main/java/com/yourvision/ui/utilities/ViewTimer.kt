package com.yourvision.ui.utilities

import android.os.Handler
import android.view.View
import android.widget.TextView
import java.util.concurrent.Future

class ViewTimer {
    private var time = 0
    private var view: View? = null
    private var currentTime = 0
    private var isStopped = false

    private fun init(time: Int, view: View? = null) {
        this.time = time
        this.view = view
        currentTime = 0
    }

    private fun start(onStop: () -> Unit) {
        isStopped = false
        currentTime = 0
        val handler = Handler()
        val timer = object : Runnable {
            override fun run() {
                if (time in currentTime..3540 && !isStopped) {
                    val minutes = if (currentTime / 60 < 10) {
                        "0${currentTime / 60}"
                    } else {
                        "${currentTime / 60}"
                    }
                    val seconds = if (currentTime % 60 < 10) {
                        "0${currentTime % 60}"
                    } else {
                        "${currentTime % 60}"
                    }
                    (view as? TextView)?.text = "${minutes}:${seconds}"
                    currentTime++
                    handler.postDelayed(this, 1000)
                } else {
                    if (!isStopped) {
                        onStop()
                    }
                }
            }
        }
        handler.post(timer)
    }

    fun initAndStart(time: Int, view: View? = null, onStop: () -> Unit) {
        init(time, view)
        start(onStop)
    }

    fun stop() {
        isStopped = true
    }
}