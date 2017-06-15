package net.kmfish.multitypelistviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

/**
 * Created by kmfish on 2015/9/9
 */
class BaseListAdapter : BaseAdapter(), NotifyDataSetChangedListener, IArrayAdapter {

    private lateinit var arrayAdapter: BaseArrayAdapter

    private var mInflater: LayoutInflater? = null

    private val itemFactory = ClassListItemFactory()

    init {
        init()
    }

    private fun init() {
        arrayAdapter = BaseArrayAdapter(this)
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
    fun <ModelType, AttachType : Any> registerDataAndItem(dataClz: Class<ModelType>,
                                                    itemClz: Class<out ListItem<ModelType, AttachType>>) {
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
    fun <ModelType, AttachType : Any> registerDataAndItem(dataClz: Class<ModelType>,
                                                    itemClz: Class<out ListItem<ModelType, AttachType>>,
                                                    attachInfo: AttachType) {
        itemFactory.registerDataType(dataClz, itemClz)
        itemFactory.registerDataClickListener(dataClz, attachInfo)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var cvtView = convertView
        if (null == mInflater) {
            mInflater = LayoutInflater.from(parent.context)
        }

        val data = getData(position)
        if (null == data || null == data.data) {
            throw RuntimeException("item data should not be null, pos:" + position)
        }

        val dataInner = data.data
        val rawClz = dataInner.javaClass
        if (null == cvtView) {
            val item = createListItem(rawClz)
            if (null != item) {
                cvtView = mInflater!!.inflate(item.onGetLayoutRes(), parent, false)
                cvtView!!.tag = item
                item.setContext(parent.context)
                item.bindViews(cvtView)

                item.data = dataInner
                item.updateView(dataInner, position)
            }
        } else {
            val item = castListItem(cvtView.tag, rawClz)
            item.data = dataInner
            item.updateView(dataInner, position)
        }

        return cvtView
    }

    private fun <T> castListItem(viewTag: Any?, dataClz: Class<T>): ListItem<T, *> {
        val item = viewTag as ListItem<T, *>
        return item
    }

    private fun <T> createListItem(dataClz: Class<T>): ListItem<T, *>? {
        return itemFactory.create(dataClz)
    }

    override fun isEnabled(position: Int): Boolean {
        val item = getItem(position)
        if (null != item) {
            return item.isEnable
        }

        return super.isEnabled(position)
    }

    override fun getViewTypeCount(): Int {
        val count = itemFactory.typeCount
        if (0 == count) {
            throw RuntimeException(
                    "registerDataAndItem() should be called before ListView.setAdapter(adapter)!")
        }

        return itemFactory.typeCount
    }

    override fun getItemViewType(position: Int): Int {
        return arrayAdapter.getItemType(position)
    }

    override fun getCount(): Int {
        return arrayAdapter.count
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Data<*>? {
        return arrayAdapter.getData(position)
    }

    override fun getData(position: Int): Data<*>? {
        return arrayAdapter.getData(position)
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

    override fun setNotifyOnChange(notifyOnChange: Boolean) {
        arrayAdapter.setNotifyOnChange(notifyOnChange)
    }

    companion object {

        val TAG = BaseListAdapter::class.java.simpleName
    }
}