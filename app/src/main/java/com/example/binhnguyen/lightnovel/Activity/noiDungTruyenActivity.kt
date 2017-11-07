package com.example.binhnguyen.lightnovel.Activity

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.webkit.WebView
import android.widget.TextView
import com.example.binhnguyen.lightnovel.Layout.noiDungTruyenLayout
import com.example.binhnguyen.lightnovel.R
import org.jetbrains.anko.*
import org.jsoup.Jsoup

class noiDungTruyenActivity : AppCompatActivity() {
    var noiDungTruyen: TextView? = null
    var noiDungTruyenCHiTiet:_WebView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noiDungTruyenLayout().setContentView(this)

        val linkNoiDungTruyen = intent.getStringExtra("linkNoiDungTruyen")
        getNoiDungTruyen(linkNoiDungTruyen)

        noiDungTruyen=find(R.id.NoiDungTruyen)

    }

    private fun getNoiDungTruyen(linkChapterTruyen: String) {
        doAsync {
            val document = Jsoup.connect(linkChapterTruyen).get()
            val noiDung = document.select("div[class=w3-justify chapter-content detailcontent]")
            uiThread {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    noiDungTruyen?.text= Html.fromHtml(noiDung.html(), 0)


                }
            }
        }

    }
}
