package net.kmfish.sample.listitem;

import android.content.Context;
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
public class LineListItem2 extends BaseListItem<ImageModel> {

    ImageView img;
    TextView text;

    public LineListItem2(Context mContext, ImageModel model) {
        super(mContext, model);
    }

    @Override
    public int onGetLayoutRes() {
        return R.layout.list_item2;
    }

    @Override
    public void bindViews(View convertView) {
        img = (ImageView) convertView.findViewById(R.id.thumb);
        text = (TextView) convertView.findViewById(R.id.name);
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
