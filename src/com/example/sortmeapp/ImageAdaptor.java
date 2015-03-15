package com.example.sortmeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] moods;


    public ImageAdapter(Context mContext, String[] moods) {
        this.mContext = mContext;
        this.moods = moods;
    }

    public int getCount() {
        return moods.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
    	LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            gridView = new View(mContext);
            gridView = inflater.inflate(R.layout.grid_single, null);
            TextView textView = (TextView) gridView.findViewById(R.id.label);
            textView.setText(moods[position]);
            ImageView grid_image = (ImageView) gridView .findViewById(R.id.grid_image);
            String mobile = moods[position];

            if (mobile.equals("Cheerful")) {
            	grid_image.setImageResource(R.drawable.checkedradio_cheerful);
            } else if (mobile.equals("Romantic")) {
            	grid_image.setImageResource(R.drawable.checkedradio_romantic);
            } else if (mobile.equals("Confused")) {
            	grid_image.setImageResource(R.drawable.checkedradio_confused);
            } else if (mobile.equals("Pensive")) {
            	grid_image.setImageResource(R.drawable.checkedradio_pensive);
            } else if (mobile.equals("Gloomy")) {
            	grid_image.setImageResource(R.drawable.checkedradio_gloomy);
            } else {
            	grid_image.setImageResource(R.drawable.checkedradio_angry);
            }
            
        } else {
        	gridView = (View) convertView;
        }

        return gridView;

    }

}

