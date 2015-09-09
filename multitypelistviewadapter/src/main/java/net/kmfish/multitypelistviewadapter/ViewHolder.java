package net.kmfish.multitypelistviewadapter;

import android.view.View;

/**
 * Created by kmfish on 2015/9/9
 */
public abstract class ViewHolder {

    public final View itemView;

    public ViewHolder(View itemView) {
        if (itemView == null) {
            throw new IllegalArgumentException("itemView may not be null");
        }
        this.itemView = itemView;
    }
}
