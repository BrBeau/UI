package com.byron.ui.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * 资源工具类
 * 方便多模块开发打包时出现R.资源问题
 * @author Byron
 * @date 2019/11/5
 */
public class ResourceUtil {

	public static int getId(Context context, String paramId){
		return getIdRes(context.getResources(), paramId, context.getPackageName());
	}

	public static int getLayoutId(Context context, String paramLayoutName){
		return getLayoutId(context.getResources(), paramLayoutName, context.getPackageName());
	}

	public static int getIdRes(Resources resources, String paramId, String paramPackName){
		return getResId(resources, "id", paramId, paramPackName);
	}
	public static int getLayoutId(Resources resources, String paramLayoutName, String paramPackageName){
		return getResId(resources, "layout", paramLayoutName, paramPackageName);
	}

	private static int getResId(Resources resources, String paramResourceName, String paramIdName, String paramPackageName){

		if (paramResourceName == null || paramIdName == null){
			return -1;
		}

		try {
			return resources.getIdentifier( paramIdName, paramResourceName,paramPackageName);
		}catch (Exception e){
			e.printStackTrace();
		}
		return -1;
	}
}
