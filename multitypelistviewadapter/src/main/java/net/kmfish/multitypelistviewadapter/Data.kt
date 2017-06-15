package net.kmfish.multitypelistviewadapter

/**
 * Created by lijun on 16/8/5.
 */
class Data<out T> constructor(val data: T, val isEnable: Boolean = false) {

    companion object {

        fun <E: Any> create(t: E): Data<E> {
            val data = Data(t)
            return data
        }
    }
}
