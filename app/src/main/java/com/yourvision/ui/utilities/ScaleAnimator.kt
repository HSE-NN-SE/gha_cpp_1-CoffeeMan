package com.yourvision.ui.utilities

import android.view.View
import android.os.Handler
import android.view.ViewGroup

class ScaleAnimator {
    private var view: View? = null
    private var widthTo: Int = 0
    private var heightTo: Int = 0
    private var isStopped: Boolean = false
    private var step: Int = 50

    private fun init(view: View?, widthTo: Int, heightTo: Int, step: Int = 50) {
        this.view = view
        this.widthTo = widthTo
        this.heightTo = heightTo
        this.step = step
    }

    private fun start(onStop: () -> Unit) {
        isStopped = false
        val handler = Handler()

        var params = view?.layoutParams
        var width = getWidth(params)
        var height = getHeight(params)

        val widthIncrease = width < widthTo
        val heightIncrease = height < heightTo

        lateinit var anim: Runnable
        anim = Runnable {
            var isPosting = false
            params = view?.layoutParams
            width = getWidth(params)
            height = getHeight(params)

            if (!isStopped) {
                if (widthIncrease) {
                    when {
                        width < widthTo -> {
                            width += step
                            isPosting = if (width > widthTo) {
                                width = widthTo
                                false
                            } else {
                                true
                            }
                        }
                    }
                } else {
                    when {
                        width > widthTo -> {
                            width -= step
                            isPosting = if (width < widthTo) {
                                width = widthTo
                                false
                            } else {
                                true
                            }
                        }
                    }
                }

                if (heightIncrease) {
                    when {
                        height < heightTo -> {
                            height += step
                            isPosting = if (height > heightTo) {
                                height = heightTo
                                false
                            } else {
                                true
                            }
                        }
                    }
                } else {
                    when {
                        height > heightTo -> {
                            height -= step
                            isPosting = if (height < heightTo) {
                                height = heightTo
                                false
                            } else {
                                true
                            }
                        }
                    }
                }

                params?.height = height
                params?.width = width
                view?.layoutParams = params

                if (isPosting) {
                    handler.post(anim)
                } else {
                    onStop()
                }
            }
        }
        handler.post(anim)
    }

    fun initAndStart(view: View?, widthTo: Int, heightTo: Int, step: Int = 50, onStop: () -> Unit) {
        init(view, widthTo, heightTo, step)
        start(onStop)
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