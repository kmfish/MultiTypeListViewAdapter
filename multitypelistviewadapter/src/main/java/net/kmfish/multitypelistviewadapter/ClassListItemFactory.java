package net.kmfish.multitypelistviewadapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijun on 16/8/8.
 */
public final class ClassListItemFactory implements ListItemFactory<Class> {

    private final Map<Class<?>, Class<? extends ListItem>> dataItemMap = new HashMap<>();


    @Override
    public final ListItem create(Class dataType) {
        ListItem item = null;
        Class<? extends ListItem> itemClz = dataItemMap.get(dataType);
        if (null != itemClz) {
            try {
                item = itemClz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return item;
    }

    @Override
    public final void registerDataType(Class dataType, Class<? extends ListItem> itemClz) {
        dataItemMap.put(dataType, itemClz);
    }

    @Override
    public int getTypeCount() {
        return dataItemMap.size();
    }
}
