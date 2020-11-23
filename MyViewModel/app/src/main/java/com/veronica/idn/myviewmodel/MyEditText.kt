package com.veronica.idn.myviewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat

class MyEditText : AppCompatEditText {


        internal lateinit var mClearButtonImage: Drawable

    constructor(context: Context) : super(context) {
        init()

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        hint = "Masukkan nama anda"
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun init() {
        mClearButtonImage = ResourcesCompat.getDrawable(
            resources,
            R.drawable.ic_close,
            null
        ) as Drawable

        setOnTouchListener(OnTouchListener { v, event ->
            if (compoundDrawablesRelative[2] != null) {
                val clearButtonStart: Float
                val clearButtonEnd: Float
                var isClearButtonClicked = false
                when (layoutDirection) {
                    View.LAYOUT_DIRECTION_RTL -> {
                        clearButtonEnd = (mClearButtonImage.intrinsicWidth + paddingStart).toFloat()
                        when {
                            event.x < clearButtonEnd -> isClearButtonClicked = true
                        }
                    }
                    else -> {
                        clearButtonStart =
                            (width - paddingEnd - mClearButtonImage.intrinsicWidth).toFloat()
                        when {
                            event.x > clearButtonStart -> isClearButtonClicked = true
                        }
                    }
                }
                when {
                    isClearButtonClicked -> when {
                        event.action == MotionEvent.ACTION_DOWN -> {
                            mClearButtonImage = ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.ic_close,
                                null
                            ) as Drawable
                            showClearButton()
                            return@OnTouchListener true
                        }
                        event.action == MotionEvent.ACTION_UP -> {
                            mClearButtonImage = ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.ic_close,
                                null
                            ) as Drawable
                            when {
                                text != null -> text?.clear()
                            }
                            hideClearButton()
                            return@OnTouchListener true
                        }
                        else -> return@OnTouchListener false
                    }
                    else -> return@OnTouchListener false
                }
            }
            false
        })
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                when {
                    !p0.toString().isEmpty() -> showClearButton()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)
    }

    private fun showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearButtonImage, null)
    }

}