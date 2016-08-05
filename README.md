# MultiTypeListViewAdapter Android ListView Adapter 封装

## [MultiTypeListViewAdapter](https://github.com/kmfish/MultiTypeListViewAdapter)

[![](https://jitpack.io/v/kmfish/MultiTypeListViewAdapter.svg)](https://jitpack.io/#kmfish/MultiTypeListViewAdapter)

## 背景
- Adapter实现多type的模式,代码不便于维护
- 希望针对Item粒度的复用
- ViewHolder的统一封装

## 封装思路
抽象出独立的ListItem,实现通用的BaseListAdapter/BaseRecyclerAdapter,支持多type,支持ViewHolder

## 特点
1. 基于ListItem 的复用,item的数据和代码更加内聚,提高可维护性.
2. 支持多种类型Item,以item的class自动计算item type.
3. 支持同时存在多种Model类型,由具体的Item确定.
4. 支持ListView, RecyclerView的Adapter

## 使用
```java
public class ListViewActivity extends Activity {

    private ListView listView;
    private BaseListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.listview);
        adapter = new BaseListAdapter(2);
        listView.setAdapter(adapter);
        setupAdapter();
    }

    private void setupAdapter() {
        LineListItem1 item1 = new LineListItem1(this, getTextModel());
        LineListItem2 item2 = new LineListItem2(this, getImageModel());

        adapter.setNotifyOnChange(false);
        for (int i = 0, len = 15; i < len; i++) {
            adapter.addItem( i  % 2 == 0 ? item1 : item2);
        }
        adapter.notifyDataSetChanged();
    }
}

public class LineListItem1 extends BaseListItem<TextModel> {

    TextView tvName;
    TextView tvDesc;

    public LineListItem1(Context mContext, TextModel model) {
        super(mContext, model);
    }

    @Override
    public int onGetLayoutRes() {
        return R.layout.list_item1;
    }

    @Override
    public void bindViews(View convertView) {
        tvName = (TextView) convertView.findViewById(R.id.text_name);
        tvDesc = (TextView) convertView.findViewById(R.id.text_desc);
    }

    @Override
    public void updateView(TextModel model, int pos) {
        if (null != model) {
            tvName.setText(model.getName());
            tvDesc.setText(model.getDesc());
        }
    }
}
```

项目发布在github上了，欢迎fork和交流。

部分实现参考了 [CommonAdapter](https://github.com/tianzhijiexian/CommonAdapter), 它的思路是数据和view分离,由adapter居中来组织.
我封装的思路是以Item为中心,组织数据和视图,使用起来代码更简洁些.同时提供了ArrayAdapter的类似接口,方便数据的增减.




