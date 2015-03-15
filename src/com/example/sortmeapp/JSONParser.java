package com.example.sortmeapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

	public ArrayList<MovieData> parse(ArrayList<MovieData> list,String[] data)
	{
		String x="";
		ArrayList<MovieData> movieList=list;
		for(int j=0;j<data.length;j++){
			try {
				JSONObject y=new JSONObject(data[j]);
				JSONArray jarray=y.getJSONArray("results");
				for(int i=0;i<jarray.length();i++)
				{
					JSONObject jobj=jarray.getJSONObject(i);
					MovieData md=new MovieData();
					md.backdrop_path=jobj.getString("backdrop_path");
					md.id=jobj.getInt("id");
					md.original_title=jobj.getString("original_title");
					md.release_date=jobj.getString("release_date");
					md.poster_path=jobj.getString("poster_path");
					md.title=jobj.getString("title");
					md.vote_average=jobj.getInt("vote_average");
					md.vote_count=jobj.getInt("vote_count");
					if(!movieList.contains(md))
					movieList.add(md);
					else
					Log.d("Movie name", md.title);
				}
				Integer t=movieList.size();
				Log.d("movie no total", t.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return movieList;
	}

}
