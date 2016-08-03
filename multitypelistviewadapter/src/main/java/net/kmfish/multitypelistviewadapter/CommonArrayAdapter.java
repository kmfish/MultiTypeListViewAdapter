package net.kmfish.multitypelistviewadapter;

import android.support.v4.util.SparseArrayCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lijun on 16/8/2.
 */
public class CommonArrayAdapter implements IArrayAdapter {

    public static final String TAG = CommonArrayAdapter.class.getSimpleName();

    private boolean mNotifyOnChange = true;
    private final Object mLock = new Object();
    private List<ListItem> items = new ArrayList<>();
    private SparseArrayCompat<Class> itemTypeClasses = new SparseArrayCompat<>();

    private NotifyDataSetChangedListener listener;

    public void setListener(NotifyDataSetChangedListener listener) {
        this.listener = listener;
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

    public void notifyDataSetChanged() {
        mNotifyOnChange = true;
        if (null != listener) {
            listener.notifyDataSetChanged();
        }
    }

    public interface NotifyDataSetChangedListener {
        void notifyDataSetChanged();
    }

    public void setNotifyOnChange(boolean notifyOnChange) {
        mNotifyOnChange = notifyOnChange;
    }

    public int getCount() {
        return items.size();
    }

    public ListItem getItem(int position) {
        if (position >= 0 && position < items.size()) {
            return items.get(position);
        }

        return null;
    }

    @Override
    public int getTypeCount() {
        return itemTypeClasses.size();
    }

    public List<ListItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Class<? extends ListItem> getItemClass(int viewType) {
        return itemTypeClasses.get(viewType);
    }

    public int getItemType(int position) {
        ListItem item = getItem(position);
        if (null != item) {
            return getItemType(item);
        }

        return -1;
    }

    public int getItemType(ListItem item) {
        int type = itemTypeClasses.indexOfValue(item.getClass());
        return type;
    }

    private void addItemTypeClasses(Class itemClz) {
        if (itemTypeClasses.indexOfValue(itemClz) == -1) {
            itemTypeClasses.append(itemTypeClasses.size(), itemClz);
        }
    }
}
