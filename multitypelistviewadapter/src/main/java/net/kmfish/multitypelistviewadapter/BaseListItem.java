package net.kmfish.multitypelistviewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kmfish on 2015/9/9.
 */
public abstract class BaseListItem<T> implements ListItem<T> {

    protected Context mContext;
    protected boolean isSelected;

    private T data;

    public BaseListItem(Context mContext) {
        this.mContext = mContext;
    }

    protected Context getContext() {
        return mContext;
    }

    protected abstract ViewHolder onCreateViewHolder(View itemView);

    protected abstract int onGetItemLayoutRes();

    @Override
    public ViewHolder createViewHolder(ViewGroup group) {
        View view = LayoutInflater.from(getContext()).inflate(onGetItemLayoutRes(), group, false);
        return onCreateViewHolder(view);
    }

    @Override
    public boolean isEnabled() {
        return true;
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
