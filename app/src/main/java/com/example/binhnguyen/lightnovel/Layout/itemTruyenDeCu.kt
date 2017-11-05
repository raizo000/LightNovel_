package com.example.binhnguyen.textmanga.Layout

import android.graphics.Typeface
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.binhnguyen.lightnovel.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Created by Binh Nguyen on 10/26/2017.
 */
class itemTruyenDeCu : AnkoComponent<ViewGroup?> {
    override fun createView(ui: AnkoContext<ViewGroup?>): View {
        return with(ui) {

            frameLayout {
                lparams(width = wrapContent, height = wrapContent) {
                    margin = dip(5)
                }
                cardView {
                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        imageView {
                            id = R.id.HinhTruyen
                            scaleType = ImageView.ScaleType.FIT_XY

                        }.lparams(width = dip(100), height = dip(150))
                        textView {
                            id = R.id.tenTruyen
                            typeface = Typeface.DEFAULT_BOLD
                            maxLines = 2
                            ellipsize = TextUtils.TruncateAt.END
                        }
                        textView {
                            id = R.id.tenChap
                        }
                    }
                }.lparams(width = dip(120), height = dip(220))
            }
        }
    }

}