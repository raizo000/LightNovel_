package com.example.binhnguyen.lightnovel.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.binhnguyen.lightnovel.Activity.noiDungTruyenActivity
import com.example.binhnguyen.lightnovel.Layout.itemChapter
import com.example.binhnguyen.lightnovel.Model.LinkModel
import com.example.binhnguyen.lightnovel.R
import com.example.binhnguyen.textmanga.Model.ChapterModel
import com.example.jenov.manga.DB.DatabaseHelper
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

/**
 * Created by Binh Nguyen on 11/04/2017.
 */
class AdapterChapter(var context: Context, var listChapter: MutableList<ChapterModel>) : RecyclerView.Adapter<AdapterChapter.HolderChapter>() {
    override fun getItemCount(): Int {
        return listChapter.size
    }

    override fun onBindViewHolder(holder: HolderChapter?, position: Int) {
        holder?.bindingData(context, listChapter[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HolderChapter {
        val view = itemChapter().createView(AnkoContext.Companion.create(context, parent, false))
        return HolderChapter(view)
    }

    class HolderChapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindingData(context: Context, chapterModel: ChapterModel) {
            val tencChap = itemView.find<TextView>(R.id.tenChapterChiTiet)
            val ngayDang = itemView.find<TextView>(R.id.ngayDang)
            tencChap.text = chapterModel.tenChap
            val db = DatabaseHelper(context)
            val allData: MutableList<LinkModel>
            var data = LinkModel("", "")
            allData = db.getParticularMangaData(chapterModel.linkChap)
            for (values in allData) {
                data = LinkModel(values.link, "")
            }

            if (!data.link.isEmpty()) {
                ngayDang.text = "Đã xem"
            } else {
                ngayDang.text = ""
            }



            itemView.setOnClickListener {
                db.insertMangatData(chapterModel.linkChap, chapterModel.ngayDang)
                val intent = Intent(context, noiDungTruyenActivity::class.java)
                intent.putExtra("linkNoiDungTruyen", chapterModel.linkChap)
                context.startActivity(intent)
            }
        }
    }


}