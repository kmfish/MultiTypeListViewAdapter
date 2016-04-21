# MultiTypeListViewAdapter Android ListView 多type的Adapter封装

## [MultiTypeListViewAdapter](https://github.com/kmfish/MultiTypeListViewAdapter) 是什么？
MultiTypeListViewAdapter，顾名思义。其封装了多type下的Adapter的编程模式，通过对每种type统一接口，利用多态的方式，将type的实现从Adapter中抽离出来。Adapter只需面向统一接口，所以可以提供一个通用实现，实现代码不再变动。而会变动的每个type对应的item实现，则由使用者自己去实现。对扩展开放，对修改封闭。

同时，由于每个type的item均被抽离出来了。相当于复用的粒度为每个type item，可以根据需要，动态地选择合适的item去添加到adapter中。提高了代码复用，每个人编写维护好自己的item即可，避免了多人合作时都去修改Adapter，容易造成冲突。

另外，由于ViewHolder 模式的规范，MultiTypeListViewAdapter也同时封装了ViewHolder模式。


## 使用
```java
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private MultiTypeArrayAdapter adapter;

    private static final int ITEM_TYPE_1 = 0;
    private static final int ITEM_TYPE_2 = 1;

    private static final int ITEM_TYPE_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);
        adapter = new MultiTypeArrayAdapter(ITEM_TYPE_COUNT);
        listView.setAdapter(adapter);

        setupAdapter();
    }

    private void setupAdapter() {
        adapter.setTypeCount(ITEM_TYPE_COUNT);
        LineListItem1 item1 = new LineListItem1(this, ITEM_TYPE_1, "line type 1");
        LineListItem2 item2 = new LineListItem2(this, ITEM_TYPE_2, "line type 2");

        adapter.setNotifyOnChange(false);
        for (int i = 0, len = 50; i < len; i++) {
            adapter.addItem( i  % 2 == 0 ? item1 : item2);
        }
        adapter.notifyDataSetChanged();
    }

}

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

public class LineListItem2 extends BaseListItem {

    private String line;

    public LineListItem2(Context mContext, int viewType, String line) {
        super(mContext, viewType);
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
```

可以看到，使用MultiTypeListViewAdapter之后，实现多type的Adapter变得相当简单了。再也不用面对一堆判断itemType的if/else了。每增加一种type，只需增加一种新的ListItem即可，再也不用去改动Adapter的代码了。

项目发布在github上了，[MultiTypeListViewAdapter](https://github.com/kmfish/MultiTypeListViewAdapter)，欢迎fork和交流。




