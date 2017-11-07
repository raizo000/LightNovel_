package com.example.binhnguyen.textmanga.Layout

import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.Gravity
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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        background = ContextCompat.getDrawable(context, R.drawable.card_shadows)
                    }
                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        imageView {
                            id = R.id.HinhTruyen
                            scaleType = ImageView.ScaleType.FIT_XY
                        }.lparams(width = dip(100), height = dip(150)) {
                            leftMargin=dip(16)
                            topMargin=dip(4)
                        }
                        textView {
                            id = R.id.tenTruyen
                            typeface = Typeface.DEFAULT_BOLD
                            maxLines = 2
                            ellipsize = TextUtils.TruncateAt.END
                        }.lparams {
                            leftMargin = dip(3)
                        }
                        textView {
                            id = R.id.tenChap
                            maxLines = 2
                            ellipsize = TextUtils.TruncateAt.END
                        }.lparams {
                            leftMargin = dip(3)
                        }
                    }
                }.lparams(width = dip(130), height = dip(225))
            }
        }
    }

}