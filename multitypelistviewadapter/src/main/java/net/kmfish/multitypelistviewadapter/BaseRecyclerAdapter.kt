package net.kmfish.multitypelistviewadapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by lijun on 16/8/2.
 */
class BaseRecyclerAdapter : RecyclerView.Adapter<BaseRecyclerAdapter.InnerViewHolder<*>>(), IArrayAdapter, NotifyDataSetChangedListener {

    private lateinit var arrayAdapter: BaseArrayAdapter

    private var mInflater: LayoutInflater? = null

    private val itemFactory = ClassListItemFactory()

    init {
        init()
    }

    private fun init() {
        this.arrayAdapter = BaseArrayAdapter(this)
    }

    /**
     * 初始化Adapter时调用此方法注册数据类型和Item类型的映射关系
     * 注意: 需要在setAdapter之前调用!!
     * @param dataClz model class
     * *
     * @param itemClz ListItem class
     * *
     * @param <ModelType> model类型
     * *
     * @param <AttachType> 附加对象类型
    </AttachType></ModelType> */
    fun <ModelType, I : ListItem<*, *>> registerDataAndItem(dataClz: Class<ModelType>,
                                              itemClz: Class<out I>) {
        itemFactory.registerDataType(dataClz, itemClz)
    }

    /**
     * 初始化Adapter时调用此方法注册数据类型和Item类型的映射关系
     * 注意: 需要在setAdapter之前调用!!
     * @param dataClz model class
     * *
     * @param itemClz ListItem class
     * *
     * @param attachInfo 附加对象
     * *
     * @param <ModelType> model类型
     * *
     * @param <AttachType> 附加对象类型
    </AttachType></ModelType> */
    fun <ModelType, I> registerDataAndItem(dataClz: Class<ModelType>,
                                        itemClz: Class<out ListItem<ModelType, I>>,
                                        attachInfo: I) {
        itemFactory.registerDataType(dataClz, itemClz)
        itemFactory.registerDataClickListener(dataClz, attachInfo)
    }

    private fun <T> createListItem(dataClz: Class<T>): ListItem<T, *>? {
        val item = itemFactory.create(dataClz)
        return item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder<*>? {
        if (null == mInflater) {
            mInflater = LayoutInflater.from(parent.context)
        }

        val dataClz = arrayAdapter.getDataClass(viewType)

        val item = createListItem(dataClz)

        item?.setContext(parent.context)
        val holder = item?.let { InnerViewHolder(parent, it) }
        return holder
    }

    override fun onBindViewHolder(holder: InnerViewHolder<*>, position: Int) {
        val data = arrayAdapter.getData(position)

        if (null != data && null != data.data) {
            val dataClz = data.data.javaClass
            val item = castListItem(holder.item, dataClz)
            item.data = data.data
            item.updateView(data.data, position)
        } else {
            throw RuntimeException("item data should not be null, pos:" + position)
        }
    }

    private fun <T> castListItem(item: ListItem<*, *>, dataClz: Class<T>): ListItem<T, *> {
        val item = item as ListItem<T, *>
        return item
    }

    override fun getItemViewType(position: Int): Int {
        return arrayAdapter.getItemType(position)
    }

    override fun getItemCount(): Int {
        return arrayAdapter.count
    }

    override fun addData(item: Data<*>) {
        arrayAdapter.addData(item)
    }

    override fun addDatas(items: List<Data<*>>) {
        arrayAdapter.addDatas(items)
    }

    override fun addDatas(vararg items: Data<*>) {
        arrayAdapter.addDatas(*items)
    }

    override fun insert(item: Data<*>, index: Int) {
        arrayAdapter.insert(item, index)
    }

    override fun remove(`object`: Data<*>) {
        arrayAdapter.remove(`object`)
    }

    override fun remove(pos: Int) {
        arrayAdapter.remove(pos)
    }

    override fun clear() {
        arrayAdapter.clear()
    }

    override fun getData(position: Int): Data<*>? {
        return arrayAdapter.getData(position)
    }

    override fun setNotifyOnChange(notifyOnChange: Boolean) {
        arrayAdapter.setNotifyOnChange(notifyOnChange)
    }

    inner class InnerViewHolder<out T>(parent: ViewGroup, internal val item: ListItem<out T, *>) : RecyclerView.ViewHolder(
            inflateItemView(item, parent)) {
        init {
            this.item.bindViews(itemView)
        }
    }

    fun inflateItemView(item: ListItem<*, *>, parent: ViewGroup): View? {
        if (null != mInflater) {
            return mInflater!!.inflate(item.onGetLayoutRes(), parent, false)
        }

        return null
    }

    companion object {

        private val TAG = BaseRecyclerAdapter::class.java.simpleName
    }
}
