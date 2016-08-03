package net.kmfish.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import net.kmfish.multitypelistviewadapter.BaseListAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private BaseListAdapter adapter;
    private static final int ITEM_TYPE_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);
        adapter = new BaseListAdapter(ITEM_TYPE_COUNT);
        listView.setAdapter(adapter);
        setupAdapter();
    }

    private void setupAdapter() {
        LineListItem1 item1 = new LineListItem1(this, "line type 1");
        LineListItem2 item2 = new LineListItem2(this, "line type 2");

        adapter.setNotifyOnChange(false);
        for (int i = 0, len = 50; i < len; i++) {
            adapter.addItem( i  % 2 == 0 ? item1 : item2);
        }

        adapter.insert(item1, 6);
        adapter.notifyDataSetChanged();
    }
}
