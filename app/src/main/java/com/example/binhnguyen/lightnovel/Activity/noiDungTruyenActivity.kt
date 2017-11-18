package com.example.binhnguyen.lightnovel.Activity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.binhnguyen.lightnovel.Layout.noiDungTruyenLayout
import com.example.binhnguyen.lightnovel.R
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup

class noiDungTruyenActivity : AppCompatActivity(), View.OnClickListener {
    var noiDungTruyen: TextView? = null
    var toolBarText: TextView? = null
    var prevButton: Button? = null
    var nextButton: Button? = null
    var preChapter: String? = null
    var nextChapter: String? = null
    var linkNoiDungTruyen: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noiDungTruyenLayout().setContentView(this)

        linkNoiDungTruyen = intent.getStringExtra("linkNoiDungTruyen")
        getNoiDungTruyen(this, linkNoiDungTruyen)

        noiDungTruyen = find(R.id.NoiDungTruyen)
        toolBarText = find(R.id.toolBarText)
        prevButton = find(R.id.PreButton)
        nextButton = find(R.id.NextButton)

        prevButton?.setOnClickListener(this)
        nextButton?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            prevButton -> {
                linkNoiDungTruyen = preChapter
                getNoiDungTruyen(this, preChapter)
            }
            nextButton -> {
                linkNoiDungTruyen = nextChapter
                getNoiDungTruyen(this, nextChapter)
            }
        }
    }

    private fun getNoiDungTruyen(context: Context, linkChapterTruyen: String?) {
        doAsync {
            val document = Jsoup.connect(linkChapterTruyen).get()
            val tenChapter = document.select("div[class=w3-row w3-center chapter-header] ul[class=w3-ul] li h3  ").text()
            Log.d("tên chapter", "$tenChapter")
            val noiDung = document.select("div[class=w3-justify chapter-content detailcontent]")

            preChapter = document.select("div[class=w3-center chapter-button] a[id=prevchap]").attr("href")
            nextChapter = document.select("div[class=w3-center chapter-button] a[id=nextchap]").attr("href")
            Log.d("pre chapter", "$preChapter")
            Log.d("next chapter", "$nextChapter")
            uiThread {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    noiDungTruyen?.text = Html.fromHtml(noiDung.html(), 0)
                }
                toolBarText?.text = tenChapter
            }
        }

    }

}
