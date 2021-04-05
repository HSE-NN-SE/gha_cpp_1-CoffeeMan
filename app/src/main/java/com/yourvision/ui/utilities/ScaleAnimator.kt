package com.yourvision.ui.utilities

import android.view.View
import android.os.Handler
import android.view.ViewGroup
import kotlin.math.abs
import kotlin.math.roundToInt

class ScaleAnimator {
    private var view: View? = null
    private var widthTo: Int = 0
    private var heightTo: Int = 0
    private var isStopped: Boolean = false

    private fun init(view: View?, widthTo: Int, heightTo: Int) {
        this.view = view
        this.widthTo = widthTo
        this.heightTo = heightTo
    }

    private fun start(speed: Double, onStop: () -> Unit = {}) {
        isStopped = false

        var params = view?.layoutParams
        var width = getWidth(params)
        var height = getHeight(params)

        val spd = speed.coerceIn(0.0..1.0)
        val widthStep = abs(widthTo - width) * spd
        val heightStep = abs(heightTo - height) * spd

        val widthIncrease = width < widthTo
        val heightIncrease = height < heightTo

        val handler = Handler()
        lateinit var anim: Runnable
        anim = Runnable {
            params = view?.layoutParams
            width = getWidth(params)
            height = getHeight(params)

            if (!isStopped) {
                width = changeScale(width, widthTo, widthStep, widthIncrease)
                height = changeScale(height, heightTo, heightStep, heightIncrease)

                params?.height = height
                params?.width = width
                view?.layoutParams = params

                if (widthTo == width && heightTo == height) {
                    onStop()
                } else {
                    handler.post(anim)
                }
            }
        }
        handler.post(anim)
    }

    fun initAndStart(view: View?,
                     widthTo: Int,
                     heightTo: Int,
                     speed: Double,
                     onStop: () -> Unit = {}) {
        if (speed > 0) {
            init(view, widthTo, heightTo)
            start(speed, onStop)
        }
    }

    private fun changeScale(currentScale: Int,
                            scaleTo: Int,
                            step: Double,
                            increase: Boolean) : Int {
        val comparator: Int
        val st = if (increase) {
            comparator = scaleTo - currentScale
            step
        } else {
            comparator = currentScale - scaleTo
            -step
        }

        return if (comparator > 0) {
            (currentScale + st).roundToInt()
        } else {
            scaleTo
        }
    }


    private fun getWidth(params: ViewGroup.LayoutParams?): Int {
        return when (val width = params?.width ?: 0) {
            -1 -> widthTo
            else -> width
        }
    }

    private fun getHeight(params: ViewGroup.LayoutParams?): Int {
        return when (val height = params?.height ?: 0) {
            -1 -> heightTo
            else -> height
        }
    }

    fun stop() {
        isStopped = true
    }
}