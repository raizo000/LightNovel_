package com.example.binhnguyen.textmanga.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.binhnguyen.lightnovel.Activity.chiTietTruyenActivity
import com.example.binhnguyen.lightnovel.R
import com.example.binhnguyen.textmanga.Layout.ItemTruyenFullHayNhat
import com.example.binhnguyen.textmanga.Model.TruyenModel
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

/**
 * Created by Binh Nguyen on 10/28/2017.
 */
class AdapterTruyenFullHayNhat(var context: Context, var listTruyen: MutableList<TruyenModel>) : RecyclerView.Adapter<AdapterTruyenFullHayNhat.HolderTruyenFullHayNhat>() {

    override fun onBindViewHolder(holder: HolderTruyenFullHayNhat?, position: Int) {
        holder?.bindingData(context, listTruyen[position],position)
    }

    override fun getItemCount(): Int {
        return listTruyen.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup?, type: Int): HolderTruyenFullHayNhat {
        val view = ItemTruyenFullHayNhat().createView(AnkoContext.Companion.create(context, viewGroup, false))
        return HolderTruyenFullHayNhat(view)
    }

    class HolderTruyenFullHayNhat(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindingData(context: Context, truyenModel: TruyenModel, position: Int) {
            val tenTruyen = itemView.find<TextView>(R.id.tenTruyen)
            tenTruyen.text = truyenModel.tenTruyen
            val tenChap = itemView.find<TextView>(R.id.tenChap)
            tenChap.text = truyenModel.listChap[0].tenChap

            val hinh=itemView.find<ImageView>(R.id.HinhTruyen)
            Picasso.with(context).load(truyenModel.linkHinhTruyen).into(hinh)

            itemView.setOnClickListener {
                val intent = Intent(context, chiTietTruyenActivity::class.java)
                intent.putExtra("linkTruyen",truyenModel.linkTruyen)
                context.startActivity(intent)
            }
        }
    }
}