package com.balusoft.paytracking.data;

import java.util.ArrayList;
import java.util.UUID;

import com.balusoft.paytracking.model.Payment;
import com.balusoft.paytracking.model.ToPay;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ToPayData {

	private static final String TAG = ToPayData.class.getSimpleName();
	private DbHelper dbHelper;	
	
	public ToPayData(Context context){
		this.dbHelper = new DbHelper(context);	
	}
	
	public void insertToPay(ToPay toPay){
		Log.d(TAG, "insertToPay");
		ContentValues values= new ContentValues();
		
		UUID uuid = UUID.randomUUID();
		
		values.put(ToPayContract.ToPayColumns._ID, uuid.toString());
		values.put(ToPayContract.ToPayColumns.SUBJECT,toPay.getSubject());
		values.put(ToPayContract.ToPayColumns.AMOUNT,toPay.getAmount());
		values.put(ToPayContract.ToPayColumns.PAYMENT_DATE,toPay.getPaymentDate());
		
		insertOrIgnore(values);			
	}
	
	public ArrayList<ToPay> getPayments(){
		Log.d(TAG,"gePayments");
		ArrayList<ToPay> newList = new ArrayList<ToPay>();
		
		SQLiteDatabase db=this.dbHelper.getReadableDatabase();
		Cursor cursor;
		try {
			cursor=db.query(ToPayContract.TABLE, null, null, null, null, null, null);
			String subject,paymentDate;
			int amount;
			while (cursor.moveToNext()) {				
				subject=cursor.getString(cursor.getColumnIndex(ToPayContract.ToPayColumns.SUBJECT));
				paymentDate=cursor.getString(cursor.getColumnIndex(ToPayContract.ToPayColumns.PAYMENT_DATE));
				amount=cursor.getInt(cursor.getColumnIndex(ToPayContract.ToPayColumns.AMOUNT));
				
				ToPay toPay=new ToPay(subject, paymentDate, amount);
				
				newList.add(toPay);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Log.d(TAG,newList.toString());
		return newList;
	}	
	
	/**
	 * 
	 * @param values
	 * @return
	 */
	private long insertOrIgnore(ContentValues values) {
		Log.d(TAG,"insertOrIgnore on "+values);
		SQLiteDatabase db = this.dbHelper.getWritableDatabase();

		long result;
		try {
			result = db.insertWithOnConflict(ToPayContract.TABLE, null, values,
					SQLiteDatabase.CONFLICT_IGNORE);
		} finally {
			db.close();
		}
		return result;
	}	
}
