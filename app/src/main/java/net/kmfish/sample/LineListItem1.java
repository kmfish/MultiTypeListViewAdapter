package net.kmfish.sample;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import net.kmfish.multitypelistviewadapter.BaseListItem;
import net.kmfish.multitypelistviewadapter.ViewHolder;

/**
 * Created by kmfish on 2015/9/9.
 */
public class LineListItem1 extends BaseListItem {

    private String line;

    public LineListItem1(Context mContext, int viewType, String line) {
        super(mContext, viewType);
        this.line = line;
    }

    @Override
    protected int onGetItemLayoutRes() {
        return R.layout.list_item1;
    }

    @Override
    protected ViewHolder onCreateViewHolder(View itemView) {
        return new LineViewHolder(itemView);
    }

    @Override
    public void updateHolder(ViewHolder holder, int pos) {
        LineViewHolder h = (LineViewHolder) holder;
        h.text.setText(line + "_" + pos);
    }

    private class LineViewHolder extends ViewHolder {

        TextView text;

        public LineViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.line_text);
        }
    }
}
