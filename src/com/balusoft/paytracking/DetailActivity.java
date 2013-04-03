package com.balusoft.paytracking;

import java.util.ArrayList;

import com.balusoft.paytracking.data.PaymentAdapter;
import com.balusoft.paytracking.data.PaymentData;
import com.balusoft.paytracking.model.Payment;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.os.Build;

public class DetailActivity extends Activity {

	
	ListView listPayments;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		// Show the Up button in the action bar.
		setupActionBar();
		
		listPayments=(ListView)findViewById(R.id.listPayments);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		PaymentData paymentData = new PaymentData(this);
		ArrayList<Payment> payments =  paymentData.getPayments();		
		PaymentAdapter adapter = new PaymentAdapter(this, R.layout.payment_row, payments.toArray(new Payment[payments.size()]));				
		listPayments.setAdapter(adapter);	
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * 
	 * @param v
	 */
	public void btnPayment_Click(View v){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater= this.getLayoutInflater();
		
		final View dialogView= inflater.inflate(R.layout.dialog_add_new_payment,null);
		builder.setView(dialogView)
		.setPositiveButton(getString(R.string.btnOk), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {				
				TextView txtSubject=(TextView)dialogView.findViewById(R.id.txtSubject);
				TextView txtAmount=(TextView)dialogView.findViewById(R.id.txtAmount);				
				
				Payment newPayment= new Payment(txtSubject.getText().toString(),
						Integer.parseInt(txtAmount.getText().toString()),
						"-");
				PaymentData paymentData = new PaymentData(getApplicationContext());
				paymentData.insertPayment(newPayment);		
				
				DetailActivity.this.onResume();
			}
		})
		.setNegativeButton(getString(R.string.btnCancel), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			 	
			}
		});
		
		AlertDialog dialog= builder.create();
		dialog.setTitle(getString(R.string.MainTitlePayment));
		dialog.show();
		
	}
}
