package com.example.binhnguyen.lightnovel.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.binhnguyen.lightnovel.Activity.theLoaiActivity
import com.example.binhnguyen.lightnovel.Layout.FragmentLayout.itemTheLoai
import com.example.binhnguyen.lightnovel.Model.TheLoai
import com.example.binhnguyen.lightnovel.R
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by jenov on 11/21/2017.
 */
class AdapterTheLoai(val context: Context, val theLoaiList: MutableList<TheLoai>) : RecyclerView.Adapter<AdapterTheLoai.Holder>() {
    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindingData(context: Context, theLoaiModel: TheLoai) {
            val theLoaiText = itemView.find<TextView>(R.id.tenTheLoai)
            theLoaiText.text = theLoaiModel.ten
            itemView.onClick {
                val theLoai = Intent(context, theLoaiActivity::class.java)
                theLoai.putExtra("linkTheLoai", theLoaiModel.link)
                context.startActivity(theLoai)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val view = itemTheLoai().createView(AnkoContext.Companion.create(context, parent, false))
        return Holder(view);
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.bindingData(context, theLoaiList.get(position))
    }

    override fun getItemCount(): Int {
        return theLoaiList.size
    }
}