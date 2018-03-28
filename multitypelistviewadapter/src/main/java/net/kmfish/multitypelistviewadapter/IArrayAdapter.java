package net.kmfish.multitypelistviewadapter;

import java.util.List;

/**
 * Created by lijun on 16/8/5.
 */
public interface IArrayAdapter {

    void addData(Data item);

    void addDatas(List<Data> items);

    void addDatas(Data... items);

    void insert(Data item, int index);

    void remove(Data object);

    void remove(int pos);

    void clear();

    List<Data> getDatas();

    void setNotifyOnChange(boolean notifyOnChange);

    void notifyDataSetChanged();

    Data getData(int position);

    int getItemType(int position);

    int getCount();
}
