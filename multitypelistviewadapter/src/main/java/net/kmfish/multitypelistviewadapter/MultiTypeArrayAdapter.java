package net.kmfish.multitypelistviewadapter;

import android.support.v4.util.SparseArrayCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kmfish on 2015/9/9
 */
public class MultiTypeArrayAdapter extends BaseMultiTypeAdapter {

    public static final String TAG = MultiTypeArrayAdapter.class.getSimpleName();

    private boolean mNotifyOnChange = true;
    private final Object mLock = new Object();
    private List<ListItem> items = new ArrayList<>();
    private final int typeCount;
    private SparseArrayCompat<Class> itemTypeClasses = new SparseArrayCompat<>();

    public MultiTypeArrayAdapter(int typeCount) {
        this.typeCount = typeCount;
    }

    public void addItem(ListItem item) {
        synchronized (mLock) {
            items.add(item);
            addItemTypeClasses(item.getClass());
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void addItems(List<ListItem> items) {
        synchronized (mLock) {
            this.items.addAll(items);
            for (ListItem item : items) {
                addItemTypeClasses(item.getClass());
            }
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void addItems(ListItem... items) {
        synchronized (mLock) {
            Collections.addAll(this.items, items);
            for (int i = 0; i < items.length; i++) {
                addItemTypeClasses(items[i].getClass());
            }
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void insert(ListItem item, int index) {
        synchronized (mLock) {
            items.add(index, item);
            addItemTypeClasses(item.getClass());
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
    public int getItemViewType(int position) {
        ListItem item = getItem(position);
        if (null != item) {
            int type = itemTypeClasses.indexOfValue(item.getClass());
            Log.d(TAG, "getItemViewType pos:" + position + " type:" + type);
            return type;
        } else {
            return 0;
        }
    }

    @Override
    public int getViewTypeCount() {
        return typeCount <= 0 ? 1 : typeCount;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void addItemTypeClasses(Class itemClz) {
        if (itemTypeClasses.indexOfValue(itemClz) == -1) {
            itemTypeClasses.append(itemTypeClasses.size(), itemClz);
        }
    }

}