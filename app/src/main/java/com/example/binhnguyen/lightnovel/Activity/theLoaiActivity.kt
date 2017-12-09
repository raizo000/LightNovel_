package com.example.binhnguyen.lightnovel.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.GridLayout
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
    var recyclerViewTheLoai: RecyclerView? = null
    var toolBarText: TextView? = null
    var maxPage = 1
    var itemDangHienThi = 8
    var linkPage = ""
    var oldPage = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        theLoaiLayout().setContentView(this)
        recyclerViewTheLoai = find(R.id.recyclerTheLoai)
        toolBarText = find(R.id.toolBarText)
        val link = intent.getStringExtra("linkTheLoai")

        adapterTheLoai = AdapterTruyenFullHayNhat(false, this, listTruyen)
        recyclerViewTheLoai?.adapter = adapterTheLoai


        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView?.layoutManager as GridLayoutManager
                val tongItemDaHienThi = layoutManager.findFirstCompletelyVisibleItemPosition()
                //   Log.d("Tong item da hien thi", "$tongItemDaHienThi")
                val tongItem = layoutManager.itemCount
                Log.d("Tong item ", "$tongItem")
                //   Log.d("Tong item", "${(tongItemDaHienThi + itemDangHienThi)}")

                if (tongItem == (tongItemDaHienThi + itemDangHienThi)) {
               //     Log.d("Tong item ", "$maxPage")
                    if (oldPage <= maxPage) {
                        getDanhSachTruyen("$linkPage$oldPage" + "/")
                    }

                }
            }
        }
        recyclerViewTheLoai?.addOnScrollListener(scrollListener)
        toolbar(link)
        getDuLieuTruyenFull(link)
    }

    private fun getDuLieuTruyenFull(link: String) {
        linkPage = link
        doAsync {
            val document = Jsoup.connect(link).get()
            val maxPageLink = document.select("div[class=w3-center pagination] ul[class=w3-pagination paging] li a[class=last]").attr("title")
            maxPage = maxPageLink.substring(5, maxPageLink.length).toInt()
            //    for (value in 1..maxPage.toInt()) {
            getDanhSachTruyen("$linkPage" + "1/")
            // }
        }
    }

    private fun toolbar(link: String) {
        doAsync {
            val document = Jsoup.connect(link).get()
            val theLoai = document.select("div[class=title-left] h1 a").attr("title")
            uiThread {
                toolBarText?.text = theLoai
            }
        }
    }

    private fun getDanhSachTruyen(link: String) {
        doAsync {
            val document = Jsoup.connect(link).get()
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

                adapterTheLoai?.notifyDataSetChanged()
                oldPage++
            }
        }
    }
}
