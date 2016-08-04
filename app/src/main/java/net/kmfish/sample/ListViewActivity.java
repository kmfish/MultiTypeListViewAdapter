package net.kmfish.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import net.kmfish.multitypelistviewadapter.BaseListAdapter;
import net.kmfish.sample.listitem.LineListItem1;
import net.kmfish.sample.listitem.LineListItem2;
import net.kmfish.sample.model.ImageModel;
import net.kmfish.sample.model.TextModel;

/**
 * Created by lijun on 16/8/4.
 */
public class ListViewActivity extends Activity {

    private ListView listView;
    private BaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.listview);
        adapter = new BaseListAdapter(2);
        listView.setAdapter(adapter);
        setupAdapter();
    }

    private void setupAdapter() {
        LineListItem1 item1 = new LineListItem1(this, getTextModel());
        LineListItem2 item2 = new LineListItem2(this, getImageModel());

        adapter.setNotifyOnChange(false);
        for (int i = 0, len = 15; i < len; i++) {
            adapter.addItem( i  % 2 == 0 ? item1 : item2);
        }
        adapter.notifyDataSetChanged();
    }

    private TextModel getTextModel() {
        return new TextModel("The name", "This is a desc text.");
    }

    private ImageModel getImageModel() {
        return new ImageModel("github", R.drawable.icon_git);
    }}
