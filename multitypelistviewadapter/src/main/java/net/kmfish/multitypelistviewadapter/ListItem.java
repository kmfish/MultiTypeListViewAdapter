package net.kmfish.multitypelistviewadapter;

import android.view.ViewGroup;

/**
 * Created by kmfish on 2015/9/9
 */
public interface ListItem<T> {

    ViewHolder createViewHolder(ViewGroup group);

    void updateHolder(ViewHolder holder, int pos);

    boolean isEnabled();

    boolean isSelected();

    T getData();

    void setData(T data);
}
