package com.example.binhnguyen.lightnovel.Layout

import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.LinearLayout
import com.example.binhnguyen.lightnovel.Activity.noiDungTruyenActivity
import com.example.binhnguyen.lightnovel.R
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.support.v4.nestedScrollView

/**
 * Created by Binh Nguyen on 11/06/2017.
 */
class noiDungTruyenLayout : AnkoComponent<noiDungTruyenActivity> {
    override fun createView(ui: AnkoContext<noiDungTruyenActivity>): View {
        return with(ui) {
            coordinatorLayout {
                appBarLayout {
                    toolbar {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            background = ContextCompat.getDrawable(context, R.color.colorPrimary)
                        }
                        textView {
                            id = R.id.toolBarText
                            typeface = Typeface.createFromAsset(context.assets, "Roboto-Bold.ttf")
                            textColor = resources.getColor(R.color.white)

                        }.lparams(width = matchParent) {
                            topMargin = dip(10)
                        }

                    }
                }.lparams(width = matchParent) {
                }

                linearLayout {
                    lparams(width = matchParent, height = matchParent)


                    nestedScrollView {
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            textView {
                                id = R.id.NoiDungTruyen
                                textSize=sp(6).toFloat()
                                textColor=resources.getColor(R.color.black)
                                typeface = Typeface.createFromAsset(context.assets, "Roboto-Regular.ttf")
                            }.lparams(width = matchParent, height = matchParent) {
                                padding = dip(10)
                                topMargin = dip(50)
                            }
                        }
                    }
                }
            }
        }
    }
}