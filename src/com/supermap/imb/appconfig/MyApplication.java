package com.supermap.imb.appconfig;


import com.supermap.data.Environment;
import com.supermap.imb.base.R;
import com.supermp.imb.file.MyAssetManager;
import com.supermp.imb.file.MySharedPreferences;

import android.app.Application;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class MyApplication extends Application {
	public static String SDCARD = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
	private static MyApplication sInstance = null;
	
	private DefaultDataManager mDefaultDataManager = null;
	
	private DataManager mUserDataManager = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		sInstance = this;
		
		//第一步就是设置环境参数，初始化好iMobile
		Environment.setLicensePath(DefaultDataConfig.LicPath);
		Environment.initialization(this);
		
		//初始化系统相关的类
		MySharedPreferences.init(this);
		MyAssetManager.init(this);
		
		mDefaultDataManager = new DefaultDataManager();
		mUserDataManager = new DataManager();
		
		//配置数据
		new DefaultDataConfig().autoConfig();
		
	}
	
	/**
	 * 获取当前application
	 * @return
	 */
	public static MyApplication getInstance(){
		return sInstance;
	}
	
	/**
	 * 获取默认数据对象
	 * @return
	 */
	public DefaultDataManager getDefaultDataManager(){
		return mDefaultDataManager;
	}
	
	/**
	 * 获取用户数据对象
	 * @return
	 */
	public DataManager getUserDataManager(){
		return mUserDataManager;
	}
	
	/**
	 * 显示信息
	 * @param info 需要显示的信息
	 */
	public void ShowInfo(String info){
		Toast toast = Toast.makeText(sInstance, info, 500);
		toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.show();
	}
	
	/**
	 * 显示错误信息
	 * @param err  需要显示的错误信息
	 */
	public void ShowError(String err){
		Toast toast = Toast.makeText(sInstance, "Error: "+err, 500);
		toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.getView().setBackgroundResource(R.drawable.red_round_rect);
		toast.show();
		Log.e(this.getClass().getName(), err);
	}
	
	public static int dp2px(int dp){
		return (int) (dp*sInstance.getResources().getDisplayMetrics().density);
	}
	
}
