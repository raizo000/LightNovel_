package com.example.binhnguyen.lightnovel.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.example.binhnguyen.lightnovel.Layout.theLoaiLayout
import com.example.binhnguyen.lightnovel.R
import com.example.binhnguyen.textmanga.Adapter.AdapterTruyenFullHayNhat
import com.example.binhnguyen.textmanga.Model.ChapterModel
import com.example.binhnguyen.textmanga.Model.TruyenModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup

class theLoaiActivity : AppCompatActivity() {
    var listChapter: MutableList<ChapterModel> = mutableListOf()
    var listTruyen: MutableList<TruyenModel> = mutableListOf()
    var adapterTheLoai: AdapterTruyenFullHayNhat? = null
    var recyclerView: RecyclerView? = null
    var toolBarText: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        theLoaiLayout().setContentView(this)
        recyclerView = find(R.id.recyclerTheLoai)
        toolBarText = find(R.id.toolBarText)
        val link = intent.getStringExtra("linkTheLoai")
        getDanhSachTruyen(link)
        adapterTheLoai = AdapterTruyenFullHayNhat(false, this, listTruyen)
        recyclerView?.adapter = adapterTheLoai
    }

    private fun getDanhSachTruyen(link: String) {
        doAsync {
            val document = Jsoup.connect(link).get()
            val theLoai = document.select("div[class=title-left] h1 a").attr("title")
            val elementListTheLoai = document.select("div[id=main] div[class=list-update] div[class=w3-row list-content]  div[class=w3-row list-row-img]")
            for (item in elementListTheLoai) {

                val tenTruyen = item.select("div[class=w3-col s2 m2 l2 row-image] div  a").attr("title")

                val tenChap = item.select("div[class=w3-col s3 m3 l3 row-number] span[class=row-time]").text()
                val linkTruyen = item.select("div[class=w3-col s2 m2 l2 row-image] div  a").attr("href")
                val linkHinh = item.select("div[class=w3-col s2 m2 l2 row-image] img").attr("src")
                val linkChap = item.select("div[class=w3-col s7 m7 l7 row-info]").attr("href")
                val chapterModel = ChapterModel(tenChap, linkChap)
                listChapter.add(chapterModel)
                val truyenModel = TruyenModel(tenTruyen, linkTruyen, listChapter, linkHinh)
                listTruyen.add(truyenModel)
            }
            uiThread {
                toolBarText?.text = theLoai
                adapterTheLoai?.notifyDataSetChanged()
            }
        }
    }


    private fun getDuLieuTruyenFull(link: String) {
        val linkPage = link
        doAsync {
            val document = Jsoup.connect(link).get()
            val maxPageLink = document.select("div[class=w3-center pagination] ul[class=w3-pagination paging] li a[class=last]").attr("href")
            val maxPage = maxPageLink.substring(maxPageLink.length - 4, maxPageLink.length - 1).toInt()
            for (value in 1..maxPage) {
                getItem("$linkPage$value/")
            }
        }
    }

    private fun getItem(link: String) {
        val document = Jsoup.connect(link).get()
        val elementList = document.select("div[class=list-update] div[class=w3-row list-content] div[class=w3-col s6 m3 l3 list]")
        for (item in elementList) {

            val tenTruyen = item.select("div[class=list-caption] h3").text()
            val tenChap = item.select("div[class=list-caption] p").text()
            val linkTruyen = item.select("a[class=w3-hover-opacity]").attr("href")
            val linkHinh = item.select("a[class=w3-hover-opacity] img").attr("src")
            val linkChap = item.select("a[class=w3-hover-opacity]").attr("href")
            val chapterModel = ChapterModel(tenChap, linkChap)
            //     listChapterTruyenFull.add(chapterModel)
            //     val truyenModel = TruyenModel(tenTruyen, linkTruyen, listChapterTruyenFull, linkHinh)
            //     listTruyenFull.add(truyenModel)
        }
    }
}
