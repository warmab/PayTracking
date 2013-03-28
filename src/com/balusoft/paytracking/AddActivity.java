package com.balusoft.paytracking;

import com.balusoft.paytracking.common.Util;
import com.balusoft.paytracking.data.PaymentData;
import com.balusoft.paytracking.model.Payment;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class AddActivity extends Activity {

	private final String TAG=AddActivity.class.getSimpleName();
	private Util.AddIntents.AddTask mActivityTask;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		Bundle extras= getIntent().getExtras();
		if(extras != null){
			mActivityTask=(Util.AddIntents.AddTask)extras.get(Util.AddIntents.EXTRA_ADD);
			
			switch (mActivityTask) {
			case AddIncome:	
				Log.d(TAG,"Add Income");				
				break;
			case AddPayment:
				Log.d(TAG,"Add Payment");	
				setupPayment();
				break;			
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}
	
	public void btnAdd_OnClick(View v){		
		
		TextView txtSubject=(TextView)findViewById(R.id.txtSubject);
		TextView txtAmount=(TextView)findViewById(R.id.txtAmount);
		TextView txtPaymentDate=(TextView)findViewById(R.id.txtPaymentDate);
		
		Payment newPayment= new Payment(txtSubject.getText().toString(),
				Integer.parseInt(txtAmount.getText().toString()),
				txtPaymentDate.getText().toString());
		PaymentData paymentData = new PaymentData(getApplicationContext());
		paymentData.insertPayment(newPayment);
		
		finish();
	}
	
	public void btnCancel_OnClick(View v){
		finish();
	}

	private void setupPayment(){
		setTitle(getString(R.string.MainTitlePayment));		
	}
}
