package com.byron.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.byron.ui.activity.ListViewRadioButtonActivity;
import com.byron.ui.adapter.RecyclerViewAdapter;
import com.byron.ui.adapter.RecyclerViewOnItemClickListener;

/**
 * 本项目主要测试自定义布局
 * @author Byron
 * @date 2019/11/5
 */
public class MainActivity extends AppCompatActivity {

	private RecyclerView recyclerView;

	private String[] payTextRecycler = {"ui列表设计", "正在进行...", "正在进行...",
			"正在进行...", "正在进行...", "正在进行...",
			"正在进行...", "正在进行...", "正在进行...",
			"正在进行...", "正在进行...", "正在进行..."};
	private int[] payImageRecycler = {R.drawable.ui_list, R.drawable.ui_designing, R.drawable.ui_designing,
			R.drawable.ui_designing, R.drawable.ui_designing, R.drawable.ui_designing,
			R.drawable.ui_designing, R.drawable.ui_designing, R.drawable.ui_designing,
			R.drawable.ui_designing, R.drawable.ui_designing, R.drawable.ui_designing,};



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

	}

	public void initView(){
		recyclerView = findViewById(R.id.ui_recycler);
		GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
		recyclerView.setLayoutManager(layoutManager);

		RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, payImageRecycler, payTextRecycler);

		recyclerView.setAdapter(recyclerViewAdapter);
		//分割线
		recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.HORIZONTAL));
		recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
		//RecyclerView各个item的点击事件
		recyclerViewAdapter.setOnClickListener(onItemClickListener);
	}

	private RecyclerViewOnItemClickListener onItemClickListener = new RecyclerViewOnItemClickListener() {
		@Override
		public void onClick(View view, int position) {


			switch (position){

				case 0:
					Intent intent = new Intent(MainActivity.this, ListViewRadioButtonActivity.class);
					startActivity(intent);
					break;
				case 1:
					Toast.makeText(MainActivity.this, "选中1", Toast.LENGTH_LONG).show();
					break;
				case 2:
					Toast.makeText(MainActivity.this, "选中2", Toast.LENGTH_LONG).show();
					break;
				case 3:
					Toast.makeText(MainActivity.this, "选中3", Toast.LENGTH_LONG).show();
					break;
				case 4:
					Toast.makeText(MainActivity.this, "选中4", Toast.LENGTH_LONG).show();
					break;
				case 5:
					Toast.makeText(MainActivity.this, "选中5", Toast.LENGTH_LONG).show();
					break;
				case 6:
					Toast.makeText(MainActivity.this, "选中6", Toast.LENGTH_LONG).show();
					break;
			}
		}
	};
}
