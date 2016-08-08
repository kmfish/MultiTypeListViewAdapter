package net.kmfish.multitypelistviewadapter;

import android.content.Context;

/**
 * Created by kmfish on 2015/9/9.
 */
public abstract class BaseListItem<T> implements ListItem<T> {

    protected Context mContext;

    protected T data;

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
}
