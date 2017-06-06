package net.kmfish.sample

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.ListView

import net.kmfish.multitypelistviewadapter.BaseListAdapter
import net.kmfish.multitypelistviewadapter.Data
import net.kmfish.sample.listitem.LineListItem1
import net.kmfish.sample.listitem.LineListItem2
import net.kmfish.sample.model.ImageModel
import net.kmfish.sample.model.TextModel

/**
 * Created by lijun on 16/8/4.
 */
class ListViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val listView = findViewById(R.id.listview) as ListView
        val adapter = BaseListAdapter()
        adapter.registerDataAndItem<TextModel, LineListItem1.OnItem1ClickListener>(TextModel::class.java, LineListItem1::class.java, item1ClickListener)
        adapter.registerDataAndItem<ImageModel, Void>(ImageModel::class.java, LineListItem2::class.java)

        listView.adapter = adapter
        //        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //            @Override
        //            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //                Log.d(TAG, "onItemClick() called with: " + "parent = [" + parent + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]");
        //            }
        //        });

        setupAdapter(adapter)
    }

    private fun setupAdapter(adapter: BaseListAdapter) {

        adapter.setNotifyOnChange(false)
        var i = 0
        val len = 30
        while (i < len) {
            adapter.addData(if (i % 2 == 0) Data.create(getTextModel(i)) else Data.create(getImageModel(i)))
            i++
        }
        adapter.notifyDataSetChanged()
    }

    private fun getTextModel(i: Int): TextModel {
        return TextModel("The name:" + i, "This is a desc text.")
    }

    private fun getImageModel(i: Int): ImageModel {
        return ImageModel("github:" + i, R.drawable.icon_git)
    }

    private val item1ClickListener = object : LineListItem1.OnItem1ClickListener {
        override fun onNameClick(model: TextModel) {
            Log.d(TAG, "onNameClick() called with: model = [$model]")
        }

        override fun onDescClick(model: TextModel) {
            Log.d(TAG, "onDescClick() called with: model = [$model]")
        }
    }

    companion object {

        private val TAG = "ListViewActivity"
    }
}
