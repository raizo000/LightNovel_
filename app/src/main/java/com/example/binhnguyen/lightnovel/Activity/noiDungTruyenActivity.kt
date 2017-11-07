package com.example.binhnguyen.lightnovel.Activity

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.util.Log
import android.widget.TextView
import com.example.binhnguyen.lightnovel.Layout.noiDungTruyenLayout
import com.example.binhnguyen.lightnovel.R
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup

class noiDungTruyenActivity : AppCompatActivity() {
    var noiDungTruyen: TextView? = null
    var toolBarText: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noiDungTruyenLayout().setContentView(this)

        val linkNoiDungTruyen = intent.getStringExtra("linkNoiDungTruyen")
        getNoiDungTruyen(linkNoiDungTruyen)

        noiDungTruyen = find(R.id.NoiDungTruyen)
        toolBarText = find(R.id.toolBarText)
    }

    private fun getNoiDungTruyen(linkChapterTruyen: String) {
        doAsync {
            val document = Jsoup.connect(linkChapterTruyen).get()
            val textToolbar = document.select("div[class=w3-row w3-center chapter-header] ul[class=w3-ul] li h1 a ").attr("tittle")
            val tenChapter = document.select("div[class=w3-row w3-center chapter-header] ul[class=w3-ul] li h3  ").text()
            Log.d("tên chapter", "$tenChapter")
            val noiDung = document.select("div[class=w3-justify chapter-content detailcontent]")
            uiThread {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    noiDungTruyen?.text = Html.fromHtml(noiDung.html(), 0)
                }
                toolBarText?.text = tenChapter
            }
        }

    }
}
