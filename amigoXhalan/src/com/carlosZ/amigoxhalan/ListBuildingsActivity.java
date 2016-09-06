package com.carlosZ.amigoxhalan;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import android.app.Activity;
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
import android.widget.GridView; 
import android.widget.ImageView;
import android.widget.Toast; 


/**
 * 
 * @author javatechig {@link http://javatechig.com}
 * 
 */ 
public class ListBuildingsActivity extends Activity {
	private GridView gridView;
	private GridViewAdapter customGridAdapter;
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listbuildings);
		
		//i.putExtra("cual_usuario",message+"");
		Bundle extras = getIntent().getExtras();
	    String  cualesBuldings=null;
		if (extras != null) {
		    String value = extras.getString("cual_usuario");
		    System.out.println("usuario activity initial step 1,value:"+value);
            
		    if(value.equalsIgnoreCase("BORITI")){
		    	cualesBuldings=",1,2,3";
		    }else if(value.equalsIgnoreCase("ARIS")){
		    	cualesBuldings=",1,3,5";
		    }else{
		    	cualesBuldings="todos";
		    }
		    //getdata for this user
		    //second value is image_users index
		    //third value is the image_building index
		    //third value is the image_recamaras array number

		}

		
   
		gridView = (GridView) findViewById(R.id.gridView1);
		customGridAdapter = new GridViewAdapter(this, R.layout.row_grid, getData(cualesBuldings));
		gridView.setAdapter(customGridAdapter);		
		
		gridView.setOnItemClickListener(new  OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
				int position, long id) {
				System.out.println("click en image:"+position);
				Toast.makeText(ListBuildingsActivity.this, position + "#Selected",
						Toast.LENGTH_SHORT).show();
				
				OpenEdificioDetail(position);
			}

	});
		
	}

	private ArrayList getData(String cualesBuldings) {
		final ArrayList imageItems = new ArrayList();
		// retrieve String drawable array
		TypedArray imgs = getResources().obtainTypedArray(R.array.image_buildings_ids);
		for (int i = 0; i < imgs.length(); i++) {
			String iValue=Integer.toString(i);
			//only add the buildings pics the user has access to
			if(cualesBuldings.equalsIgnoreCase("TODOS") || cualesBuldings.indexOf(","+iValue)>=0){
				Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
						imgs.getResourceId(i, -1));
				imageItems.add(new ImageItem(bitmap, "Edificio#" + i));				
			}
			
			
		}

		return imageItems;

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
    	System.out.println("paso click gral");
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case R.id.action_search:
            // search action
        	System.out.println("paso click 1");
            return true;
        case R.id.action_location_found:
        	System.out.println("paso click 2");
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
        Intent i = new Intent(ListBuildingsActivity.this, BuildingsActivity.class);
        startActivity(i);
        
    }

    /**
     * Launching new activity with edificio
     * */
    private void OpenEdificioDetail(int cualReca) {
    	Intent i=null;
    	    	
    	
        i = new Intent(ListBuildingsActivity.this, MainActivity.class);
        i.putExtra("cual_building",cualReca+"");
        System.out.println("Building activity passing,value:"+cualReca);
        startActivity(i);
        
    }


}
