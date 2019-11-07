package com.byron.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.byron.ui.utils.ResourceUtil;

import java.util.List;
import java.util.Map;

/**
 * 支付布局适配器
 * @author Byron
 * @date 2019/11/5
 */
public class UiPayAdapter extends BaseAdapter {

	private Context context;
	private List<Map<String, Object>> payData;
	private int selectId;

	public UiPayAdapter(Context context, List<Map<String, Object>> payData){
		this.context = context;
		this.payData = payData;
	}

	@Override
	public int getCount() {
		return payData.size();
	}

	@Override
	public Object getItem(int position) {
		return payData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View view, ViewGroup viewGroup) {

		ViewHolder viewHolder;
		if (view == null){

			view = LayoutInflater.from(context).inflate(ResourceUtil.getLayoutId(context, "ui_pay_list_item"), null);
			viewHolder = new ViewHolder();
			viewHolder.payImage = view.findViewById(ResourceUtil.getId(context, "ui_pay_item_image"));
			viewHolder.payTxt =  view.findViewById(ResourceUtil.getId(context, "ui_pay_txt"));
			view.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) view.getTag();
		}

		RadioButton payRb2 = view.findViewById(ResourceUtil.getId(context, "ui_pay_rb"));
		viewHolder.payRb = payRb2;
		/**
		 * 获取数据中相对应的字段
		 * " " 内填入传进来data相应的key值
		 */
		viewHolder.payImage.setBackgroundResource((Integer) payData.get(position).get("payImage"));
		viewHolder.payTxt.setText((String)payData.get(position).get("payText"));

		/**
		 * selectId如果 == item的位置
		 * radioButton设置为选中
		 * 否则为未选中
		 */
		if (selectId == position){
			viewHolder.payRb.setChecked(true);
		}else {
			viewHolder.payRb.setChecked(false);
		}

		return view;
	}

	static class ViewHolder{
		ImageView payImage;
		TextView payTxt;
		RadioButton payRb;
	}
	/**
	 * 设置选中时的id
	 * @param position listView item里的位置
	 */
	public void setSelectId(int position){
		selectId = position;
	}

}
