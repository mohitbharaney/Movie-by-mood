package com.example.sortmeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
//import android.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

 //   @Override
 //   public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
  //      switch (item.getItemId()) {
   //         case R.id.action_search:
     //           openSearch();
     //           return true;
      //      case R.id.action_settings:
       //         openSettings();
        //        return true;
    //        default:
    //            return super.onOptionsItemSelected(item);
   //     }
  //  }
    
    /** Called when the user clicks the Continue button */
    public void onContinue(View view) {
       //  Do something in response to button
    	Intent intent = new Intent(this, MoodChoicesActivity.class);
    	startActivity(intent);
    }
    
}
