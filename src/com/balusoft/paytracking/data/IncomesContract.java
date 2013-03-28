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
	public static final String TABE="incomes";
	
	/**
	 * 
	 * @author Isaac Ojeda
	 *
	 */
	public static final class IncomesColumns implements BaseColumns{
		public static final String Amount="amount";
		public static final String Date="date";
	}
	
}
