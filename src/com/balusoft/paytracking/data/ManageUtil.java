package com.balusoft.paytracking.data;

import android.content.Context;
import android.content.SharedPreferences;

public class ManageUtil {

	public ManageUtil(Context currentContext) {
		super();
		this.currentContext = currentContext;
	}

	private Context currentContext;	
	private final String MANAGE_STORAGE_ID="com.balusoft.paytracking.ManageUtil.STORAGE";
	private String payments;

	public static final String C_CURRENT_MONEY="CurrentMoney";
	public static final String C_EARN_MONEY="EarnMoney";
	
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

	/**
	 * 
	 * @return
	 */
	public String getEarn() {
		return getStoredValue(ManageUtil.C_EARN_MONEY,"-1");
	}

	/**
	 * 
	 * @param earn
	 */
	public void setEarn(String earn) {
		setStoredValue(ManageUtil.C_EARN_MONEY, earn);
	}

	public String getPayments() {
		return payments;
	}

	public void setPayments(String payments) {
		this.payments = payments;
	}
	
	public void addMoney(int money){
		this.setCurrentMoney(""+(Integer.parseInt(this.getCurrentMoney())+money));
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
