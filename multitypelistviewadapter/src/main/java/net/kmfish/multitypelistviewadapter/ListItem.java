package net.kmfish.multitypelistviewadapter;

import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * Created by kmfish on 2015/9/9
 */
public interface ListItem<T> {

    @LayoutRes
    int onGetLayoutRes();

    void bindViews(View convertView);

    void updateView(T data, int pos);

    boolean isEnabled();

    boolean isSelected();

    T getData();

    void setData(T data);
}
