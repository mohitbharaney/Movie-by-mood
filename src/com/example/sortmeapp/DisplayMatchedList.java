package com.example.sortmeapp;

import java.util.ArrayList;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;

public class DisplayMatchedList extends ExpandableListActivity {

	private ArrayList<MovieData> parentItems = new ArrayList<MovieData>();
	private ArrayList<Object> childItems = new ArrayList<Object>();
	private ArrayList<MovieData> tempParentItems = new ArrayList<MovieData>();
	private ArrayList<Object> tempChildItems = new ArrayList<Object>();
	private ArrayList<MovieData> dataList=new ArrayList<MovieData>();
	private int movieSize=10;
	private Integer pageCount=1;
	private String message="";
	private Context context = this;
    private Button next;
	ExpandableListAdapter adapter;
	boolean test=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
		//not necessary
		setContentView(R.layout.activity_display_matched_list); 

		Intent intent = getIntent();
		message = intent.getStringExtra("mood");

		getMovieList(message);
		shuffle(dataList);

		setParent();
		addBitmap(parentItems);
		setChildren();
		
		ExpandableListView expandableList = (ExpandableListView) findViewById(android.R.id.list);
		expandableList.setDividerHeight(2);
		expandableList.setGroupIndicator(null);
		expandableList.setClickable(true);		

		LayoutInflater footerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));
		View x=footerView.inflate(R.layout.button, null);
		((Button)x.findViewById(R.id.footer_1)).setText("next");
		expandableList.addFooterView(x);
//
//		next=(Button) findViewById(R.id.footer_1);
//		next.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Button temp=(Button) v.findViewById(R.id.footer_1);
//				//temp.setClickable(false);
//			//	v.setBackgroundColor(Color.CYAN);
//				if(test){
//					test=false;
//					tempParentItems.clear();
//				setParent();
//				addBitmap(parentItems);
//				setChildren();
//				adapter.notifyDataSetChanged();
//			
//				}
//				test=true;
//				//temp.setClickable(true);
//			}
//		});

		adapter = new ExpandableListAdapter(this,parentItems, childItems);
		//adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
		expandableList.setAdapter(adapter);
		//	expandableList.setAdapter(adapter);



		//expandableList.setOnChildClickListener(this);

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Button temp=(Button) v.findViewById(R.id.footer_1);
		//temp.setClickable(false);
	//	v.setBackgroundColor(Color.CYAN);
		if(test){
			test=false;
			tempParentItems.clear();
		setParent();
		addBitmap(parentItems);
		setChildren();
		adapter.notifyDataSetChanged();
	
		}
		test=true;
		//temp.setClickable(true);
	}
	public void getMovieList(String mood)
	{

		try{
			if(mood.equals("Cheerful")){
				HTTP_Retriver pullFromSite=new HTTP_Retriver();
				String comedy=getString(R.string.comedy)+"&page="+pageCount.toString();
				String animation=getString(R.string.animation)+"&page="+pageCount.toString();
				String family=getString(R.string.family)+"&page="+pageCount.toString();
				String holiday=getString(R.string.holiday)+"&page="+pageCount.toString();
				pullFromSite.execute(comedy,animation,family,holiday);

				JSONParser jparse=new JSONParser();
				jparse.parse(dataList,pullFromSite.get());
			}
			else if(mood.equals("Romantic")){
				HTTP_Retriver pullFromSite=new HTTP_Retriver();
				String romance=getString(R.string.romance)+"&page="+pageCount.toString();
				pullFromSite.execute(romance);
				JSONParser jparse=new JSONParser();
				jparse.parse(dataList,pullFromSite.get());
			}
			else if(mood.equals("Pensive")){
				HTTP_Retriver pullFromSite=new HTTP_Retriver();
				String drama=getString(R.string.drama)+"&page="+pageCount.toString();
				String crime=getString(R.string.crime)+"&page="+pageCount.toString();
				String documentary=getString(R.string.documentary)+"&page="+pageCount.toString();
				String history=getString(R.string.history)+"&page="+pageCount.toString();
				String suspense=getString(R.string.suspense)+"&page="+pageCount.toString();
				String scifi=getString(R.string.scifi)+"&page="+pageCount.toString();
				pullFromSite.execute(drama,crime,documentary,history,suspense,scifi);
				JSONParser jparse=new JSONParser();
				jparse.parse(dataList,pullFromSite.get());
			}else if(mood.equals("Gloomy")){
				HTTP_Retriver pullFromSite=new HTTP_Retriver();
				String drama=getString(R.string.drama)+"&page="+pageCount.toString();
				String sport=getString(R.string.sports)+"&page="+pageCount.toString();
				String fantasy=getString(R.string.fantasy)+"&page="+pageCount.toString();
				pullFromSite.execute(drama,sport,fantasy);
				JSONParser jparse=new JSONParser();
				jparse.parse(dataList,pullFromSite.get());
			}else if(mood.equals("Angry")){
				HTTP_Retriver pullFromSite=new HTTP_Retriver();
				String action=getString(R.string.action)+"&page="+pageCount.toString();
				String drama=getString(R.string.drama)+"&page="+pageCount.toString();
				String war=getString(R.string.war)+"&page="+pageCount.toString();
				pullFromSite.execute(action,drama,war);
				JSONParser jparse=new JSONParser();
				jparse.parse(dataList,pullFromSite.get());
			}else if(mood.equals("Confused")){
				HTTP_Retriver pullFromSite=new HTTP_Retriver();
				String confused=getString(R.string.confused)+"&page="+pageCount.toString();
				pullFromSite.execute(confused);
				JSONParser jparse=new JSONParser();
				jparse.parse(dataList,pullFromSite.get());
			}	

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public ArrayList<MovieData> shuffle(ArrayList<MovieData> list){
		Random r=new Random();
		for(int i=0;i<list.size();i++)
		{
			int temp=r.nextInt(list.size());
			MovieData data=list.remove(temp);
			list.add(0, data);

		}
		return list;

	}

	public void addBitmap(ArrayList<MovieData> list){
		try {
			String url=getString(R.string.configuration);
			HTTP_Retriver pullFromSite=new HTTP_Retriver();
			pullFromSite.execute(url);

			String[] data=pullFromSite.get();
			JSONObject temp=new JSONObject(data[0]);
			JSONObject y=temp.getJSONObject("images");
			String base_url=y.getString("base_url");
			JSONArray jarray=y.getJSONArray("poster_sizes");
			String poster_size=jarray.getString(0);
			Log.d("poster image", base_url+poster_size);
			for(int i=0;i<list.size();i++){
				MovieData movie=list.get(i);
				String imageUrl=base_url+poster_size+movie.poster_path;
				HTTP_Retriver_Bitmap bitmap=new HTTP_Retriver_Bitmap();
				bitmap.execute(imageUrl);
				Bitmap[] finalImage=bitmap.get();
				movie.movieIcon=finalImage[0];
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void setParent(){
		if(dataList.size()<movieSize)
		{
			pageCount++;
			getMovieList(message);
			shuffle(dataList);
		}
		for(int i=0;i<movieSize;i++)
			tempParentItems.add(dataList.remove(0));

		addBitmap(tempParentItems);
		parentItems.addAll(tempParentItems);
		
	}

//	public void UNeedTherapy(View v){
//		
//	}
	public void setChildren(){
		for (int i=0; i<movieSize; i++)
		{
			try {
				ArrayList<String> child = new ArrayList<String>();
				MovieData temp=tempParentItems.get(i);
				String id=temp.id.toString();
				String url="http://api.themoviedb.org/3/movie/"+id+"?api_key=40ddf5a548a42db2b58e8e074f09329a&append_to_response=credits";
				HTTP_Retriver pullFromSite=new HTTP_Retriver();
				pullFromSite.execute(url);
				String[] data=pullFromSite.get();
				JSONObject jobj=new JSONObject(data[0]);

				child.add("OVERVIEW : "+jobj.getString("overview"));
				child.add("RATING :" + tempParentItems.get(i).vote_average.toString());
				child.add("RELEASE DATE :" + tempParentItems.get(i).release_date);
				JSONObject credit=jobj.getJSONObject("credits");
				JSONArray jarr=credit.getJSONArray("cast");
				String cast="CAST :";
				for(int j=0;j<jarr.length();j++){
					JSONObject temp1=jarr.getJSONObject(j);
					cast+=temp1.getString("name")+",";
				}
				child.add(cast);
				childItems.add(child);    

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}
}
