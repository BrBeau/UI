package com.byron.ui.adapter;

import android.view.View;

/**
 * RecyclerView的点击响应接口
 * @author Byron
 * @date 2019/11/7
 */
public interface RecyclerViewOnItemClickListener {

	/**
	 * 点击事件
	 * @param view 视图
	 * @param position 点击的位置
	 */
	void onClick(View view, int position);
}
