package net.kmfish.multitypelistviewadapter

import java.util.*

/**
 * Created by lijun on 16/8/5.
 */
class BaseArrayAdapter(private val listener: NotifyDataSetChangedListener?) : IArrayAdapter {

    private var mNotifyOnChange = true
    private val mLock = Any()
    private val datas = ArrayList<Data<*>>()

    private val helper = ListItemTypeHelper()

    override fun addData(item: Data<*>) {
        synchronized(mLock) {
            datas.add(item)
        }
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    override fun addDatas(items: List<Data<*>>) {
        synchronized(mLock) {
            this.datas.addAll(items)
        }
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    override fun addDatas(vararg items: Data<*>) {
        synchronized(mLock) {
            Collections.addAll<Data<*>>(this.datas, *items)
        }
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    override fun insert(item: Data<*>, index: Int) {
        synchronized(mLock) {
            datas.add(index, item)
        }
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    override fun remove(`object`: Data<*>) {
        synchronized(mLock) {
            if (datas.contains(`object`)) {
                datas.remove(`object`)
            }
        }
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    override fun remove(pos: Int) {
        synchronized(mLock) {
            datas.removeAt(pos)
        }
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    override fun clear() {
        synchronized(mLock) {
            datas.clear()
        }
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    override fun notifyDataSetChanged() {
        mNotifyOnChange = true
        listener?.notifyDataSetChanged()
    }

    override fun setNotifyOnChange(notifyOnChange: Boolean) {
        mNotifyOnChange = notifyOnChange
    }

    val count: Int
        get() = datas.size

    override fun getData(position: Int): Data<*>? {
        if (position >= 0 && position < datas.size) {
            return datas[position]
        }

        return null
    }

    val items: List<Data<*>>
        get() = Collections.unmodifiableList<Data<*>>(datas)

    fun getDataClass(viewType: Int): Class<*> {
        val clz = helper.getItemClass(viewType)
        return clz
    }

    fun getItemType(position: Int): Int {
        val item = getData(position)
        if (null != item) {
            return getItemType(item)
        }

        return -1
    }

    fun getItemType(data: Data<*>?): Int {
        if (null != data && null != data.data) {
            return helper.getType(data.data.javaClass)
        }

        return -1
    }

    companion object {

        val TAG = BaseArrayAdapter::class.java.simpleName
    }
}
