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
import android.widget.Toast; 


/**
 * 
 * @author javatechig {@link http://javatechig.com}
 * 
 */
public class MainActivity extends Activity {
	private GridView gridView;
	private GridViewAdapter customGridAdapter;
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
   
		gridView = (GridView) findViewById(R.id.gridView1);
		customGridAdapter = new GridViewAdapter(this, R.layout.row_grid, getData());
		gridView.setAdapter(customGridAdapter);		
		
		gridView.setOnItemClickListener(new  OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
				int position, long id) {
				System.out.println("click en image:"+position);
				Toast.makeText(MainActivity.this, position + "#Selected",
						Toast.LENGTH_SHORT).show();
				
				OpenRecamaraDetail(position);
			}

	});
		
	}

	private ArrayList getData() {
		final ArrayList imageItems = new ArrayList();
		// retrieve String drawable array
		TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
		for (int i = 0; i < imgs.length(); i++) {
			Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
					imgs.getResourceId(i, -1));
			imageItems.add(new ImageItem(bitmap, "Recamara#" + i));
			
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
        Intent i = new Intent(MainActivity.this, BuildingsActivity.class);
        startActivity(i);
        
    }

    /**
     * Launching new activity with recamara
     * */
    private void OpenRecamaraDetail(int cualReca) {
    	Intent i=null;
    	    	
    	
        i = new Intent(MainActivity.this, RecamaraActivity.class);
        i.putExtra("cual_recamara",cualReca+"");
        System.out.println("Building activity passing,value:"+cualReca);
        startActivity(i);
        
    }


}
