package com.example.sortmeapp;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Activity activity;
    private ArrayList<Object> childtems;
    private ArrayList<String> child;
    private ArrayList<MovieData> parentItems;
    
    public ExpandableListAdapter(Activity activity, ArrayList<MovieData> parents, ArrayList<Object> childern) {
        this.parentItems = parents;
        this.childtems = childern;
        this.activity= activity;
    }
    
    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        child = (ArrayList<String>) childtems.get(groupPosition);
        TextView textView = null;
        LayoutInflater inflater = activity.getLayoutInflater();
        if (convertView == null) {
        	
            convertView = inflater.inflate(R.layout.parent_listview, null);
        }
 
        textView = (TextView) convertView.findViewById(R.id.textView1);
        textView.setText(child.get(childPosition));
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        return convertView;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
 
        if (convertView == null) {
        	LayoutInflater infalInflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_listview, null);
           
        }
        
        ((TextView) convertView.findViewById(R.id.textView1)).setText(parentItems.get(groupPosition).title);
        
       // ((TextView) convertView).setText(isExpanded);
       ImageView temp= (ImageView) convertView.findViewById(R.id.imageView1);
       temp.setImageBitmap(parentItems.get(groupPosition).movieIcon);
        
        return convertView;
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<String>) childtems.get(groupPosition)).size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }
 
    @Override
    public int getGroupCount() {
        return parentItems.size();
}
 
    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }
 
    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
}

	
	@Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}