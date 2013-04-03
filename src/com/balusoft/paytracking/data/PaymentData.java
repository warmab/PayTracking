package com.balusoft.paytracking.data;

import java.util.ArrayList;
import java.util.UUID;
import com.balusoft.paytracking.model.Payment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 
 * @author Isaac Ojeda
 *
 */
public class PaymentData {

	private static final String TAG = PaymentData.class.getSimpleName();
	private DbHelper dbHelper;	
	
	/**
	 * 
	 * @param context
	 */
	public PaymentData(Context context) {
		this.dbHelper = new DbHelper(context);		
	}

	/**
	 * 
	 * @param newPayment
	 */
	public void insertPayment(Payment newPayment) {
		Log.d(TAG, "insertPayment");
		ContentValues values= new ContentValues();
		
		UUID uuid = UUID.randomUUID();
		
		values.put(PaymentContract.PaymentColumns._ID, uuid.toString());
		values.put(PaymentContract.PaymentColumns.SUBJECT,newPayment.getSubject());
		values.put(PaymentContract.PaymentColumns.AMOUNT,newPayment.getAmount());
		values.put(PaymentContract.PaymentColumns.PAYMENT_DATE,newPayment.getPaymentDate());
		
		insertOrIgnore(values);		
	}
	
	public ArrayList<Payment> getPayments(){
		Log.d(TAG,"gePayments");
		ArrayList<Payment> newList = new ArrayList<Payment>();
		
		SQLiteDatabase db=this.dbHelper.getReadableDatabase();
		Cursor cursor;
		try {
			cursor=db.query(PaymentContract.TABLE, null, null, null, null, null, null);
			String subject,paymentDate;
			int amount;
			while (cursor.moveToNext()) {				
				subject=cursor.getString(cursor.getColumnIndex(PaymentContract.PaymentColumns.SUBJECT));
				paymentDate=cursor.getString(cursor.getColumnIndex(PaymentContract.PaymentColumns.PAYMENT_DATE));
				amount=cursor.getInt(cursor.getColumnIndex(PaymentContract.PaymentColumns.AMOUNT));
				
				Payment payment=new Payment(subject, amount, paymentDate);
				
				newList.add(payment);
				
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
			result = db.insertWithOnConflict(PaymentContract.TABLE, null, values,
					SQLiteDatabase.CONFLICT_IGNORE);
		} finally {
			db.close();
		}
		return result;
	}
}
