package net.kmfish.multitypelistviewadapter;

import android.content.Context;

/**
 * Created by kmfish on 2015/9/9.
 *
 * Subclass extends this class must has a empty constructor method.
 */
public abstract class BaseListItem<T, L> implements ListItem<T, L> {

    protected Context mContext;

    protected T data;

    protected L attachInfo;

    public BaseListItem() {
    }

    protected Context getContext() {
        return mContext;
    }

    @Override
    public void setContext(Context context) {
        this.mContext = context;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public void setAttachInfo(L obj) {
        attachInfo = obj;
    }
}
