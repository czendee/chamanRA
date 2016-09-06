package com.carlosZ.amigoxhalan;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList; 
import android.os.Bundle; 
import android.app.Activity; 
import android.content.res.TypedArray; 
import android.graphics.Bitmap; 
import android.graphics.BitmapFactory; 
import android.view.View; 
import android.widget.AdapterView; 
import android.widget.AdapterView.OnItemClickListener; 
import android.widget.EditText;
import android.widget.GridView; 
import android.widget.TextView;
import android.widget.Toast; 


/**
 * 
 * @author javatechig {@link http://javatechig.com}
 * 
 */
public class BuildingsActivity extends Activity {
	private GridView gridView;
	private GridViewAdapter customGridAdapter;
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buildings);
		
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
        Intent i = new Intent(BuildingsActivity.this, BuildingsActivity.class);
        startActivity(i);
        
    }
    
    
    //ICONS particular for this screen
    
    /** Called when the user clicks the MonitorConfig button */
    public void goToMonitorConfigLocal(View view) {
    	String resultData = Constants.TEMPORAL_FILE_TEST_BUILDINGS_ALL_ONE_BULDING;
    	goToMonitorConfig(view,resultData);
    	
    	
    } 

    
    /** Called when the user clicks the MonitorConfig button */
/*    public void goToMonitorConfigLocal(View view) {
    	String resultData = Constants.TEMPORAL_FILE_TEST_COLLECTOR;
    	goToMonitorConfig(view,resultData);
    } 
*/
    
    /** Called when the user clicks the MonitorConfig button */
    
    public void goToMonitorConfigWeb(View view) {
    	//get the result data
    	String resultData = null;
    	WebManager webMgr=new WebManager();
    	//get the employee id and 
//    	EditText editText = (EditText) findViewById(R.id.editText1);
//    	String valueEmployee = editText.getText().toString();
    	
    	resultData = webMgr.processWebFunctions("01", "68", ""); // pass the id for the html page
    	
    	goToMonitorConfig( view,resultData);
    }
    
    

    
    /** Called when the user clicks the ViewLocalFile button */
/*    public void goToViewLocalFileContent(View view) {
    	
    	System.out.println("goToViewLocalFileContent - 1:");
    	Context ctx = getApplicationContext();
    	FileManager fMgr= new FileManager();
    	System.out.println("goToViewLocalFileContent - 2:");
    	String resultado= fMgr.processFileFunctions(ctx, "06", Constants.FILENAME_EMPLOYEE_CONFIG, " ");
    	System.out.println("goToViewLocalFileContent - 3:"+resultado);


    } 
    */
    
    /** Called when one of the buttons are clicked: web or local */
    public void goToMonitorConfig(View view, String resultData) {
    	// Do something in response to button}
    	//get the result data
    	//String resultData = Constants.TEMPORAL_FILE_TEST;
    	
    	
    	//parse the result data
    	
    	;
    	ParseDataManager pdMgr = new ParseDataManager();
    	
    	String infoToDisplay= pdMgr.parseDataFunctions(resultData, Constants.GET_BUILDING_ALL_DATA, "", "");
    	
//    	String infoToDisplay= resultData;
    	//display the result data
    	
    	
    	 System.out.println("goToMonitorConfig - 1:"+infoToDisplay);
    	 TextView txtText = (TextView) findViewById(R.id.textBuildingResult);
    	 
 		txtText.setText(infoToDisplay);
    	
    	//take initial marker and final marker
    	//	bulding ID NAME
		//	UUID
		//	ADDRESS
		//	ANDUINO PORT
		//	ANDUINO KEY 
		//	 buildunits[101|2050][101|2052][101|2055][101|2056][112|3042][112|3052]ENDbuildunits 
    }

}
