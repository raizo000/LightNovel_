package com.example.binhnguyen.lightnovel.Layout

import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.example.binhnguyen.lightnovel.Activity.noiDungTruyenActivity
import com.example.binhnguyen.lightnovel.R
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView

/**
 * Created by Binh Nguyen on 11/06/2017.
 */
class noiDungTruyenLayout : AnkoComponent<noiDungTruyenActivity> {
    override fun createView(ui: AnkoContext<noiDungTruyenActivity>): View {
        return with(ui) {
            frameLayout {

                linearLayout {
                    lparams(width = matchParent, height = matchParent)
                    nestedScrollView {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            background = ContextCompat.getDrawable(context, R.color.wheat)
                        }
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            textView {
                                id = R.id.NoiDungTruyen
                                textSize = sp(5).toFloat()
                                textColor = ContextCompat.getColor(context,R.color.primaryText)
                                typeface = Typeface.createFromAsset(context.assets, "Roboto-Regular.ttf")
                            }.lparams(width = matchParent, height = matchParent) {
                                padding = dip(10)
                            }
                        }
                    }
                }
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL

                    backgroundColor = ContextCompat.getColor(context, R.color.transparent)
                    button {
                        text = "<"
                        textColor = ContextCompat.getColor(context,R.color.white)
                        typeface = Typeface.createFromAsset(context.assets, "Roboto-Bold.ttf")
                        id = R.id.PreButton
                        backgroundColor = ContextCompat.getColor(context,R.color.primaryColor)
                    }.lparams(width = dip(50), height = matchParent)
                    textView {
                        id = R.id.txtTenChap
                        textColor = ContextCompat.getColor(context,R.color.white)
                        typeface = Typeface.createFromAsset(context.assets, "Roboto-Bold.ttf")
                        gravity = Gravity.CENTER
                        backgroundColor = ContextCompat.getColor(context,R.color.primaryColor)
                    }.lparams(width = wrapContent,height = matchParent,weight = 1f)
                    button {
                        id = R.id.NextButton
                        text = ">"
                        textColor = ContextCompat.getColor(context,R.color.white)
                        typeface = Typeface.createFromAsset(context.assets, "Roboto-Bold.ttf")
                        backgroundColor = ContextCompat.getColor(context,R.color.primaryColor)
                    }.lparams(width = dip(50), height = matchParent)
                }.lparams(width = matchParent, height = dip(40)) {
                    gravity = Gravity.BOTTOM
                }
            }
        }
    }
}