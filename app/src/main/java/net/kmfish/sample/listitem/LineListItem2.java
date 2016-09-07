package net.kmfish.sample.listitem;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.kmfish.multitypelistviewadapter.BaseListItem;
import net.kmfish.sample.R;
import net.kmfish.sample.model.ImageModel;

/**
 * Created by kmfish on 2015/9/9.
 */
public class LineListItem2 extends BaseListItem<ImageModel, Void> {

    ImageView img;
    TextView text;

    @Override
    public int onGetLayoutRes() {
        return R.layout.list_item2;
    }

    @Override
    public void bindViews(View convertView) {
        Log.d("item2", "bindViews:" + convertView);
        img = (ImageView) convertView.findViewById(R.id.thumb);
        text = (TextView) convertView.findViewById(R.id.name);

        // 可以在这里添加事件监听,通过getData,可以获得当前item绑定的data.
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LineListItem2", "onTextClicked data:" + getData());
            }
        });
    }

    @Override
    public void updateView(ImageModel model, int pos) {
        if (null != model) {
            Log.d("item2", "updateView model:" + model + "pos:" + pos);
            text.setText(model.getName());
            img.setImageResource(model.getImgResId());
        }
    }
}
