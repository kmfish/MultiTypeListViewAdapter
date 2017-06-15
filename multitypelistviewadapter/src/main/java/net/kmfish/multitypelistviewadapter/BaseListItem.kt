package net.kmfish.multitypelistviewadapter

import android.content.Context

/**
 * Created by kmfish on 2015/9/9.

 * Subclass extends this class must has a empty constructor method.
 */
abstract class BaseListItem<T, L> : ListItem<T, L> {

    protected lateinit var mContext: Context

    override var attachInfo: L? = null

    override var data: T? = null

    protected fun getContext(): Context {
        return mContext
    }

    override fun setContext(context: Context) {
        this.mContext = context
    }
}
