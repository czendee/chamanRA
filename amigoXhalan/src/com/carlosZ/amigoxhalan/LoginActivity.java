package com.carlosZ.amigoxhalan;




import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends Activity {

	public final static String ERRORmsg = "com.fedex.monitor.docefedex.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
	}

	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
 
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case R.id.action_search:
            // search action
            return true;
        case R.id.action_location_found:
            // location found
            LocationFound();
            return true;
        case R.id.action_refresh:
            // refresh
            return true;
        case R.id.action_help:
            // help action
            return true;
        case R.id.action_check_updates:
            // check for updates action
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    
    /**
     * Launching new activity
     * */
    private void LocationFound() {
        Intent i = new Intent(LoginActivity.this, BuildingsActivity.class);
        startActivity(i);
        
    }

    
	
    /** Called when the user clicks the login button */
/*    public void goToLogin(View view) {
    	
 
    	
    	EditText editTextEmployee = (EditText) findViewById(R.id.Entry03);
    	String messageEmployee = editTextEmployee.getText().toString();
    	 
    	EditText editTextKey = (EditText) findViewById(R.id.editText2);
    	String messageKey = editTextKey.getText().toString();

    	
    	Controller myController=new Controller();
    	Context ctx = getApplicationContext(); 
    	System.out.println("goToLogin - 1");
    	
	   	Intent intent = myController.processLogin(this,ctx, messageEmployee,messageKey);
    	
    	if(intent!=null){
    		intent.putExtra(ERRORmsg, "Error occurred, please review logs");    		
        	startActivity(intent);    	
        	finish();
    	}else{
    		//error handling
    	}
    	
 
    	
    }
    
  */
    
    
    /** Called when the user clicks the login button */
    public void goToLogin2(View view) {
    	
    	// Do something in response to button}
    	// logic to
    	/*
    	 *           On click: Local method - find the employeeid and key in Local Storage
                         returns - valid/invalid/new/blocked/inactive/expired
                                   valid-employeeId and key exists and are the same as typed, and status is Active
                                   invalid- employeeId and key exists, but at least does not match
                                   new -data for employeeId does not exists
                                   blocked- employeeId and key exists and are the same as typed, but status is Blocked
                                   inactive- employeeId and key exists and are the same as typed, but status is Inactive
                                   expired-employeeId and key exists and are the same as typed, and status is Active,
                                           but expiration date has already passed.

                      What to do with the result:
                                  valid: -go to home
                                  invalid: -store one failed try
                                            if less than 3 tries:
                                               -go to 1.4
                                            else
                                               -go to 1.5
                                  new:    -go to LoginFirstActivity
                                  blocked: -go to LoginBlockedInactive
                                  inactive: -go to LoginBlockedInactive
                                  expired: - go to LoginExpired

    	 * 
    	 * 
    	 */

    	Intent intent = new Intent(this, LoginActivity.class);
    	
    	EditText editText = (EditText) findViewById(R.id.Entry03);
    	String message = editText.getText().toString();
    	
    	if (message.equals("1") || message.equals("boriti") || message.equals("aris")){
//    	   	Intent intent2 = new Intent(this, MainActivity.class);
    	   	Intent intent2 = new Intent(this, ListBuildingsActivity.class);
        	
    	   	intent2.putExtra("cual_usuario",message+"");
            System.out.println("Login activity passing,value:"+message);            
        	
        	startActivity(intent2); 
    	}else if (message.equals("2") ){
//    	   	Intent intent2 = new Intent(this, LoginFirstTimeActivity.class);
        	        	
//        	intent.putExtra(EXTRA_MESSAGE, message);
        	
//        	startActivity(intent2);     		
    	}else if (message.equals("3") ){
//    	   	Intent intent2 = new Intent(this, LoginBlockInactiveActivity.class);
        	
//        	intent.putExtra(EXTRA_MESSAGE, message);
        	
//        	startActivity(intent2);    		
    	}
    	 
    	//to test assume it is OK
    	
 
    	
    }
    
}



	
    
    

