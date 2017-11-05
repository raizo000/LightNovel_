package com.example.binhnguyen.lightnovel.Layout

import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.binhnguyen.lightnovel.R
import org.jetbrains.anko.*

/**
 * Created by Binh Nguyen on 11/05/2017.
 */
class itemChapter : AnkoComponent<ViewGroup?> {

    override fun createView(ui: AnkoContext<ViewGroup?>): View {
        return with(ui) {
            frameLayout {
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    lparams(width = matchParent, height = wrapContent)
                    textView {
                        id = R.id.tenChapterChiTiet
                        textSize = dip(4).toFloat()

                    }.lparams(width = matchParent, height = wrapContent)
                }
            }
        }
    }
}