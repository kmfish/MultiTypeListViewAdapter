package net.kmfish.multitypelistviewadapter

/**
 * Created by lijun on 16/8/5.
 */
interface IArrayAdapter {

    fun addData(item: Data<*>)

    fun addDatas(items: List<Data<*>>)

    fun addDatas(vararg items: Data<*>)

    fun insert(item: Data<*>, index: Int)

    fun remove(item: Data<*>)

    fun remove(pos: Int)

    fun clear()

    fun setNotifyOnChange(notifyOnChange: Boolean)

    fun notifyDataSetChanged()

    fun getData(position: Int): Data<*>?
}
