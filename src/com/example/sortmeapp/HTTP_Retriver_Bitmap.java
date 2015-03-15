package com.example.sortmeapp;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
public class HTTP_Retriver_Bitmap extends AsyncTask<String, Void, Bitmap[]>{

	private DefaultHttpClient client = new DefaultHttpClient(); 

	public String retrieve(String url) {

		HttpGet getRequest = new HttpGet(url);

		try {

			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				Log.w(getClass().getSimpleName(), "Error " + statusCode + " for URL " + url);
				return null;
			}

			HttpEntity getResponseEntity = getResponse.getEntity();

			if (getResponseEntity != null) {
				return EntityUtils.toString(getResponseEntity);
			}

		}
		catch (Exception e) {
			getRequest.abort();
			Log.w(getClass().getSimpleName(), "Error for URL " + url,e);
			e.printStackTrace();
		}

		return null;

	}


	public InputStream retrieveStream(String url) {

		HttpGet getRequest = new HttpGet(url);

		try {

			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				Log.w(getClass().getSimpleName(), "Error " + statusCode + " for URL " + url);
						return null;
			}

			HttpEntity getResponseEntity = getResponse.getEntity();
			return getResponseEntity.getContent();

		}
		catch (IOException e) {
			getRequest.abort();
			Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
		}

		return null;

	}

	public Bitmap retrieveBitmap(String url) throws Exception {

		InputStream inputStream = null;
		try {
			inputStream = this.retrieveStream(url);
			final Bitmap bitmap = BitmapFactory.decodeStream(new FlushedInputStream(inputStream));
			return bitmap;
		}
		finally {
			Utils.closeStreamQuietly(inputStream);
		}

	}


	@Override
	protected Bitmap[] doInBackground(String... params) {
		
		Bitmap[] data=new Bitmap[params.length];
		for(int i=0;i<data.length;i++)
			try {
				data[i]=retrieveBitmap(params[i]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return data;
	}


}


