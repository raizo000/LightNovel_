package com.example.binhnguyen.lightnovel.Layout

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.example.binhnguyen.lightnovel.Activity.theLoaiActivity
import com.example.binhnguyen.lightnovel.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by jenov on 12/07/2017.
 */
class theLoaiLayout : AnkoComponent<theLoaiActivity> {
    override fun createView(ui: AnkoContext<theLoaiActivity>): View {
        return with(ui) {
            frameLayout {
                lparams(width = matchParent, height = matchParent)
                appBarLayout {
                    toolbar {
                        textView {
                            id = R.id.toolBarText
                            typeface = Typeface.createFromAsset(context.assets, "Roboto-Bold.ttf")
                            textColor = ContextCompat.getColor(context, R.color.white)
                            textSize = dip(5).toFloat()
                        }
                    }.lparams(width = matchParent, height = dip(50))
                }.lparams(width = matchParent)
                linearLayout {
                    recyclerView {
                        id = R.id.recyclerTheLoai
                        layoutManager = GridLayoutManager(context, 3)
                    }
                }.lparams {
                    topMargin=dip(40)
                }


            }
        }
    }
}