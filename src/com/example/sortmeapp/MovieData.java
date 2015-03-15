package com.example.sortmeapp;

import android.graphics.Bitmap;

public class MovieData {
	String backdrop_path;
	Integer id;
	String original_title;
	String release_date;
	String poster_path;
	String title;
	Integer vote_average;
	Integer vote_count;
	Bitmap movieIcon;

	public boolean equals(Object o){
		MovieData comp=(MovieData)o;
		if(this.title.equals(comp.title))
			return true;
		else
			return false;
	}
	
	public int hashCode(){
		return title.hashCode();
	}
}
