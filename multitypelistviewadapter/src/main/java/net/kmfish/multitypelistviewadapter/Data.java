package net.kmfish.multitypelistviewadapter;

/**
 * Created by lijun on 16/8/5.
 */
public class Data<T> {

    private T data;

    private boolean isEnable;

    private Data() {

    }

    public static <E> Data<E> create(E t) {
        Data data = new Data();
        data.setData(t);
        return data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
