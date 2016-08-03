package net.kmfish.multitypelistviewadapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lijun on 16/8/2.
 */
public class BaseRecyclerAdapter extends RecyclerView.Adapter implements
        RecyclerAdapter, IArrayAdapter, CommonArrayAdapter.NotifyDataSetChangedListener {

    private static final String TAG = BaseRecyclerAdapter.class.getSimpleName();

    private CommonArrayAdapter arrayAdapter;

    public BaseRecyclerAdapter() {
        this.arrayAdapter = new CommonArrayAdapter();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewHolder holder;

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
                }
            }
        }

        if (null == target) {
            throw new RuntimeException("list item is never null.");

        }

        holder = target.createViewHolder(parent);
        Log.d(TAG, "onCreateViewHolder viewType:" + viewType);

        return new InnerViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListItem item = arrayAdapter.getItem(position);
        if (null != item) {
            InnerViewHolder innerVH = (InnerViewHolder) holder;
            item.updateHolder(innerVH.rawVH, position);
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
    public int getTypeCount() {
        return arrayAdapter.getTypeCount();
    }

    @Override
    public ListItem getItem(int position) {
        return arrayAdapter.getItem(position);
    }

    private class InnerViewHolder extends RecyclerView.ViewHolder {

        ViewHolder rawVH;

        public InnerViewHolder( ViewHolder viewHolder) {
            super(viewHolder.itemView);
            this.rawVH = viewHolder;
        }
    }
}
