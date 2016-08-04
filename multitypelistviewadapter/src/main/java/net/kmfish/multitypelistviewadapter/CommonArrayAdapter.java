package net.kmfish.multitypelistviewadapter;

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

    private ListItemTypeHelper helper = new ListItemTypeHelper();
    private NotifyDataSetChangedListener listener;

    public CommonArrayAdapter(NotifyDataSetChangedListener listener) {
        this.listener = listener;
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
            for (ListItem item : items) {
            }
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void addItems(ListItem... items) {
        synchronized (mLock) {
            Collections.addAll(this.items, items);
            for (int i = 0; i < items.length; i++) {
            }
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

    @Override
    public void remove(int pos) {
        synchronized (mLock) {
            items.remove(pos);
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

    public List<ListItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Class<? extends ListItem> getItemClass(int viewType) {
        return helper.getItemClass(viewType);
    }

    public int getItemType(int position) {
        ListItem item = getItem(position);
        if (null != item) {
            return getItemType(item);
        }

        return -1;
    }

    public int getItemType(ListItem item) {
       return helper.getType(item.getClass());
    }
}
