package com.balusoft.paytracking.data;

import android.content.Context;
import android.content.SharedPreferences;

public class ManageUtil {

	public ManageUtil(Context currentContext) {
		super();
		this.currentContext = currentContext;
	}

	private Context currentContext;
	private String TAG=ManageUtil.class.getSimpleName();
	private final String MANAGE_STORAGE_ID="com.balusoft.paytracking.ManageUtil.STORAGE";
	private String currentMoney,earn,payments;

	public static final String C_CURRENT_MONEY="CurrentMoney";
	
	/**
	 * 
	 * @return
	 */
	public String getCurrentMoney() {
		return getStoredValue(ManageUtil.C_CURRENT_MONEY, "0");
	}

	/**
	 * 
	 * @param currentMoney
	 */
	public void setCurrentMoney(String currentMoney) {
		setStoredValue(ManageUtil.C_CURRENT_MONEY, currentMoney);		
	}

	public String getEarn() {
		return earn;
	}

	public void setEarn(String earn) {
		this.earn = earn;
	}

	public String getPayments() {
		return payments;
	}

	public void setPayments(String payments) {
		this.payments = payments;
	}
	
	/**
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	private String getStoredValue(String key,String defaultValue){
		SharedPreferences settingsHelper=this.currentContext.getSharedPreferences(this.MANAGE_STORAGE_ID,0);
		return settingsHelper.getString(key,defaultValue);
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	private void setStoredValue(String key,String value){
		SharedPreferences settingsHelper=this.currentContext.getSharedPreferences(this.MANAGE_STORAGE_ID,0);
		SharedPreferences.Editor editor = settingsHelper.edit();
		
		editor.putString(key, value);		
		
		editor.commit();		
	}
	
	
}
