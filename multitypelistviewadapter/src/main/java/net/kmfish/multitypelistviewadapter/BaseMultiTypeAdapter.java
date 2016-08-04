package net.kmfish.multitypelistviewadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by kmfish on 2015/9/9
 */
public abstract class BaseMultiTypeAdapter extends BaseAdapter implements IArrayAdapter {

    private LayoutInflater mInflater;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == mInflater) {
            mInflater = LayoutInflater.from(parent.getContext());
        }

        ListItem item;
        if (null == convertView) {
            item = getItem(position);
            convertView = mInflater.inflate(item.onGetLayoutRes(), parent, false);
            convertView.setTag(item);
            item.bindViews(convertView);
        } else {
            item = (ListItem) convertView.getTag();
        }

        ListItem dataItem = getItem(position);
        item.updateView(dataItem.getData(), position);
        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        ListItem item = getItem(position);
        if (null != item) {
            return item.isEnabled();
        }

        return super.isEnabled(position);
    }

}