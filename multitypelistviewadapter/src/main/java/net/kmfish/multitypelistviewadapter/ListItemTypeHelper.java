package net.kmfish.multitypelistviewadapter;

import android.support.v4.util.SparseArrayCompat;

/**
 * Created by lijun on 16/8/4.
 */
public class ListItemTypeHelper {

    private SparseArrayCompat<Class> itemTypeClasses = new SparseArrayCompat<>();

    public int getType(Class clz) {
        int index = itemTypeClasses.indexOfValue(clz);
        if (index == -1) {
            index = itemTypeClasses.size();
            itemTypeClasses.put(index, clz);
        }

        return index;
    }

    public Class getItemClass(int type) {
        return itemTypeClasses.get(type);
    }
}
