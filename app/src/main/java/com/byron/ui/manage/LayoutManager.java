package com.byron.ui.manage;

import android.app.AlertDialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import java.util.ArrayList;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

/**
 * 加载布局类
 * @date 2019/11/7
 * @author Byron
 */
public class LayoutManager {

	private static AlertDialog alertDialog;
	private static FrameLayout frameLayout;
	private static AlertDialog fullAlertDialog;
	private static FrameLayout fullFrameLayout;
	private static ArrayList<View> arrayView = new ArrayList<>();


	/**
	 * 弹出设置好宽高的alertDialog
	 * 未适配屏幕旋转
	 * @param context 上下文
	 * @param layout getResLayout后的int类型的布局
	 * @return view
	 */
	public static View pushView(final Context context, int layout){

		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(displayMetrics);

		//设置view的宽高
		int width = (int) Math.min(displayMetrics.widthPixels * 0.85, 540 * displayMetrics.density);
		int height = (int) Math.min(displayMetrics.heightPixels * 0.85, 340 * displayMetrics.density);

		//设置如果是竖屏时候的宽高
		if (context.getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT){
			width = (int) (displayMetrics.widthPixels * 0.95);
			height = (int)Math.min(displayMetrics.heightPixels * 0.85, 340 * displayMetrics.density);
		}

		if (alertDialog == null){

			frameLayout = new FrameLayout(context);
			AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
			alertDialog = dialogBuilder.create();
			alertDialog.show();
			alertDialog.setContentView(frameLayout);
			//点击弹窗外部失效
			alertDialog.setCanceledOnTouchOutside(false);
			//返回键失效
			alertDialog.setCancelable(false);
			//实例化宽高
			WindowManager.LayoutParams lp =new WindowManager.LayoutParams();
			lp.copyFrom(alertDialog.getWindow().getAttributes());
			lp.width = width;
			lp.height =WindowManager.LayoutParams.WRAP_CONTENT;

			alertDialog.getWindow().setAttributes(lp);
			alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
			alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

			ViewGroup.LayoutParams lp2 = frameLayout.getLayoutParams();
			lp2.width = WindowManager.LayoutParams.MATCH_PARENT;
			lp2.height =height;

			frameLayout.setLayoutParams(lp2);
			frameLayout.setOnKeyListener(new View.OnKeyListener() {
				@Override
				public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
					if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK){
						Log.d("Byron:FrameLayout: ", "onKey-----");
						popView(context);
						return true;
					}
					return false;
				}
			});
		}

		View contentView = LayoutInflater.from(context).inflate(layout, null);
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(contentView.getWindowToken(), 0);

		arrayView.add(contentView);
		frameLayout.removeAllViews();
		frameLayout.addView(contentView);
		return contentView;
	}

	/**
	 * 减少arrayList<View>的缓存
	 * @param context 上下文
	 */
	public static void popView(Context context) {

		if (alertDialog == null || arrayView.size() == 0){
			return;
		}

		View removeView = arrayView.remove(arrayView.size() - 1);
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(removeView.getWindowToken(), 0);

		if (arrayView.size() > 0){
			View contentView = arrayView.get(arrayView.size() - 1);

			frameLayout.removeAllViews();
			frameLayout.addView(contentView);
		}else {
			alertDialog.dismiss();
		}
	}

	/**
	 * 清空布局的缓存
	 */
	public static void dismissDialog(){

		try {

			if (alertDialog !=null && alertDialog.isShowing()){
				alertDialog.dismiss();
			}

			if (fullAlertDialog != null && fullAlertDialog.isShowing()){
				fullAlertDialog.dismiss();
			}

		}catch (Exception e){
			e.printStackTrace();
		}finally {
			arrayView.clear();
			frameLayout = null;
			alertDialog = null;
			fullFrameLayout = null;
			fullAlertDialog = null;
		}
	}

}
