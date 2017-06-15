package net.kmfish.multitypelistviewadapter

import android.support.v4.util.SparseArrayCompat

/**
 * Created by lijun on 16/8/4.
 */
class ListItemTypeHelper {

    private val itemTypeClasses = SparseArrayCompat<Class<*>>()

    fun getType(clz: Class<*>): Int {
        var index = itemTypeClasses.indexOfValue(clz)
        if (index == -1) {
            index = itemTypeClasses.size()
            itemTypeClasses.put(index, clz)
        }

        return index
    }

    fun getItemClass(type: Int): Class<*> {
        return itemTypeClasses.get(type)
    }
}
