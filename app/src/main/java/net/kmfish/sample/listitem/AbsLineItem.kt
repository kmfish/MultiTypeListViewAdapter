package net.kmfish.sample.listitem

import android.view.View
import android.widget.TextView

import net.kmfish.multitypelistviewadapter.BaseListItem
import net.kmfish.sample.R
import net.kmfish.sample.model.AbsModel

/**
 * Created by lijun on 16/11/19.
 */

class AbsLineItem : BaseListItem<AbsModel, Void>() {

    private lateinit var tvName: TextView

    override fun onGetLayoutRes(): Int {
        return R.layout.list_item1
    }

    override fun bindViews(convertView: View) {
        tvName = convertView.findViewById(R.id.text_name) as TextView
    }

    override fun updateView(data: AbsModel, pos: Int) {
        tvName.text = data.name() + pos
    }
}
