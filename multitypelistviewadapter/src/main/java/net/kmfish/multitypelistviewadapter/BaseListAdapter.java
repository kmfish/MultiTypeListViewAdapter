package net.kmfish.multitypelistviewadapter;

import java.util.List;

/**
 * Created by kmfish on 2015/9/9
 */
public class BaseListAdapter extends BaseMultiTypeAdapter implements CommonArrayAdapter.NotifyDataSetChangedListener {

    public static final String TAG = BaseListAdapter.class.getSimpleName();

    private CommonArrayAdapter arrayAdapter;

    private int typeCount;

    public BaseListAdapter(int typeCount) {
        arrayAdapter = new CommonArrayAdapter(this);
        this.typeCount = typeCount;
    }

    @Override
    public int getViewTypeCount() {
        return typeCount;
    }

    @Override
    public int getItemViewType(int position) {
        return arrayAdapter.getItemType(position);
    }

    @Override
    public int getCount() {
        return arrayAdapter.getCount();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ListItem getItem(int position) {
        return arrayAdapter.getItem(position);
    }

    @Override
    public void addItem(ListItem item) {
        arrayAdapter.addItem(item);
    }

    @Override
    public void addItems(List<ListItem> items) {
        arrayAdapter.addItems(items);
    }

    @Override
    public void addItems(ListItem... items) {
        arrayAdapter.addItems(items);
    }

    @Override
    public void insert(ListItem item, int index) {
        arrayAdapter.insert(item, index);
    }

    @Override
    public void remove(ListItem object) {
        arrayAdapter.remove(object);
    }

    @Override
    public void remove(int pos) {
        arrayAdapter.remove(pos);
    }

    @Override
    public void clear() {
        arrayAdapter.clear();
    }

    @Override
    public void setNotifyOnChange(boolean notifyOnChange) {
        arrayAdapter.setNotifyOnChange(notifyOnChange);
    }
}