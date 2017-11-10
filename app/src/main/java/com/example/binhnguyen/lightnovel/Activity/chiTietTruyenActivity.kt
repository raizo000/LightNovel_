package com.example.binhnguyen.lightnovel.Activity

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.text.Html
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
        chapterRecyclerView?.adapter = adapterChapter
    }

    private fun getChitietTruyen(linkTruyen: String) {
        val listLink = arrayListOf<String>()
        val tempList2 = mutableListOf<ChapterModel>()
        doAsync {
            val document = Jsoup.connect(linkTruyen).get()
            val tenTruyen = document.select("div[class=w3-col m8 l8 w3-container w3-center detail-right] h1 a").text()
            val hinhTruyen = document.select("div[class=w3-col s4 m12 l12 detail-thumbnail] img ").attr("src")
            val thongtinTruyen = document.select("div[class=w3-col s8 m12 l12 detail-info] ul[class=w3-ul] li")
            val tacGia = thongtinTruyen[0].select(" h2 a").text()
            val theLoaiElement = thongtinTruyen[1].select("a")
            var theLoai = ""
            for (value in theLoaiElement) {
                theLoai += value.attr("title") + ", "
            }
            val nguonTruyen = thongtinTruyen[2].text()
            val tinhTrang = thongtinTruyen[3].select("span").text()
            val moTa = document.select("div[class=w3-justify summary] article")

            val chapterElement = document.select("div[id=divtab] ul[class=w3-ul] li  ")
            val tempList = mutableListOf<ChapterModel>()
            for (value in chapterElement) {
                val linkChapter = value.select("h4 a").attr("href")
                val tenChapter = value.select("h4 a").text()
                val ngayCapNhat = value.select("span[class=w3-right w3-hide-small]").text()
                val chapter = ChapterModel(tenChapter, linkChapter, ngayCapNhat)
                tempList.add(chapter)
            }
            listChapter.addAll(tempList)

            val linkPaginationElement = document.select("div[class=w3-center pagination] ul[class=w3-pagination paging] li a[class=last]").attr("href")
            if (linkPaginationElement.equals("")) {
                val LINK = document.select("div[class=w3-center pagination] ul[class=w3-pagination paging] li a ")
                for (value in LINK) {
                    val linkChapter: String = value.attr("href")
                    if (!linkChapter.isEmpty()) {
                        listLink.add(value.select("a").attr("href"))
                    }
                }

            }
            if (!listLink.isEmpty()) {
                for (values in listLink) {
                    val document2 = Jsoup.connect("${values}").get()
                    tempList2.clear()
                    val chapterElement2 = document2.select("div[id=divtab] ul[class=w3-ul] li  h4")
                    for (value in chapterElement2) {
                        val linkChapter = value.select("a").attr("href")
                        val tenChapter = value.select("a").text()
                        val ngayCapNhat = value.select("span[class=w3-right w3-hide-small]").text()
                        val chapter2 = ChapterModel(tenChapter, linkChapter, ngayCapNhat)
                        tempList2.add(chapter2)
                    }
                    listChapter.addAll(tempList2)
                }
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
