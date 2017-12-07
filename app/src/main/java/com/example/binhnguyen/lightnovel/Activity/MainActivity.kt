package com.example.binhnguyen.lightnovel.Activity

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.binhnguyen.lightnovel.Fragment.theLoaiFragment
import com.example.binhnguyen.lightnovel.Model.TheLoai
import com.example.binhnguyen.lightnovel.R
import com.example.binhnguyen.textmanga.Adapter.AdapterTruyenDecu
import com.example.binhnguyen.textmanga.Adapter.AdapterTruyenFullHayNhat
import com.example.binhnguyen.textmanga.Layout.MainLayout
import com.example.binhnguyen.textmanga.Model.ChapterModel
import com.example.binhnguyen.textmanga.Model.TruyenModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity(), TextWatcher {
    var timKiem: EditText? = null
    var tempList: MutableList<TruyenModel> = mutableListOf()


    var noidung: LinearLayout? = null

    var listChapterTruyenDeCu: MutableList<ChapterModel> = mutableListOf()
    var listTruyenDeCu: MutableList<TruyenModel> = mutableListOf()

    var listChapterTruyenFullHayNhat: MutableList<ChapterModel> = mutableListOf()
    var listTruyenFullMoiNhat: MutableList<TruyenModel> = mutableListOf()

    var listChapterTruyenCapNhat: MutableList<ChapterModel> = mutableListOf()
    var listTruyenCapNhat: MutableList<TruyenModel> = mutableListOf()

    var listChapterTruyenFull: MutableList<ChapterModel> = mutableListOf()
    var listTruyenFull: MutableList<TruyenModel> = mutableListOf()

    var adapterTruyenDeCu: AdapterTruyenDecu? = null
    var adapterTruyenCapNhat: AdapterTruyenDecu? = null
    var adapterTruyenFullHayNhat: AdapterTruyenFullHayNhat? = null
    var adapterTimKiem: AdapterTruyenFullHayNhat? = null

    var recyclerTruyenDeCu: RecyclerView? = null
    var recyclerTruyenCapNhat: RecyclerView? = null
    var recyclerTruyenFullHayNhat: RecyclerView? = null
    var recyclerTimKiem: RecyclerView? = null

    var imgTheLoai: ImageView? = null
    var isTheLoai: Boolean = false
    var fragmentTheLoai: theLoaiFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainLayout().setContentView(this)
        getDanhSachTruyen()
        getDanhSachTruyenFullHayNhat()
        if (listTruyenFull.size == 0) {
            getDuLieuTruyenFull()
        }

        recyclerTruyenDeCu = find(R.id.recyclTruyenDeCu)
        adapterTruyenDeCu = AdapterTruyenDecu(this, listTruyenDeCu)
        recyclerTruyenDeCu?.adapter = adapterTruyenDeCu


        recyclerTruyenFullHayNhat = find(R.id.recyclTruyenFullHayNhat)
        adapterTruyenFullHayNhat = AdapterTruyenFullHayNhat(false, this, listTruyenFullMoiNhat)
        recyclerTruyenFullHayNhat?.adapter = adapterTruyenFullHayNhat

        timKiem = find(R.id.search)
        recyclerTimKiem = find(R.id.timKiemRecycler)
        noidung = find(R.id.noiDung)
        timKiem?.addTextChangedListener(this)

        fragmentTheLoai = theLoaiFragment()

        imgTheLoai = find(R.id.theLoai)
        imgTheLoai?.onClick {
            if (isTheLoai) {
                val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.remove(fragmentTheLoai)
                fragmentTransaction.commit()
            } else {
                val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.khungNoiDung, fragmentTheLoai)
                fragmentTransaction.commit()
            }
            isTheLoai = !isTheLoai
        }

    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        if (p0?.trim()!!.length > 0) {
            noidung?.visibility = View.GONE
            recyclerTimKiem?.visibility = View.VISIBLE

            tempList.clear()
            tempList = listTruyenFull.filter { p0.toString() in it.tenTruyen }.toMutableList()
            adapterTimKiem = AdapterTruyenFullHayNhat(true, this, tempList)
            recyclerTimKiem?.adapter = adapterTimKiem
            adapterTimKiem?.notifyDataSetChanged()
        } else {
            noidung?.visibility = View.VISIBLE
            recyclerTimKiem?.visibility = View.GONE
        }

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



    private fun getDuLieuTruyenFull() {
        val linkPage = "http://webtruyen.com/truyen-full/"
        doAsync {
            val document = Jsoup.connect("http://webtruyen.com/truyen-full/").get()
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
            listChapterTruyenFull.add(chapterModel)
            val truyenModel = TruyenModel(tenTruyen, linkTruyen, listChapterTruyenFull, linkHinh)
            listTruyenFull.add(truyenModel)
        }
    }

    private fun getDanhSachTruyenFullHayNhat() {
        doAsync {
            val document = Jsoup.connect("http://webtruyen.com/truyen-full/").get()
            val elementList_Decu = document.select("div[id=main] div[class=list-update] div[class=w3-row list-content]  div[class=w3-row list-row-img]")
            Log.d("elementList_Decu", "$elementList_Decu")
            for (item in elementList_Decu) {

                val tenTruyen = item.select("div[class=w3-col s2 m2 l2 row-image] div  a").attr("title")

                val tenChap = item.select("div[class=w3-col s3 m3 l3 row-number] span[class=row-time]").text()
                val linkTruyen = item.select("div[class=w3-col s2 m2 l2 row-image] div  a").attr("href")
                val linkHinh = item.select("div[class=w3-col s2 m2 l2 row-image] img").attr("src")
                val linkChap = item.select("div[class=w3-col s7 m7 l7 row-info]").attr("href")
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
