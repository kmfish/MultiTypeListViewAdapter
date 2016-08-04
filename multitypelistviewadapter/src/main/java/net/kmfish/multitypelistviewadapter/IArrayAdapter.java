package net.kmfish.multitypelistviewadapter;

import java.util.List;

/**
 * Created by lijun on 16/8/3.
 */
public interface IArrayAdapter {

    void addItem(ListItem item);

    void addItems(List<ListItem> items);

    void addItems(ListItem... items);

    void insert(ListItem item, int index);

    void remove(ListItem object);

    void remove(int pos);

    void clear();

    void setNotifyOnChange(boolean notifyOnChange);

    void notifyDataSetChanged();

    ListItem getItem(int position);
}
