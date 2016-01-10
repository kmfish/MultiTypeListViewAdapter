package net.kmfish.sample;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.kmfish.multitypelistviewadapter.BaseListItem;
import net.kmfish.multitypelistviewadapter.ViewHolder;

/**
 * Created by kmfish on 2015/9/9.
 */
public class LineListItem2 extends BaseListItem {

    private String line;

    public LineListItem2(Context mContext, String line) {
        super(mContext);
        this.line = line;
    }

    @Override
    protected int onGetItemLayoutRes() {
        return R.layout.list_item2;
    }

    @Override
    protected ViewHolder onCreateViewHolder(View itemView) {
        return new LineViewHolder2(itemView);
    }

    @Override
    public void updateHolder(ViewHolder holder, int pos) {
        LineViewHolder2 h = (LineViewHolder2) holder;
        h.text.setText(line + "_" + pos);
        h.img.setImageResource(R.drawable.icon_git);
    }

    private class LineViewHolder2 extends ViewHolder {
        ImageView img;
        TextView text;

        public LineViewHolder2(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.thumb);
            text = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
