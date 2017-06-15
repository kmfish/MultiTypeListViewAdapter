package net.kmfish.sample.listitem

import android.util.Log
import android.view.View
import android.widget.TextView

import net.kmfish.multitypelistviewadapter.BaseListItem
import net.kmfish.sample.R
import net.kmfish.sample.model.TextModel

/**
 * Created by kmfish on 2015/9/9.
 */
class LineListItem1 : BaseListItem<TextModel, LineListItem1.OnItem1ClickListener>() {

    private lateinit var tvName: TextView
    private lateinit var tvDesc: TextView

    override fun onGetLayoutRes(): Int {
        return R.layout.list_item1
    }

    override fun bindViews(convertView: View) {
        Log.d("item1", "bindViews:" + convertView)
        tvName = convertView.findViewById(R.id.text_name) as TextView
        tvDesc = convertView.findViewById(R.id.text_desc) as TextView

        tvName.setOnClickListener {
            if (null != attachInfo && null != data) {
                (attachInfo as OnItem1ClickListener).onNameClick(data as TextModel)
            }
        }
        tvDesc.setOnClickListener {
            if (null != attachInfo && null != data) {
                (attachInfo as OnItem1ClickListener).onDescClick(data as TextModel)
            }
        }

    }

    override fun updateView(data: TextModel, pos: Int) {
        Log.d("item1", "updateView model:" + data + "pos:" + pos)
        tvName.text = data.name
        tvDesc.text = data.desc
    }

    interface OnItem1ClickListener {
        fun onNameClick(model: TextModel)
        fun onDescClick(model: TextModel)
    }
}
