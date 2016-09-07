package net.kmfish.multitypelistviewadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * Created by kmfish on 2015/9/9
 * 列表的每一行的抽象接口
 */
public interface ListItem<T, L> {

    void setContext(Context context);

    /**
     * 返回当前item对应的layout
     * @return
     */
    @LayoutRes
    int onGetLayoutRes();

    /**
     * 该方法仅会回调一次,可以在这里做findViewById(), 已经添加OnClickListener
     * @param convertView
     */
    void bindViews(View convertView);

    /**
     * 每次item刷新时均会回调,此时 item的getData() 可以获取到当前pos对应的data.
     * @param data
     * @param pos
     */
    void updateView(T data, int pos);

    /**
     * 返回当前item绑定的data
     * @return
     */
    T getData();

    /**
     * 绑定data到item,此方法会由adapter在getView时调用,动态绑定. 使用者一般情况下不应调用此方法.
     * @param data
     */
    void setData(T data);

    void setAttachInfo(L obj);

}
