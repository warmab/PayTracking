package com.balusoft.paytracking;



import com.balusoft.paytracking.data.DbHelper;
import com.balusoft.paytracking.data.ManageUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
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
        	
        	final View dialogView= inflater.inflate(R.layout.current_money_dialog, null);
        	builder.setView(dialogView)
        	.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub											

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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
    	switch (item.getItemId()) {
		case R.id.action_settings:
			startActivity(new Intent(MainActivity.this,SettingsActivity.class));
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
    
}
