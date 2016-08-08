package net.kmfish.multitypelistviewadapter;

/**
 * Created by lijun on 16/8/8.
 * 维护注册的数据类型和item类型的对应关系,可以根据数据类型来创建item的实例.
 */
public interface ListItemFactory<T> {

    ListItem create(T dataType);

    void registerDataType(T dataType, Class<? extends ListItem> itemClz);

}
