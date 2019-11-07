package com.byron.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.byron.ui.R;

/**
 * 主活动页面布局适配
 * @author Byron
 * @date 2019/11/7
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

	private Context mContext;
	private int[] viewIcons;
	private String[] viewTxt;
	private RecyclerViewOnItemClickListener onItemClickListener;

	public RecyclerViewAdapter(Context context, int[] viewIcons, String[] viewTxt){
		mContext = context;
		this.viewIcons = viewIcons;
		this.viewTxt = viewTxt;
	}

	@Override
	public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (mContext == null){
			mContext = parent.getContext();
		}

		View view = LayoutInflater.from(mContext).inflate(R.layout.ui_recycler_view_item, parent, false);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(view.getLayoutParams());
		//列均分为4块
		lp.height = parent.getHeight() / 4;
		view.setLayoutParams(lp);

		if (onItemClickListener != null){

			/**
			 * 设置每一块的点击事件
			 * 调用RecyclerView接口的点击事件
			 */
			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					onItemClickListener.onClick(view, (Integer) view.getTag());
				}
			});
		}

		return new RecyclerViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerViewHolder holder, int position) {

		holder.itemView.setTag(position);
		int mViewIcons = viewIcons[position];
		String mViewTxt= viewTxt[position];

		holder.viewIcons.setBackgroundResource(mViewIcons);
		holder.viewTxt.setText(mViewTxt);

	}


	@Override
	public int getItemCount() {
		return viewIcons.length;
	}

	/**
	 * 需要继承RecyclerView的ViewHolder类
	 */
	static class RecyclerViewHolder extends RecyclerView.ViewHolder{

		ImageView viewIcons;
		TextView viewTxt;

		public RecyclerViewHolder(View itemView) {
			super(itemView);

			viewIcons = itemView.findViewById(R.id.ui_recycler_image);
			viewTxt = itemView.findViewById(R.id.ui_recycler_txt);
		}
	}

	public void setOnClickListener(RecyclerViewOnItemClickListener listener){
		onItemClickListener = listener;
	}
}
