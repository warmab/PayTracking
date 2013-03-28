package com.balusoft.paytracking.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 
 * @author Isaac Ojeda
 *
 */
public class IncomesContract extends BaseContract {

	public static final Uri CONTENT_URI=Uri.parse(String.format("content://%s/incomes",AUTHORITY));	
	public static final String TABLE="incomes";
	
	/**
	 * 
	 * @author Isaac Ojeda
	 *
	 */
	public static final class IncomesColumns implements BaseColumns{
		public static final String AMOUNT="amount";
		public static final String DATE="date";
	}
	
}
