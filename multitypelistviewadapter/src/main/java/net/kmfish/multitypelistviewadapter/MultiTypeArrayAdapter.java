package net.kmfish.multitypelistviewadapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kmfish on 2015/9/9
 */
public class MultiTypeArrayAdapter extends BaseMultiTypeAdapter {

    private boolean mNotifyOnChange = true;
    private final Object mLock = new Object();
    private List<ListItem> items = new ArrayList<>();
    private int typeCount;

    public MultiTypeArrayAdapter(int typeCount) {
        this.typeCount = typeCount;
    }

    public void setTypeCount(int typeCount) {
        this.typeCount = typeCount;
    }

    public void addItem(ListItem item) {
        synchronized (mLock) {
            items.add(item);
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void addItems(List<ListItem> items) {
        synchronized (mLock) {
            this.items.addAll(items);
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void addItems(ListItem... items) {
        synchronized (mLock) {
            Collections.addAll(this.items, items);
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void insert(ListItem item, int index) {
        synchronized (mLock) {
            items.add(index, item);
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void remove(ListItem object) {
        synchronized (mLock) {
            if (items.contains(object)) {
                items.remove(object);
            }
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void clear() {
        synchronized (mLock) {
            items.clear();
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        mNotifyOnChange = true;
    }

    public void setNotifyOnChange(boolean notifyOnChange) {
        mNotifyOnChange = notifyOnChange;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ListItem getItem(int position) {
        if (position >= 0 && position < items.size()) {
            return items.get(position);
        }


        return null;
    }

    @Override
    public int getViewTypeCount() {
        return typeCount;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
