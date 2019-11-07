package com.byron.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.byron.ui.MainActivity;
import com.byron.ui.R;
import com.byron.ui.adapter.UiPayAdapter;
import com.byron.ui.manage.LayoutManager;
import com.byron.ui.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 结合listView和RadioButton界面设计
 * @author Byron
 * @date 2019/11/7
 */
public class ListViewRadioButtonActivity extends AppCompatActivity {

	private ListView listView;
	private ImageView questView, backView;
	private Button uiPayBtn;
	private View payView;

	private String[] payText = {"微信支付", "支付宝支付", "QQ支付", "银联支付", "游戏点卡", "手机卡"};
	private Integer[] payImage = {R.drawable.ui_wechat, R.drawable.ui_ali, R.drawable.ui_qq, R.drawable.ui_union, R.drawable.ui_game, R.drawable.ui_phone};


	private int mPosition;
	private List<Map<String, Object>> payData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_radio_button);

		if (getSupportActionBar() != null){
			getSupportActionBar().hide();
		}

		payData = new ArrayList<>();
		for (int i=0; i<6; i++){
			Map<String, Object> map = new HashMap<>();
			map.put("payImage", payImage[i]);
			map.put("payText", payText[i]);
			payData.add(map);
		}

		showView(ListViewRadioButtonActivity.this);

		findViewById(R.id.ui_title_btn_back).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});

		findViewById(R.id.ui_pay_btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				showView(ListViewRadioButtonActivity.this);
			}
		});

	}

	/**
	 * 显示dialog
	 * @param context 上下文
	 */
	public void showView(final Context context){

		payView = LayoutManager.pushView(context, ResourceUtil.getLayoutId(context, "ui_pay"));
		/**
		 * 也可以直接用R.来获取相应的资源
		 */
		questView = payView.findViewById(ResourceUtil.getId(context, "ui_question"));
		listView = payView.findViewById(ResourceUtil.getId(context, "ui_pay_list"));
		backView = payView.findViewById(ResourceUtil.getId(context, "ui_back"));
		uiPayBtn = payView.findViewById(ResourceUtil.getId(context, "ui_pay_btn"));

		final UiPayAdapter uiPayAdapter = new UiPayAdapter(context, payData);
		listView.setAdapter(uiPayAdapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				Log.d("byron:", "onItemClick");
				uiPayAdapter.setSelectId(position);
				mPosition = position;
				Log.d("byron:", "onItemClick: position: " + position);
				/**
				 * 若适配器内容改变
				 * 强制调用getView来刷新每个item
				 */
				uiPayAdapter.notifyDataSetChanged();
			}
		});

		//问题按钮
		questView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(context, R.string.ui_pay_customer_service, Toast.LENGTH_LONG).show();
			}
		});

		//返回按钮
		backView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				LayoutManager.dismissDialog();
			}
		});

		//确定支付按钮
		uiPayBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mPosition == 0){
					Toast.makeText(context, "选中微信", Toast.LENGTH_LONG).show();
				}else if (mPosition == 1){
					Toast.makeText(context, "选中支付宝", Toast.LENGTH_LONG).show();
				}else if (mPosition == 2){
					Toast.makeText(context, "选中QQ", Toast.LENGTH_LONG).show();
				}else if (mPosition == 3){
					Toast.makeText(context, "选中银联", Toast.LENGTH_LONG).show();
				}else if (mPosition == 4){
					Toast.makeText(context, "选中点卡", Toast.LENGTH_LONG).show();
				}else if (mPosition == 5){
					Toast.makeText(context, "选中手机卡", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
