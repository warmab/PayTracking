package com.balusoft.paytracking;



import com.balusoft.paytracking.data.DbHelper;
import com.balusoft.paytracking.data.ManageUtil;
import com.balusoft.paytracking.data.ToPayData;
import com.balusoft.paytracking.model.ToPay;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 
 * @author Isaac Ojeda
 *
 */
public class MainActivity extends Activity {

	DbHelper dbHelper;
	
	private final String TAG=MainActivity.class.getSimpleName();
	private TextView txtViewCurrentMoney;
	private String mCurrentMoney="0";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
        setTitle("Payment Dashboard");
        
        
        // Check if the user already set his current money
        final ManageUtil manageUtil = new ManageUtil(getApplicationContext());
        mCurrentMoney=manageUtil.getCurrentMoney();
        if(mCurrentMoney.equals("0")){
        	// Show modal dialog to put the value
        	
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	LayoutInflater inflater = this.getLayoutInflater();
        	
        	final View dialogView= inflater.inflate(R.layout.dialog_add_current_money, null);
        	builder.setView(dialogView)
        	.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {										

					EditText txtAddCurrentMoney=(EditText)dialogView.findViewById(R.id.txtAddCurrentMoney);
					ManageUtil tempManage= new ManageUtil(getApplicationContext());
					tempManage.setCurrentMoney(txtAddCurrentMoney.getText().toString());
					
					String currentMoneyTitle=String.format(getString(R.string.txtViewCurrentMoney), txtAddCurrentMoney.getText().toString());
					txtViewCurrentMoney.setText(currentMoneyTitle);					
				}
			});
        	
        	AlertDialog dialog=builder.create();
        	dialog.show();        	 
        }
    	// Find references
    	txtViewCurrentMoney=(TextView)findViewById(R.id.txtViewCurrentMoney);        
    }
  
    @Override
	protected void onResume() {
		super.onResume();
		
		ManageUtil manageUtil = new ManageUtil(getApplicationContext());
		mCurrentMoney=manageUtil.getCurrentMoney();
		String currentMoneyTitle=String.format(getString(R.string.txtViewCurrentMoney), mCurrentMoney);
		txtViewCurrentMoney.setText(currentMoneyTitle);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
           
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	AlertDialog.Builder builder;
    	LayoutInflater inflater=this.getLayoutInflater();
    	AlertDialog dialog;
    	
    	switch (item.getItemId()) {
		case R.id.action_settings:
			startActivity(new Intent(MainActivity.this,SettingsActivity.class));
			break;

		case R.id.action_add_money:
			
			builder = new AlertDialog.Builder(this);
			
			final View addDialog = inflater.inflate(R.layout.dialog_add_more_money, null);
			builder.setView(addDialog)
			.setPositiveButton(getString(R.string.btnOk), new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {

					EditText txtMoreMoney=(EditText)addDialog.findViewById(R.id.txtMoreMoney);
					ManageUtil managementUtil= new ManageUtil(getApplicationContext());
					managementUtil.addMoney(Integer.parseInt(txtMoreMoney.getText().toString()));
					
					MainActivity.this.onResume();
				}
			}).setNegativeButton(getString(R.string.btnCancel), new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {

					
				}
			});
			
			dialog= builder.create();
			dialog.setTitle(getString(R.string.titleAddMoreMoney));
			dialog.show();
			
			break;
		case R.id.action_add_payment:
			
			builder = new AlertDialog.Builder(this);
			final View removeDialog = inflater.inflate(R.layout.dialog_add_new_to_pay,null);
			builder.setView(removeDialog)
			.setPositiveButton(getString(R.string.btnRemove), new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					String subject,paymentDate;
					int amount;
					
					subject=((EditText)removeDialog.findViewById(R.id.txtSpentSubject)).getText().toString();
					paymentDate="-";					
					amount=Integer.parseInt(((EditText)removeDialog.findViewById(R.id.txtSpentAmount)).getText().toString());
					
					ToPayData toPaydata = new ToPayData(getApplicationContext());
					ToPay toPay = new ToPay(subject, paymentDate, amount);
					toPaydata.insertToPay(toPay);
					
					ManageUtil manageUtil = new ManageUtil(getApplicationContext());					
					manageUtil.addMoney(-amount);
					
					MainActivity.this.onResume();
				}
			})
			.setNegativeButton(getString(R.string.btnCancel), new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			});
			
			dialog= builder.create();
			dialog.show();
			
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

    /**
     * Event Handler, open DetailActivity
     * @param v
     */
	public void btnDetail_OnClick(View v){
    	Intent openDetailsIntent = new Intent(MainActivity.this,DetailActivity.class);
    	startActivity(openDetailsIntent);	
    }
	
	public void btnSpentMoney_OnClick(View v){
		startActivity(new Intent(this,SpentMoneyActivity.class));
	}
    
}
