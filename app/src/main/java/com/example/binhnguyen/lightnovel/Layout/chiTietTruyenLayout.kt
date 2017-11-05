package com.example.binhnguyen.lightnovel.Layout

import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.binhnguyen.lightnovel.Activity.chiTietTruyenActivity
import com.example.binhnguyen.lightnovel.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.nestedScrollView

/**
 * Created by Binh Nguyen on 10/28/2017.
 */
class chiTietTruyenLayout : AnkoComponent<chiTietTruyenActivity> {
    override fun createView(ui: AnkoContext<chiTietTruyenActivity>): View {
        return with(ui) {
            verticalLayout {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    background = ContextCompat.getDrawable(context, R.drawable.shadown)
                }

                lparams(width = matchParent, height = matchParent) {
                    topMargin = dip(6)
                }
                nestedScrollView {

                    verticalLayout {
                        view {
                            backgroundColor = ContextCompat.getColor(context, R.color.gray)
                        }.lparams(width = matchParent, height = dip(2))
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            // hình truyện
                            imageView {
                                id = R.id.hinhTruyenChiTiet
                                scaleType = ImageView.ScaleType.FIT_END
                            }.lparams(width = dip(90), height = dip(120), weight = 1f){
                                leftMargin=dip(10)
                            }
                            // thông tin truyện
                            verticalLayout {
                                textView {
                                    textResource = R.string.tenTruyenChiTiet
                                    maxLines = 1
                                    typeface = Typeface.DEFAULT_BOLD
                                    textSize = 20f
                                    ellipsize = TextUtils.TruncateAt.END
                                    id = R.id.tenTruyenChiTiet
                                }.lparams {
                                    bottomMargin = dip(5)
                                }
                                textView {
                                    textResource = R.string.tacGiaTruyenChiTiet
                                    maxLines = 1
                                    typeface = Typeface.DEFAULT_BOLD
                                    ellipsize = TextUtils.TruncateAt.END
                                    id = R.id.tacGiaChiTietTruyen
                                }.lparams {
                                    bottomMargin = dip(5)
                                }

                                textView {
                                    textResource = R.string.theLoaiTruyenChiTiet
                                    maxLines = 1
                                    typeface = Typeface.DEFAULT_BOLD
                                    ellipsize = TextUtils.TruncateAt.END
                                    id = R.id.theLoaiTruyenChiTiet
                                }.lparams {
                                    bottomMargin = dip(5)
                                }
                                textView {
                                    textResource = R.string.tinhTrangTruyenChiTiet
                                    ellipsize = TextUtils.TruncateAt.END
                                    maxLines = 1
                                    typeface = Typeface.DEFAULT_BOLD
                                    id = R.id.tinhTrangChiTietTruyen
                                }.lparams {
                                    bottomMargin = dip(5)
                                }
                                textView {
                                    textResource = R.string.nguonTruyenChiTiet
                                    maxLines = 1
                                    typeface = Typeface.DEFAULT_BOLD
                                    ellipsize = TextUtils.TruncateAt.END
                                    id = R.id.nguonTruyenChiTiet
                                }.lparams {
                                    bottomMargin = dip(5)
                                }
                            }.lparams(weight = 3f, width = dip(250)) {
                                leftMargin = dip(7)
                            }
                        }
                        //
                        // thanh ngang
                        view {
                            backgroundColor = ContextCompat.getColor(context, R.color.gray)
                        }.lparams(width = matchParent, height = dip(2))
                        //
                        // Mô tả truyện
                        verticalLayout {

                            textView {
                                textSize = dip(5).toFloat()
                                text = "Mô tả:"
                                typeface = Typeface.DEFAULT_BOLD

                            }
                            linearLayout {
                                textView {
                                    id = R.id.moTaTruyenChiTiet
                                    maxLines = 4
                                    ellipsize = TextUtils.TruncateAt.END
                                }.lparams(width = matchParent, height = wrapContent) {

                                }
                            }.lparams(width = matchParent, height = dip(70)) {
                                leftMargin = dip(10)
                            }
                        }.lparams {
                            leftMargin = dip(10)
                        }
                        //
                        // thanh ngang
                        view {
                            backgroundColor = ContextCompat.getColor(context, R.color.gray)
                        }.lparams(width = matchParent, height = dip(2))

                        //
                        // Danh sách chapter
                        verticalLayout {
                            textView {
                                text = "Danh sách chapter"
                                textSize = sp(5).toFloat()
                                typeface = Typeface.DEFAULT_BOLD
                            }
                            recyclerView {
                                id = R.id.danhSachChapter
                                layoutManager = LinearLayoutManager(context)
                            }.lparams {
                                padding = dip(10)
                                leftMargin = dip(15)
                            }
                        }
                    }
                }
            }
        }
    }
}