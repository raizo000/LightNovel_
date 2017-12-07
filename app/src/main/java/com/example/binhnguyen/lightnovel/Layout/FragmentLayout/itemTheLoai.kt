package com.example.binhnguyen.lightnovel.Layout.FragmentLayout

import android.graphics.Typeface
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.example.binhnguyen.lightnovel.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Created by jenov on 11/21/2017.
 */
class itemTheLoai : AnkoComponent<ViewGroup?> {
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun createView(ui: AnkoContext<ViewGroup?>): View {
        return with(ui) {
            frameLayout {
                lparams(width = dip(100), height = dip(40)) {
                    margin = dip(4)
                }
                cardView {
                    textView {
                        typeface = Typeface.createFromAsset(context.assets, "Roboto-Bold.ttf")
                        id = R.id.tenTheLoai
                        gravity = Gravity.CENTER
                    }

                }
            }
        }
    }
}