package net.kmfish.multitypelistviewadapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by kmfish on 2015/9/9
 */
public abstract class BaseMultiTypeAdapter extends BaseAdapter {

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public abstract ListItem getItem(int position);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final ListItem item = getItem(position);

        if (null == item) {
            throw new RuntimeException("list item is never null. pos:" + position);
        }

        if (null == convertView) {
            holder = item.createViewHolder(parent);
            convertView = holder.itemView;
            convertView.setTag(holder);
            Log.d("BaseMultiTypeAdapter", "createView pos:" + position + " convertView:" + convertView + " holder:" + holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            Log.d("BaseMultiTypeAdapter", "getTag pos:" + position + " convertView:" + convertView + " holder:" + holder);
        }

        item.updateHolder(holder, position);
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