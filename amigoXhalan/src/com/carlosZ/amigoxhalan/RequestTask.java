package com.carlosZ.amigoxhalan;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream.*;

import java.io.FileInputStream.*;


import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.content.Context; 
import android.os.AsyncTask;
import android.util.Log; 
import android.view.View;
import android.widget.TextView;


import android.content.Intent;


class RequestTask extends AsyncTask<TextView, String, String>{
	TextView t;
	String result = "fail";
	
    @Override
    protected String doInBackground(TextView... params) {
    	System.out.println("doInBackground - 1");
    	this.t = params[0];
    	System.out.println("doInBackground - 2");
		return GetSomething();
		

    }

	final String GetSomething()
	{
		System.out.println("GetSomething - 1");
		
		Constants constantValues=new Constants();
		String url = constantValues.URL_GET_BUILDING_ALL_INFO+"68";
		BufferedReader inStream = null;
		System.out.println("GetSomething - 2");
		try {
			HttpClient httpClient = new DefaultHttpClient();
			System.out.println("GetSomething - 3");
			HttpGet httpRequest = new HttpGet(url);
			System.out.println("GetSomething - 4");
			HttpResponse response = httpClient.execute(httpRequest);
			System.out.println("GetSomething - 5");
			inStream = new BufferedReader(
				new InputStreamReader(
					response.getEntity().getContent()));
			System.out.println("GetSomething - 6");
			StringBuffer buffer = new StringBuffer("");
			String line = "";
			System.out.println("GetSomething - 7");
			String NL = System.getProperty("line.separator");
			System.out.println("GetSomething - 8");
			while ((line = inStream.readLine()) != null) {
				buffer.append(line + NL);
			}
			System.out.println("GetSomething - 9");
			inStream.close();
			System.out.println("GetSomething - 10");
			result = buffer.toString();			
			System.out.println("GetSomething - 11");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("GetSomething - 12 error");
			e.printStackTrace();
		} finally {
			System.out.println("GetSomething - 13");
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("GetSomething - 14");
		return result;
	}
	
    @Override
    protected void onPostExecute(String result) {
    	System.out.println("onPostExecute - 1");
    	t.setText(result);
    	System.out.println("onPostExecute - 2");
    }
    

}
