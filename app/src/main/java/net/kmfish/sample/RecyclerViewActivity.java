package net.kmfish.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.kmfish.multitypelistviewadapter.BaseRecyclerAdapter;
import net.kmfish.multitypelistviewadapter.Data;
import net.kmfish.sample.listitem.AbsLineItem;
import net.kmfish.sample.listitem.LineListItem1;
import net.kmfish.sample.listitem.LineListItem2;
import net.kmfish.sample.model.AbsModel;
import net.kmfish.sample.model.ImageModel;
import net.kmfish.sample.model.SubModel;
import net.kmfish.sample.model.TextModel;

/**
 * Created by lijun on 16/8/4.
 */
public class RecyclerViewActivity extends Activity {

    private BaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setRecycleChildrenOnDetach(true);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BaseRecyclerAdapter();
        recyclerView.setAdapter(adapter);
        setupAdapter(adapter);
    }

    private void setupAdapter(BaseRecyclerAdapter adapter) {
        adapter.registerDataAndItem(TextModel.class, LineListItem1.class);
        adapter.registerDataAndItem(ImageModel.class, LineListItem2.class);
        adapter.registerDataAndItem(AbsModel.class, AbsLineItem.class);

        adapter.setNotifyOnChange(false);
        for (int i = 0, len = 10; i < len; i++) {
            adapter.addData( i  % 2 == 0 ? Data.create(getTextModel(i)) : Data.create(getImageModel(i)));
        }
        adapter.notifyDataSetChanged();

        adapter.addData(Data.create(new SubModel())); // SubModel extends AbsModel
    }

    private TextModel getTextModel(int i) {
        return new TextModel("The name:" + i, "This is a desc text.");
    }

    private ImageModel getImageModel(int i) {
        return new ImageModel("github:" + i, R.drawable.icon_git);
    }
}
