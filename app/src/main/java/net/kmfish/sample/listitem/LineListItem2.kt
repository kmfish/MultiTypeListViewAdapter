package net.kmfish.sample.listitem

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import net.kmfish.multitypelistviewadapter.BaseListItem
import net.kmfish.sample.R
import net.kmfish.sample.model.ImageModel

/**
 * Created by kmfish on 2015/9/9.
 */
class LineListItem2 : BaseListItem<ImageModel, Void>() {

    private lateinit var img: ImageView
    private lateinit var text: TextView

    override fun onGetLayoutRes(): Int {
        return R.layout.list_item2
    }

    override fun bindViews(convertView: View) {
        Log.d("item2", "bindViews:" + convertView)
        img = convertView.findViewById(R.id.thumb) as ImageView
        text = convertView.findViewById(R.id.name) as TextView

        // 可以在这里添加事件监听,通过getData,可以获得当前item绑定的data.
        text.setOnClickListener { Log.d("LineListItem2", "onTextClicked data:" + getData()) }
    }

    override fun updateView(model: ImageModel?, pos: Int) {
        if (null != model) {
            Log.d("item2", "updateView model:" + model + "pos:" + pos)
            text.text = model.name
            img.setImageResource(model.imgResId)
        }
    }
}
