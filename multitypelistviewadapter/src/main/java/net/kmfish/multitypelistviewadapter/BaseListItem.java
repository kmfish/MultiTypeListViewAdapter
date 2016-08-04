package net.kmfish.multitypelistviewadapter;

import android.content.Context;

/**
 * Created by kmfish on 2015/9/9.
 */
public abstract class BaseListItem<T> implements ListItem<T> {

    protected Context mContext;
    protected boolean isSelected;
    protected boolean isEnabled;

    private T data;

    public BaseListItem(Context mContext, T data) {
        this.mContext = mContext;
        this.data = data;
    }

    protected Context getContext() {
        return mContext;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }
}
