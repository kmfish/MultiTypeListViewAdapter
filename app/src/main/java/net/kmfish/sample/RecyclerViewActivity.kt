package net.kmfish.sample

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import net.kmfish.multitypelistviewadapter.BaseRecyclerAdapter
import net.kmfish.multitypelistviewadapter.Data
import net.kmfish.sample.listitem.AbsLineItem
import net.kmfish.sample.listitem.LineListItem1
import net.kmfish.sample.listitem.LineListItem2
import net.kmfish.sample.model.AbsModel
import net.kmfish.sample.model.ImageModel
import net.kmfish.sample.model.SubModel
import net.kmfish.sample.model.TextModel

/**
 * Created by lijun on 16/8/4.
 */
class RecyclerViewActivity : Activity() {

    private lateinit var adapter: BaseRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        layoutManager.recycleChildrenOnDetach = true
        recyclerView.layoutManager = layoutManager

        adapter = BaseRecyclerAdapter()
        recyclerView.adapter = adapter
        setupAdapter(adapter)
    }

    private fun setupAdapter(adapter: BaseRecyclerAdapter) {
        adapter.registerDataAndItem<TextModel, LineListItem1.OnItem1ClickListener>(TextModel::class.java, LineListItem1::class.java)
        adapter.registerDataAndItem<ImageModel, Void>(ImageModel::class.java, LineListItem2::class.java)
        adapter.registerDataAndItem<AbsModel, Void>(AbsModel::class.java, AbsLineItem::class.java)

        adapter.setNotifyOnChange(false)
        var i = 0
        val len = 10
        while (i < len) {
            adapter.addData(if (i % 2 == 0) Data.create(getTextModel(i)) else Data.create(getImageModel(i)))
            i++
        }
        adapter.notifyDataSetChanged()

        adapter.addData(Data.create(SubModel())) // SubModel extends AbsModel
    }

    private fun getTextModel(i: Int): TextModel {
        return TextModel("The name:" + i, "This is a desc text.")
    }

    private fun getImageModel(i: Int): ImageModel {
        return ImageModel("github:" + i, R.drawable.icon_git)
    }
}
