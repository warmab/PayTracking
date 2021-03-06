package com.balusoft.paytracking.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
	
	static final String TAG=DbHelper.class.getSimpleName();
	
	Context context;
	
	/**
	 * Constructor
	 * @param context
	 */
	public DbHelper(Context context){
		super(context,BaseContract.DB_NAME,null,BaseContract.DB_VERSION);
		this.context=context;
	}

	/**
	 * Called only once, fist time the DB is created
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.d(TAG,"onCreate DbHelper");
		String sql=String.format("create table %s (%s text primary key, %s text, %s text,%s text)",
				PaymentContract.TABLE,
				PaymentContract.PaymentColumns._ID,
				PaymentContract.PaymentColumns.SUBJECT,
				PaymentContract.PaymentColumns.AMOUNT,
				PaymentContract.PaymentColumns.PAYMENT_DATE);
		
		db.execSQL(sql);
		
		sql=String.format("create table %s (%s text primary key, %s text, %s text,%s text)",
				ToPayContract.TABLE,
				ToPayContract.ToPayColumns._ID,
				ToPayContract.ToPayColumns.SUBJECT,
				ToPayContract.ToPayColumns.AMOUNT,
				ToPayContract.ToPayColumns.PAYMENT_DATE);
		
		db.execSQL(sql);		
		
		Log.d(TAG,"Executed sql: "+sql);
	}

	/**
	 *  Called whenever newVersion != oldVersion
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// Typically do ALTER TABLE statements, but... we're just in development
		// so:
		
		db.execSQL("drop table if exists "+PaymentContract.TABLE); // drops the old database			
		
		Log.d(TAG,"onUpdated");
		
		onCreate(db); // run onCreate to get new database
	}
}
