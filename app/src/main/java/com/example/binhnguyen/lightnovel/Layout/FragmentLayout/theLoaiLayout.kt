package com.example.binhnguyen.lightnovel.Layout.FragmentLayout

import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.view.ViewGroup
import com.example.binhnguyen.lightnovel.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by jenov on 11/21/2017.
 */
class theLoaiLayout : AnkoComponent<ViewGroup?> {
    override fun createView(ui: AnkoContext<ViewGroup?>): View {
        return with(ui) {
            frameLayout {
                lparams(width = matchParent, height = matchParent)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        background = ContextCompat.getDrawable(context, R.color.wheat)
                    }

                recyclerView {
                    id = R.id.theLoaiRecycler
                    layoutManager = GridLayoutManager(context, 3)
                }
            }
        }
    }
}