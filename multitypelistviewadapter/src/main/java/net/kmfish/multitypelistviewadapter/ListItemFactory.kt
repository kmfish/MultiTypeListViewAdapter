package net.kmfish.multitypelistviewadapter

/**
 * Created by lijun on 16/8/8.
 * 维护注册的数据类型和item类型的对应关系,可以根据数据类型来创建item的实例.
 */
interface ListItemFactory<in T> {

    fun <T> create(dataType: Class<T>): ListItem<T, Any>?

    fun registerDataType(dataType: T, itemClz: Class<out ListItem<*, *>>)

    fun registerDataClickListener(dataType: T, attachInfo: Any?)

    val typeCount: Int

}
