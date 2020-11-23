package com.veronica.idn.myviewmodel

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class MyButton : AppCompatButton {
    private var enabledBackground: Drawable? = null
    private var disabledBackgroud: Drawable? = null
    private var txtColor: Int = 0

    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {
        txtColor = ContextCompat.getColor(context, android.R.color.background_light)
        enabledBackground = ResourcesCompat.getDrawable(resources, R.drawable.bg_button, null)
        disabledBackgroud =
            ResourcesCompat.getDrawable(resources, R.drawable.bg_button_disable, null)
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

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        background = when {
            isEnabled -> enabledBackground
            else -> disabledBackgroud
        }
        setTextColor(txtColor)
        textSize = 12f
        gravity = Gravity.CENTER
        text = when {
            isEnabled -> "Submit"
            else -> "Isi Dulu"
        }

    }
}