package net.kmfish.multitypelistviewadapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijun on 16/8/8.
 */
public final class ClassListItemFactory implements ListItemFactory<Class> {

    private final Map<Class<?>, Class<? extends ListItem>> dataItemMap = new HashMap<>();
    private final Map<Class<?>, Object> dataItemAttachInfoMap = new HashMap<>();


    @Override
    public final ListItem create(Class dataType) {
        ListItem item = null;
        Class<? extends ListItem> itemClz = null;

        // the dataType maybe is a sub class extends the key class(or implements interface) of dataItemMap
        for (Map.Entry<Class<?>, Class<? extends ListItem>> entry : dataItemMap.entrySet()) {
            if (entry.getKey().isAssignableFrom(dataType)) {
                itemClz = entry.getValue();
            }
        }

        if (null != itemClz) {
            try {
                item = itemClz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (item != null) {
            item.setAttachInfo(dataItemAttachInfoMap.get(dataType));
        } else {
            System.err.println("Can not create ListItem.");
        }

        return item;
    }

    @Override
    public final void registerDataType(Class dataType, Class<? extends ListItem> itemClz) {
        dataItemMap.put(dataType, itemClz);
    }

    @Override
    public void registerDataClickListener(Class dataType, Object attachInfo) {
        dataItemAttachInfoMap.put(dataType, attachInfo);
    }

    @Override
    public int getTypeCount() {
        return dataItemMap.size();
    }
}
