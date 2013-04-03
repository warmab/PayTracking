package com.balusoft.paytracking.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 
 * @author Isaac Ojeda
 *
 */
public class ToPayContract extends BaseContract {
	
	public static final Uri CONTENT_URI=Uri.parse(String.format("content://%s/topay", AUTHORITY));		
	public static final String TABLE="topay";	
	
	/**
	 * 
	 * @author Isaac Ojeda
	 *
	 */
	public static final class ToPayColumns implements BaseColumns{

		public static final String SUBJECT="subject";
		public static final String AMOUNT="amount";
		public static final String PAYMENT_DATE="payment_date";		
	}		
}
