package net.kmfish.multitypelistviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lijun on 16/8/2.
 */
public class BaseRecyclerAdapter extends RecyclerView.Adapter implements IArrayAdapter, CommonArrayAdapter.NotifyDataSetChangedListener {

    private static final String TAG = BaseRecyclerAdapter.class.getSimpleName();

    private CommonArrayAdapter arrayAdapter;

    public BaseRecyclerAdapter() {
        this.arrayAdapter = new CommonArrayAdapter(this);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Class<? extends ListItem> itemClass = arrayAdapter.getItemClass(viewType);
        if (null == itemClass) {
            return null;
        }

        ListItem target = null;
        List<ListItem> items = arrayAdapter.getItems();
        if (null != items && items.size() > 0) {
            for (ListItem item : items) {
                if (item.getClass().equals(itemClass)) {
                    target = item;
                    break;
                }
            }
        }

        if (null == target) {
            throw new RuntimeException("list item is never null.");
        }

        Log.d(TAG, "onCreateViewHolder viewType:" + viewType);
        return new InnerViewHolder(parent.getContext(), parent, target);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListItem item = ((InnerViewHolder)holder).item;
        if (null != item) {
            item.updateView(item.getData(), position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return arrayAdapter.getItemType(position);
    }

    @Override
    public int getItemCount() {
        return arrayAdapter.getCount();
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
    public void clear() {
        arrayAdapter.clear();
    }

    @Override
    public ListItem getItem(int position) {
        return arrayAdapter.getItem(position);
    }

    @Override
    public void setNotifyOnChange(boolean notifyOnChange) {
        arrayAdapter.setNotifyOnChange(notifyOnChange);
    }

    private class InnerViewHolder extends RecyclerView.ViewHolder {

        ListItem item;

        public InnerViewHolder(Context context, ViewGroup parent, ListItem item) {
            super(LayoutInflater.from(context).inflate(item.onGetLayoutRes(), parent, false));
            this.item = item;
            this.item.bindViews(itemView);
        }
    }
}
