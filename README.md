# MultiTypeListViewAdapter： Android ListView Adapter 封装

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
2. 支持多种类型Item同时使用.
3. 支持同时存在多种Model类型,由具体的Item确定. Model支持子类和接口实现。
4. 支持ListView, RecyclerView

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
2. 在用到的module中添加依赖，最新版本查看 [Release](https://github.com/kmfish/MultiTypeListViewAdapter/releases)
```
dependencies {
    compile 'com.github.kmfish:MultiTypeListViewAdapter:0.1.0'
}
```


## Example
- [For ListView](https://github.com/kmfish/MultiTypeListViewAdapter/blob/master/app/src/main/java/net/kmfish/sample/ListViewActivity.java)
- [For RecyclerView](https://github.com/kmfish/MultiTypeListViewAdapter/blob/master/app/src/main/java/net/kmfish/sample/RecyclerViewActivity.java)


## 参考
部分实现参考了 [CommonAdapter](https://github.com/tianzhijiexian/CommonAdapter),
,由于考虑到多type的场景下,必然data的类型也是多种的,所以支持了不同类型的data.另外item也可以持有当前显示的data对象,这样方便实现点击事件之后的需求. 同时提供了ArrayAdapter的类似接口,方便数据的增减.




