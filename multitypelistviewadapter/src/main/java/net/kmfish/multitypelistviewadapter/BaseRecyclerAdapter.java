package net.kmfish.multitypelistviewadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lijun on 16/8/2.
 */
public class BaseRecyclerAdapter extends RecyclerView.Adapter implements IArrayAdapter, NotifyDataSetChangedListener {

    private static final String TAG = BaseRecyclerAdapter.class.getSimpleName();

    private BaseArrayAdapter arrayAdapter;

    private LayoutInflater mInflater;

    private ListItemFactory itemFactory = new ClassListItemFactory();

    public BaseRecyclerAdapter() {
        init();
    }

    private void init() {
        this.arrayAdapter = new BaseArrayAdapter(this);
    }

    /**
     * 初始化Adapter时调用此方法注册数据类型和Item类型的映射关系
     * @param dataClz
     * @param itemClz
     */
    public void registerDataAndItem(Class<?> dataClz, Class<? extends ListItem> itemClz) {
        itemFactory.registerDataType(dataClz, itemClz);
    }

    private ListItem createListItem(Class<?> dataClz) {
        ListItem item = itemFactory.create(dataClz);
        return item;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (null == mInflater) {
            mInflater = LayoutInflater.from(parent.getContext());
        }

        Class<? extends Data> dataClz = arrayAdapter.getDataClass(viewType);
        if (null == dataClz) {
            return null;
        }

        ListItem item = createListItem(dataClz);
        if (null == item) {
            return null;
        }

        item.setContext(parent.getContext());
        InnerViewHolder holder = new InnerViewHolder(parent, item);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListItem item = ((InnerViewHolder)holder).item;
        if (null != item) {
            Data data = arrayAdapter.getData(position);
            if (null != data) {
                item.setData(data.getData());
                item.updateView(data.getData(), position);
            } else {
                throw new RuntimeException("item data should not be null, pos:" + position);
            }
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
    public void addData(Data item) {
        arrayAdapter.addData(item);
    }

    @Override
    public void addDatas(List<Data> items) {
        arrayAdapter.addDatas(items);
    }

    @Override
    public void addDatas(Data... items) {
        arrayAdapter.addDatas(items);
    }

    @Override
    public void insert(Data item, int index) {
        arrayAdapter.insert(item, index);
    }

    @Override
    public void remove(Data object) {
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
    public Data getData(int position) {
        return arrayAdapter.getData(position);
    }

    @Override
    public void setNotifyOnChange(boolean notifyOnChange) {
        arrayAdapter.setNotifyOnChange(notifyOnChange);
    }

    private class InnerViewHolder extends RecyclerView.ViewHolder {

        ListItem item;

        public InnerViewHolder(ViewGroup parent, ListItem item) {
            super(mInflater.inflate(item.onGetLayoutRes(), parent, false));
            this.item = item;
            this.item.bindViews(itemView);
        }
    }
}
