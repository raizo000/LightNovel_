package com.example.binhnguyen.lightnovel.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.example.binhnguyen.lightnovel.R
import com.example.binhnguyen.textmanga.Adapter.AdapterTruyenDecu
import com.example.binhnguyen.textmanga.Adapter.AdapterTruyenFullHayNhat
import com.example.binhnguyen.textmanga.Layout.MainLayout
import com.example.binhnguyen.textmanga.Model.ChapterModel
import com.example.binhnguyen.textmanga.Model.TruyenModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {
    var listChapterTruyenDeCu: MutableList<ChapterModel> = mutableListOf()
    var listTruyenDeCu: MutableList<TruyenModel> = mutableListOf()

    var listChapterTruyenFullHayNhat: MutableList<ChapterModel> = mutableListOf()
    var listTruyenFullMoiNhat: MutableList<TruyenModel> = mutableListOf()

    var listChapterTruyenCapNhat: MutableList<ChapterModel> = mutableListOf()
    var listTruyenCapNhat: MutableList<TruyenModel> = mutableListOf()

    var adapterTruyenDeCu: AdapterTruyenDecu? = null
    var adapterTruyenCapNhat: AdapterTruyenDecu? = null
    var adapterTruyenFullHayNhat: AdapterTruyenFullHayNhat? = null

    var recyclerTruyenDeCu: RecyclerView? = null
    var recyclerTruyenCapNhat: RecyclerView? = null
    var recyclerTruyenFullHayNhat: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainLayout().setContentView(this)
        getDanhSachTruyen()
        getDanhSachTruyenCapNhat()
        getDanhSachTruyenFullHayNhat()
        recyclerTruyenDeCu = find(R.id.recyclTruyenDeCu)
        adapterTruyenDeCu = AdapterTruyenDecu(this, listTruyenDeCu)
        recyclerTruyenDeCu?.adapter = adapterTruyenDeCu

        recyclerTruyenCapNhat = find(R.id.recyclTruyenCapNhat)
        adapterTruyenCapNhat = AdapterTruyenDecu(this, listTruyenCapNhat)
        recyclerTruyenCapNhat?.adapter = adapterTruyenCapNhat

        recyclerTruyenFullHayNhat = find(R.id.recyclTruyenFullHayNhat)
        adapterTruyenFullHayNhat = AdapterTruyenFullHayNhat(this, listTruyenFullMoiNhat)
        recyclerTruyenFullHayNhat?.adapter = adapterTruyenFullHayNhat

    }

    private fun getDanhSachTruyen() {
        doAsync {
            val document = Jsoup.connect("http://webtruyen.com/").get()
            val elementList_Decu = document.select(" div[class=w3-row ]  div[class=w3-col s12 m12 l8 main w3-container] div[class=w3-col s12 m12 l12 slides] div.w3-col ")
            for (item in elementList_Decu) {

                val tenTruyen = item.select("div[class=slides-caption] h3").text()
                val tenChap = item.select("div[class=slides-caption] p").text()
                val linkTruyen = item.select("a[class=w3-hover-opacity]").attr("href")
                val linkHinh = item.select("a[class=w3-hover-opacity] img").attr("src")
                val linkChap = item.select("a[class=w3-hover-opacity]").attr("href")
                val chapterModel = ChapterModel(tenChap, linkChap)

                listChapterTruyenDeCu.add(chapterModel)
                val truyenModel = TruyenModel(tenTruyen, linkTruyen, listChapterTruyenDeCu, linkHinh)
                listTruyenDeCu.add(truyenModel)
            }

            uiThread {
                adapterTruyenDeCu?.notifyDataSetChanged()
            }

        }
    }

    private fun getDanhSachTruyenCapNhat() {
        doAsync {
            val document = Jsoup.connect("http://webtruyen.com/").get()
            val elementList_Decu = document.select(" div[class=list-update]  div[class=w3-row list-content] div[class=w3-col s6 m3 l3 list] ")
            for (item in elementList_Decu) {

                val tenTruyen = item.select("div[class=list-caption] h3").text()
                val tenChap = item.select("div[class=list-caption] p").text()
                val linkTruyen = item.select("a[class=w3-hover-opacity]").attr("href")
                val linkHinh = item.select("a[class=w3-hover-opacity] img").attr("src")
                val linkChap = item.select("a[class=w3-hover-opacity]").attr("href")
                val chapterModel = ChapterModel(tenChap, linkChap)
                listChapterTruyenCapNhat.add(chapterModel)
                val truyenModel = TruyenModel(tenTruyen, linkTruyen, listChapterTruyenCapNhat, linkHinh)
                listTruyenCapNhat.add(truyenModel)
            }
            uiThread {
                adapterTruyenCapNhat?.notifyDataSetChanged()
            }

        }
    }
    private fun getDuLieuTrangChu(){
        val link 
    }
    private fun getDanhSachTruyenFullHayNhat() {
        doAsync {
            val document = Jsoup.connect("http://webtruyen.com/truyen-full/").get()
            val elementList_Decu = document.select("div[class=list-update] div[class=w3-row list-content] div[class=w3-col s6 m3 l3 list]")
            for (item in elementList_Decu) {

                val tenTruyen = item.select("div[class=list-caption] h3").text()
                val tenChap = item.select("div[class=list-caption] p").text()
                val linkTruyen = item.select("a[class=w3-hover-opacity]").attr("href")
                val linkHinh = item.select("a[class=w3-hover-opacity] img").attr("src")
                val linkChap = item.select("a[class=w3-hover-opacity]").attr("href")
                val chapterModel = ChapterModel(tenChap, linkChap)
                listChapterTruyenFullHayNhat.add(chapterModel)
                val truyenModel = TruyenModel(tenTruyen, linkTruyen, listChapterTruyenFullHayNhat, linkHinh)
                listTruyenFullMoiNhat.add(truyenModel)
            }
            uiThread {
                adapterTruyenFullHayNhat?.notifyDataSetChanged()
            }
        }
    }
}
