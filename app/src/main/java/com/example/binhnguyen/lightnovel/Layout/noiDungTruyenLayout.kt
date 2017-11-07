package com.example.binhnguyen.lightnovel.Layout

import android.graphics.Typeface
import android.os.Build
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
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            textView {
                                id = R.id.NoiDungTruyen
                                typeface= Typeface.createFromAsset(context.assets,"Roboto-Regular.ttf")
                            }.lparams(width = matchParent, height = matchParent) {
                                padding = dip(10)
                            }
                        }
                    }
                }
            }
        }
    }
}