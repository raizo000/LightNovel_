package com.example.binhnguyen.lightnovel.Activity

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.binhnguyen.lightnovel.Adapter.AdapterChapter
import com.example.binhnguyen.lightnovel.Layout.chiTietTruyenLayout
import com.example.binhnguyen.lightnovel.R
import com.example.binhnguyen.textmanga.Model.ChapterModel
import com.squareup.picasso.Picasso
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup

class chiTietTruyenActivity : AppCompatActivity() {
    var tenTruyenTruyenChiTiet: TextView? = null
    var tacGiaTruyenChiTiet: TextView? = null
    var theLoaiTruyenChiTiet: TextView? = null
    var tinhTrangTruyenChiTiet: TextView? = null
    var nguonTruyenChiTiet: TextView? = null
    var moTaTruyenChiTiet: TextView? = null
    var hinhTruyenChiTiet: ImageView? = null
    var listChapter = mutableListOf<ChapterModel>()
    var chapterRecyclerView: RecyclerView? = null
    var adapterChapter: AdapterChapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chiTietTruyenLayout().setContentView(this)
        val linkTruyen = intent.getStringExtra("linkTruyen")
        getChitietTruyen(linkTruyen)

        tacGiaTruyenChiTiet = find(R.id.tacGiaChiTietTruyen)
        tenTruyenTruyenChiTiet = find(R.id.tenTruyenChiTiet)
        theLoaiTruyenChiTiet = find(R.id.theLoaiTruyenChiTiet)
        tinhTrangTruyenChiTiet = find(R.id.tinhTrangChiTietTruyen)
        nguonTruyenChiTiet = find(R.id.nguonTruyenChiTiet)
        hinhTruyenChiTiet = find(R.id.hinhTruyenChiTiet)
        moTaTruyenChiTiet = find(R.id.moTaTruyenChiTiet)
        chapterRecyclerView = find(R.id.danhSachChapter)

        adapterChapter = AdapterChapter(this, listChapter)
        chapterRecyclerView?.adapter=adapterChapter
    }

    private fun getChitietTruyen(linkTruyen: String) {
        doAsync {
            val document = Jsoup.connect(linkTruyen).get()
            val tenTruyen = document.select("div[class=w3-col m8 l8 w3-container w3-center detail-right] h1 a").text()
            val hinhTruyen = document.select("div[class=w3-col s4 m12 l12 detail-thumbnail] img ").attr("src")
            val thongtinTruyen = document.select("div[class=w3-col s8 m12 l12 detail-info] ul[class=w3-ul] li")
            val tacGia = thongtinTruyen[0].select(" h2 a").text()
            val theLoaiElement = thongtinTruyen[1].select("a")
            var theLoai = ""
            for (value in theLoaiElement) {
                theLoai += value.attr("title") + " "
            }
            val nguonTruyen = thongtinTruyen[2].text()
            val tinhTrang = thongtinTruyen[3].select("span").text()
            val moTa = document.select("div[class=w3-justify summary] article")

            val chapterElement = document.select("div[id=divtab] ul[class=w3-ul] li h4 a ")
            for (value in chapterElement) {
                val linkChapter = value.attr("href")
                val tenChapter = value.attr("title")
                Log.d("Mô tả", "$tenChapter")
                val chapter = ChapterModel(tenChapter, linkChapter)
                listChapter.add(chapter)
            }

            uiThread {
                Picasso.with(applicationContext).load(hinhTruyen).into(hinhTruyenChiTiet)
                tenTruyenTruyenChiTiet?.text = "$tenTruyen"
                tacGiaTruyenChiTiet?.text = "${resources.getString(R.string.tacGiaTruyenChiTiet)} $tacGia"
                theLoaiTruyenChiTiet?.text = "${resources.getString(R.string.theLoaiTruyenChiTiet)} $theLoai"
                tinhTrangTruyenChiTiet?.text = "${resources.getString(R.string.tinhTrangTruyenChiTiet)} $tinhTrang"
                nguonTruyenChiTiet?.text = "${resources.getString(R.string.nguonTruyenChiTiet)} $nguonTruyen"
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    moTaTruyenChiTiet?.text = Html.fromHtml(moTa.html(), 0)
                } else {
                    moTaTruyenChiTiet?.text = Html.fromHtml(moTa.html())

                }
                adapterChapter?.notifyDataSetChanged()
            }
        }
    }
}
