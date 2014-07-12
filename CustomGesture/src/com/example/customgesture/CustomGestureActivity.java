package com.example.customgesture;

import java.util.ArrayList;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomGestureActivity extends ActionBarActivity implements OnGesturePerformedListener {
	private GestureLibrary gLibrary;
	private Canvas canvas;
	private Paint paint=new Paint();
	Path path=new Path();
	GestureOverlayView gOverlay;
	Bitmap bitmap;
	ImageView imageView;
	Button DoneButton,clearButton;
	//private ArrayList<Path> pointsToDraw = new ArrayList<Path>();
	private ArrayList<String> gesturesMade=new ArrayList<String>();
	Display display;
	TextView ans;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		canvas=new Canvas();
		path=new Path();
		paint=new Paint();
		paint.setColor(Color.BLUE);
		setContentView(R.layout.fragment_custom_gesture);
		DoneButton=(Button) findViewById(R.id.ans);
		clearButton=(Button) findViewById(R.id.clear);
		setContentView(R.layout.activity_custom_gesture);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.custom_gesture, menu);
		return true;
	}

	public void doneClick(View v) {
		ans=(TextView) findViewById(R.id.textView1);
		ans.setText("here");
		int noOfEyes=0;
		String faceType=null;
		while(!gesturesMade.isEmpty())
		{
			String temp=gesturesMade.remove(0);
			if(temp.equalsIgnoreCase("circle"))
				noOfEyes++;
			else if(temp.equalsIgnoreCase("curve"))
				faceType="happy";
			else if(temp.equalsIgnoreCase("sad smile"))
				faceType="sad";
		}

		if(noOfEyes==2&&faceType!=null){

			ans.setText(faceType);
		}

	}


	public void clearClick(View v) {
		// TODO Auto-generated method stub
		ans=(TextView)(findViewById(R.id.textView1));
		ans.setText("");
		//canvas=new Canvas();
		display= getWindowManager().getDefaultDisplay();
		bitmap=Bitmap.createBitmap(display.getWidth(), display.getHeight(), Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		imageView.setImageBitmap(bitmap);
		gesturesMade.clear();
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_custom_gesture,
					container, false);
			return rootView;
		}
	}



	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		gLibrary = 
				GestureLibraries.fromRawResource(this, 
						R.raw.gestures);
		if (!gLibrary.load()) {
			finish();
		}   	
		gOverlay = (GestureOverlayView)findViewById(R.id.gestureOverlayView1);
		gOverlay.setGestureColor(Color.BLUE);
		gOverlay.setFadeEnabled(false);
		gOverlay.setUncertainGestureColor(Color.BLUE);
		gOverlay.setGestureStrokeWidth(8);
		imageView=(ImageView) findViewById(R.id.imageView1);

		display= getWindowManager().getDefaultDisplay();
		bitmap=Bitmap.createBitmap(display.getWidth(), display.getHeight(), Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		gOverlay.addOnGesturePerformedListener(this); 
		paint.setStrokeWidth(8);
		paint.setColor(Color.BLUE);	
		paint.setStyle(Paint.Style.STROKE);

	}


	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {

		ArrayList<Prediction> predictions =gLibrary.recognize(gesture);
		if (predictions.size() > 0 && predictions.get(0).score > 2) {
			String action = predictions.get(0).name;
			Double temp=predictions.get(0).score;
			path=gOverlay.getGesturePath();
			path.offset(-10,35);
			canvas.drawPath(path, paint);
			imageView.setImageBitmap(bitmap);
			gesturesMade.add(action);

		}
	}


}
