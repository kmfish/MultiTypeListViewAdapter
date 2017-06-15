package net.kmfish.multitypelistviewadapter

import java.util.*

/**
 * Created by lijun on 16/8/8.
 */
class ClassListItemFactory : ListItemFactory<Class<*>> {

    private val dataItemMap = HashMap<Class<*>, Class<out ListItem<*, *>>>()
    private val dataItemAttachInfoMap = HashMap<Class<*>, Any>()

    override fun <T> create(dataType: Class<T>): ListItem<T, Any>? {
        var item: ListItem<T, Any>? = null
        var itemClz: Class<out ListItem<T, Any>>? = null

        // the dataType maybe is a sub class extends the key class(or implements interface) of dataItemMap
        for ((key, value) in dataItemMap) {
            if (key.isAssignableFrom(dataType)) {
                itemClz = value as Class<out ListItem<T, Any>>
            }
        }

        if (null != itemClz) {
            try {
                item = itemClz.newInstance()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

            if (item != null) {
                val attachInfo = dataItemAttachInfoMap[itemClz]
                if (null != attachInfo) {
                    item.attachInfo = attachInfo
                }
            } else {
                System.err.println("Can not create ListItem.")
            }
        }

        return item
    }

    override fun registerDataType(dataType: Class<*>, itemClz: Class<out ListItem<*, *>>) {
        dataItemMap.put(dataType, itemClz)
    }

    override fun registerDataClickListener(dataType: Class<*>, attachInfo: Any?) {
        if (attachInfo != null) {
            dataItemAttachInfoMap.put(dataType, attachInfo)
        }
    }

    override val typeCount: Int
        get() = dataItemMap.size
}
