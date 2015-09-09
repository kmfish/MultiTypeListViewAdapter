package net.kmfish.multitypelistviewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kmfish on 2015/9/9.
 */
public abstract class BaseListItem implements ListItem {

    protected Context mContext;
    protected int viewType;
    protected boolean isSelected;

    public BaseListItem(Context mContext) {
        this.mContext = mContext;
    }

    public BaseListItem(Context mContext, int viewType) {
        this.mContext = mContext;
        this.viewType = viewType;
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
    public int getViewType() {
        return viewType;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
