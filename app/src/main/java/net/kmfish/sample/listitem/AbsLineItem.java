package net.kmfish.sample.listitem;

import android.view.View;
import android.widget.TextView;

import net.kmfish.multitypelistviewadapter.BaseListItem;
import net.kmfish.sample.R;
import net.kmfish.sample.model.AbsModel;

/**
 * Created by lijun on 16/11/19.
 */

public class AbsLineItem extends BaseListItem<AbsModel, Void> {

    TextView tvName;

    @Override
    public int onGetLayoutRes() {
        return R.layout.list_item1;
    }

    @Override
    public void bindViews(View convertView) {
        tvName = (TextView) convertView.findViewById(R.id.text_name);
    }

    @Override
    public void updateView(AbsModel data, int pos) {
        tvName.setText(data.name() + pos);
    }
}
