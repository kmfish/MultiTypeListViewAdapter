package net.kmfish.multitypelistviewadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by kmfish on 2015/9/9
 */
public class BaseListAdapter extends BaseAdapter implements NotifyDataSetChangedListener, IArrayAdapter {

    public static final String TAG = BaseListAdapter.class.getSimpleName();

    private BaseArrayAdapter arrayAdapter;

    private LayoutInflater mInflater;

    private ListItemFactory itemFactory = new ClassListItemFactory();

    public BaseListAdapter() {
        init();
    }

    private void init() {
        arrayAdapter = new BaseArrayAdapter(this);
    }

    /**
     * 初始化Adapter时调用此方法注册数据类型和Item类型的映射关系
     * 注意: 需要在setAdapter之前调用!!
     * @param dataClz model class
     * @param itemClz ListItem class
     * @param <ModelType> model类型
     * @param <AttachType> 附加对象类型
     */
    public <ModelType, AttachType> void registerDataAndItem(Class<ModelType> dataClz,
                                                            Class<? extends ListItem<ModelType, AttachType>> itemClz) {
        itemFactory.registerDataType(dataClz, itemClz);
    }

    /**
     * 初始化Adapter时调用此方法注册数据类型和Item类型的映射关系
     * 注意: 需要在setAdapter之前调用!!
     * @param dataClz model class
     * @param itemClz ListItem class
     * @param attachInfo 附加对象
     * @param <ModelType> model类型
     * @param <AttachType> 附加对象类型
     */
    public <ModelType, AttachType> void registerDataAndItem(Class<ModelType> dataClz,
                                                            Class<? extends ListItem<ModelType, AttachType>> itemClz,
                                                            AttachType attachInfo) {
        itemFactory.registerDataType(dataClz, itemClz);
        itemFactory.registerDataClickListener(dataClz, attachInfo);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == mInflater) {
            mInflater = LayoutInflater.from(parent.getContext());
        }

        Data data = getData(position);
        if (null == data || null == data.getData()) {
            throw new RuntimeException("item data should not be null, pos:" + position);
        }

        Class rawClz = data.getData().getClass();
        ListItem item;
        if (null == convertView) {
            item = createListItem(rawClz);
            if (null != item) {
                convertView = mInflater.inflate(item.onGetLayoutRes(), parent, false);
                convertView.setTag(item);
                item.setContext(parent.getContext());
                item.bindViews(convertView);
            }
        } else {
            item = (ListItem) convertView.getTag();
        }

        if (null != item) {
            item.setData(data.getData());
            item.updateView(data.getData(), position);
        }
        return convertView;
    }

    private ListItem createListItem(Class<?> dataClz) {
        ListItem item = itemFactory.create(dataClz);
        return item;
    }

    @Override
    public boolean isEnabled(int position) {
        Data item = getItem(position);
        if (null != item) {
            return item.isEnable();
        }

        return super.isEnabled(position);
    }

    @Override
    public int getViewTypeCount() {
        int count = itemFactory.getTypeCount();
        if (0 == count) {
            throw new RuntimeException(
                    "registerDataAndItem() should be called before ListView.setAdapter(adapter)!");
        }

        return itemFactory.getTypeCount();
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
    public Data getItem(int position) {
        return arrayAdapter.getData(position);
    }

    @Override
    public Data getData(int position) {
        return arrayAdapter.getData(position);
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
    public void setNotifyOnChange(boolean notifyOnChange) {
        arrayAdapter.setNotifyOnChange(notifyOnChange);
    }
}