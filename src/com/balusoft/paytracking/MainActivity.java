package com.balusoft.paytracking;



import com.balusoft.paytracking.data.DbHelper;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * 
 * @author Isaac Ojeda
 *
 */
public class MainActivity extends Activity {

	DbHelper dbHelper;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
        setTitle("Payment Dashboard");
        
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
