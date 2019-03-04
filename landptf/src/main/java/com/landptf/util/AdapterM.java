package com.landptf.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author landptf
 * 公共的Adapter List适配器
 */
public abstract class AdapterM<T> extends BaseAdapter {

	private Context context;
	//为丰富程序功能，提供了两种常见的数据类型
	private List<T> dataList = null;//数据源List<T>
	private T[] dataArray = null;//数据源T[]
	//布局文件ID
	private int layoutId;
	
	/**
	 * 构造方法
	 * @param context
	 * @param layoutId
	 * @param dataList
	 */
	public AdapterM(Context context, int layoutId, List<T> dataList) {
		this.context = context;
		this.dataList = dataList;
		this.layoutId = layoutId;
	}
	
	/**
	 * 构造方法(与上一个只有数据源不同)  
	 * @param context
	 * @param layoutId
	 * @param dataArray
	 */
	public AdapterM(Context context, int layoutId, T[] dataArray) {
		this.context = context;
		this.dataArray = dataArray;
		this.layoutId = layoutId;
	}
	
	@Override
	public int getCount() {
		if (dataList != null) {
			return dataList.size();
		}else {
			return dataArray.length;
		}
	}
	@Override
	public T getItem(int position) {
		if (dataList != null) {
			if (dataList.size() <= position){
				return null;
			}
			return dataList.get(position);
		}else {
			if (dataArray.length <= position){
				return null;
			}
			return dataArray[position];
		}
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolderM holder = new ViewHolderM(context, convertView, parent,layoutId, position);
		convert(holder,getItem(position));
		return holder.getConvertView();
	}
	/**
	 * 需实现的抽象方法
	 * @param holder
	 * @param model
	 */
	public abstract void convert(ViewHolderM holder, T model);
}
