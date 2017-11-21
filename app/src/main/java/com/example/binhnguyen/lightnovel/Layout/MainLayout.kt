package com.example.binhnguyen.textmanga.Layout

import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.example.binhnguyen.lightnovel.Activity.MainActivity
import com.example.binhnguyen.lightnovel.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.nestedScrollView

/**
 * Created by Binh Nguyen on 10/25/2017.
 */
class MainLayout : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>): View {

        return with(ui) {
            linearLayout {
                orientation = LinearLayout.VERTICAL
                isFocusableInTouchMode = true

                lparams(width = matchParent, height = matchParent) {}

                // thanh tìm kiếm
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    editText {
                        id = R.id.search
                        hint = "Tìm kiếm"
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            background = ContextCompat.getDrawable(context, R.drawable.shadown)
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            elevation = 2f
                        }
                        compoundDrawablePadding = dip(5)
                        setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, R.drawable.seach), null, null, null)
                    }.lparams(width = matchParent, height = wrapContent, weight = 1f)
                    imageView {
                        id = R.id.theLoai
                        imageResource = R.drawable.ic_reorder_black_24dp
                    }.lparams(width = wrapContent, height = matchParent)
                }
                //
                nestedScrollView {
                    lparams(width = matchParent, height = matchParent)
                    frameLayout {
                        lparams(width = matchParent, height = matchParent)
                        id=R.id.khungNoiDung

                        verticalLayout {

                            recyclerView {
                                id = R.id.timKiemRecycler
                                layoutManager = GridLayoutManager(context, 3)
                                visibility = View.GONE
                            }.lparams(width = matchParent, height = matchParent)
                            linearLayout {
                                id = R.id.noiDung
                                lparams(width = matchParent, height = matchParent)
                                orientation = LinearLayout.VERTICAL
                                textView {
                                    text = "Truyện đề cử  "
                                    textSize = sp(9).toFloat()
                                    typeface = Typeface.createFromAsset(context.assets, "Roboto-Bold.ttf")
                                    padding = dip(2)
                                }.lparams(width = matchParent) {
                                    leftMargin = dip(5)

                                }
                                recyclerView {
                                    id = R.id.recyclTruyenDeCu
                                    isNestedScrollingEnabled = false
                                    layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
                                }
                                textView {
                                    text = "Truyện mới cập nhật "
                                    textSize = sp(9).toFloat()
                                    typeface = Typeface.createFromAsset(context.assets, "Roboto-Bold.ttf")
                                    padding = dip(2)

                                }.lparams() {
                                    leftMargin = dip(5)
                                }
                                recyclerView {
                                    id = R.id.recyclTruyenCapNhat
                                    isNestedScrollingEnabled = false
                                    layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)

                                }

                                textView {
                                    text = "Truyện Full hay nhất "
                                    textSize = sp(9).toFloat()
                                    typeface = Typeface.createFromAsset(context.assets, "Roboto-Bold.ttf")
                                    padding = dip(2)

                                }.lparams() {
                                    leftMargin = dip(5)
                                }
                                recyclerView {
                                    id = R.id.recyclTruyenFullHayNhat
                                    isNestedScrollingEnabled = false
                                    layoutManager = GridLayoutManager(context, 3)

                                }
                            }
                        }

                    }
                }
            }
        }
    }

}