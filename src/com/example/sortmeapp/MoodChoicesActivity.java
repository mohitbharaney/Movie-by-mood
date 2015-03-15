package com.example.sortmeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MoodChoicesActivity extends ActionBarActivity {
	
	public final static String EXTRA_MESSAGE = "com.example.sortmeapp.MESSAGE";
	GridView gridView;
	static final String[] MOBILE_OS = new String[] { "Cheerful", "Romantic", "Confused", "Pensive", "Gloomy", "Angry" };

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		//calculate screen size
	//	DisplayMetrics displaymetrics = new DisplayMetrics();
	//	getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
	//	int height = displaymetrics.heightPixels;
	//	int width = displaymetrics.widthPixels;
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mood_choices);
		
		//to set custom images
		gridView = (GridView) findViewById(R.id.gridView);
	    gridView.setAdapter(new ImageAdapter(this, MOBILE_OS)); 
	   
	    
	    //to change gridview dimensions based on device
	  //  gridView.setLayoutParams(new GridView.LayoutParams(width/2, height/3));
	    
	    
	    //to do something when you click on an image
	    gridView.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView parent, View v, int position, long id) {
	            Toast.makeText(getApplicationContext(), 
	            		((TextView) v.findViewById(R.id.label)).getText(), 
	            		Toast.LENGTH_SHORT).show();
	            Intent movieList=new Intent(getApplicationContext(),DisplayMatchedList.class);
	            String mood=(String) ((TextView) v.findViewById(R.id.label)).getText();
	            movieList.putExtra("mood", mood);
	       //     movieList.putExtra(EXTRA_MESSAGE, value)
	            startActivity(movieList);
	        }
	    });
	}
	    
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	/** Called when the user clicks the a radio button for mood
	public void displayList(View view) {
	    // Do something in response to radio button
		
		Intent intent = new Intent(this, DisplayMatchedList.class);
		int id = ((RadioGroup)findViewById( R.id.radioGroup1 )).getCheckedRadioButtonId();
		intent.putExtra(EXTRA_MESSAGE,id);
		startActivity(intent);
				
	} */
}
