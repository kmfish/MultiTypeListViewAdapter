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

## Install
1. 在工程根目录的build.gradle中添加JitPack仓库地址 (注意:是在allprojects节点下)
```
allprojects {
 repositories {
    jcenter()
    maven { url "https://jitpack.io" }
 }
}
```
2. 在用到的module中添加依赖
```
dependencies {
    compile 'com.github.kmfish:MultiTypeListViewAdapter:0.1.0'
}
```


## 使用
```java
public class ListViewActivity extends Activity {

    private ListView listView;
    private BaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById
        (R.id.listview);
        adapter = new BaseListAdapter();
        adapter.registerDataAndItem(TextModel.class, LineListItem1.class, item1ClickListener);
        adapter.registerDataAndItem(ImageModel.class, LineListItem2.class);

        listView.setAdapter(adapter);
        setupAdapter(adapter);
    }

    private void setupAdapter(BaseListAdapter adapter) {

        adapter.setNotifyOnChange(false);
        for (int i = 0, len = 30; i < len; i++) {
            adapter.addData( i  % 2 == 0 ? Data.create(getTextModel(i)) : Data.create(getImageModel(i)));
        }
        adapter.notifyDataSetChanged();
    }

    private TextModel getTextModel(int i) {
        return new TextModel("The name:" + i, "This is a desc text.");
    }

    private ImageModel getImageModel(int i) {
        return new ImageModel("github:" + i, R.drawable.icon_git);
    }

   private LineListItem1.OnItem1ClickListener item1ClickListener = new LineListItem1.OnItem1ClickListener() {
        @Override
        public void onNameClick(TextModel model) {
            Log.d(TAG, "onNameClick() called with: " + "model = [" + model + "]");
        }

        @Override
        public void onDescClick(TextModel model) {
            Log.d(TAG, "onDescClick() called with: " + "model = [" + model + "]");
        }
   };
}

public class LineListItem1 extends BaseListItem<TextModel, LineListItem1.OnItem1ClickListener> {

    TextView tvName;
    TextView tvDesc;

    @Override
    public int onGetLayoutRes() {
        return R.layout.list_item1;
    }

    @Override
    public void bindViews(View convertView) {
        Log.d("item1", "bindViews:" + convertView);
        tvName = (TextView) convertView.findViewById(R.id.text_name);
        tvDesc = (TextView) convertView.findViewById(R.id.text_desc);

        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != attachInfo) {
                    attachInfo.onNameClick(getData());
                }
            }
        });
        tvDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != attachInfo) {
                    attachInfo.onDescClick(getData());
                }
            }
        });

    }

    @Override
    public void updateView(TextModel model, int pos) {
        if (null != model) {
            Log.d("item1", "updateView model:" + model + "pos:" + pos);
            tvName.setText(model.getName());
            tvDesc.setText(model.getDesc());
        }
    }

    public interface OnItem1ClickListener {
        void onNameClick(TextModel model);
        void onDescClick(TextModel model);
    }
}
```

项目发布在github上了，欢迎fork和交流。

部分实现参考了 [CommonAdapter](https://github.com/tianzhijiexian/CommonAdapter),
,由于考虑到多type的场景下,必然data的类型也是多种的,所以支持了不同类型的data.另外item也可以持有当前显示的data对象,这样方便实现点击事件之后的需求. 同时提供了ArrayAdapter的类似接口,方便数据的增减.




