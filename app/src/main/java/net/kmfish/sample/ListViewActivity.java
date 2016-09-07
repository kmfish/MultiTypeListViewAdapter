package net.kmfish.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import net.kmfish.multitypelistviewadapter.BaseListAdapter;
import net.kmfish.multitypelistviewadapter.Data;
import net.kmfish.sample.listitem.LineListItem1;
import net.kmfish.sample.listitem.LineListItem2;
import net.kmfish.sample.model.ImageModel;
import net.kmfish.sample.model.TextModel;

/**
 * Created by lijun on 16/8/4.
 */
public class ListViewActivity extends Activity {

    private static final String TAG = "ListViewActivity";

    private ListView listView;
    private BaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.listview);
        adapter = new BaseListAdapter();
        adapter.registerDataAndItem(TextModel.class, LineListItem1.class, item1ClickListener);
        adapter.registerDataAndItem(ImageModel.class, LineListItem2.class);

        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d(TAG, "onItemClick() called with: " + "parent = [" + parent + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]");
//            }
//        });
        setupAdapter(adapter);
    }

    private void setupAdapter(BaseListAdapter adapter) {

        adapter.setNotifyOnChange(false);
        for (int i = 0, len = 30; i < len; i++) {
            adapter.addData( i  % 2 == 0 ? Data.create(getTextModel(i)) : Data.create(getImageModel(i)));
        }
        adapter.notifyDataSetChanged();
    }

    private TextModel getTextModel(int i) {
        return new TextModel("The name:" + i, "This is a desc text.");
    }

    private ImageModel getImageModel(int i) {
        return new ImageModel("github:" + i, R.drawable.icon_git);
    }

    private LineListItem1.OnItem1ClickListener item1ClickListener = new LineListItem1.OnItem1ClickListener() {
        @Override
        public void onNameClick(TextModel model) {
            Log.d(TAG, "onNameClick() called with: " + "model = [" + model + "]");
        }

        @Override
        public void onDescClick(TextModel model) {
            Log.d(TAG, "onDescClick() called with: " + "model = [" + model + "]");
        }
    };
}
