package net.kmfish.sample.listitem;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import net.kmfish.multitypelistviewadapter.BaseListItem;
import net.kmfish.sample.R;
import net.kmfish.sample.model.TextModel;

/**
 * Created by kmfish on 2015/9/9.
 */
public class LineListItem1 extends BaseListItem<TextModel> {

    TextView tvName;
    TextView tvDesc;

    public LineListItem1(Context mContext, TextModel model) {
        super(mContext, model);
    }

    @Override
    public int onGetLayoutRes() {
        return R.layout.list_item1;
    }

    @Override
    public void bindViews(View convertView) {
        tvName = (TextView) convertView.findViewById(R.id.text_name);
        tvDesc = (TextView) convertView.findViewById(R.id.text_desc);
    }

    @Override
    public void updateView(TextModel model, int pos) {
        if (null != model) {
            tvName.setText(model.getName());
            tvDesc.setText(model.getDesc());
        }
    }
}
