package net.kmfish.multitypelistviewadapter

import android.view.View

/**
 * Created by kmfish on 2015/9/9
 */
abstract class ViewHolder(val itemView: View?) {

    init {
        if (itemView == null) {
            throw IllegalArgumentException("itemView may not be null")
        }
    }
}
