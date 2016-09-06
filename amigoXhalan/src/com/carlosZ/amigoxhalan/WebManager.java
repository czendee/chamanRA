package com.carlosZ.amigoxhalan;

import java.io.BufferedReader;
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
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.content.Context; 
import android.util.Log; 


import android.content.Intent;


public class WebManager {

	
    private static final String FILENAME_EMPLOYEE_RECORD = "mobileEmployeeRecord.txt"; 
    private static final String TAG = "FileManager"; 
    
    private Constants constantValues=new Constants();
    
    
	public String processWebFunctions(String activityPerform, String valueInput, String valueParm) {
        String result=null;
        try{
        	
        	System.out.println("processWebFunctions - 1");
            if("00".equals(activityPerform)){//send web request to get employeeId/Key
         	   System.out.println("processWebFunctions - 0.1");
//         	   result =requestGetEmployeeInfo(valueInput, valueParm);
         	   System.out.println("processWebFunctions - 0.2");
         	   
            }else if("01".equals(activityPerform)){//send web request for building unit
        	   System.out.println("processWebFunctions - 1.1");
        	   result = requestBuildingUnit(valueInput, valueParm);
        	   System.out.println("processWebFunctions - 1.2");
           }else{
        	   System.out.println("processWebFunctions - else");
        	   
           }
              	     	   
     	            
        }catch(Exception e){
        	//error handler

        	System.out.println("processWebFunctions - 5"+e);
        	return null;        	
        }
        System.out.println("processWebFunctions - 6");
        
        return result;
		

	}	//end method
	
	

	

	
	
	/**
	 *  
	 * Send web request to Building Units 
	 *
	 *@param String  employee id 
	 *@param String  key the metric/notification code
	 *@return String with information for the Notification/Metric information
	 *@throws Exception
	 */
	
	public String requestBuildingUnit(String valueParm, String valueKey) throws Exception {
	
		String infoMetric=null;
		try {
			//valueKey contains the notification/metric code
			
			String htmlInfoMetric= getHtml(constantValues.URL_GET_BUILDING_ALL_INFO+valueParm); // concatenate url with the 68 passed , id for the book
			//String htmlInfoMetric= getHtml(constantValues.URL_MONITOR_METRIC
			//                           +"&employee="+valueParm+
			//                           +"&metricNotifCode="+valueKey);				
			System.out.println("requestBuildingUnit - 1:"+htmlInfoMetric);
			if(htmlInfoMetric!=null){
				//infoMetric= parseResult(constantValues.GET_BUILDING_ALL_DATA,htmlInfoMetric,valueParm, valueKey);
				System.out.println("requestBuildingUnit - 2:");
				
				ParseDataManager pdMgr = new ParseDataManager();
				System.out.println("requestBuildingUnit - 3:");
		    	String infoToDisplay= pdMgr.parseDataFunctions(htmlInfoMetric, Constants.GET_BUILDING_ALL_DATA, "", "");
		    	infoMetric=infoToDisplay;
		    	System.out.println("requestBuildingUnit - 4:"+infoMetric);
			}else{
				infoMetric= "ERROR:requestMonitorMetric html is null";
			}	
			
		} catch (Exception e) {
		    
		    throw new Exception("Other Exception"+e.toString(), e);
		    
		}
		return infoMetric;
	}
	
	

	
	
	/**
	 *  
	 * Get the html tags 
	 *
	 *@param String  url 
	 *@return String with html tags requested
	 * @throws URISyntaxException 
	 *@throws Exception
	 */
	
    public static String getHtml(String url)
    throws ClientProtocolException, IOException, URISyntaxException
    {

    	
    	
    	
        
/*    	System.out.println("getHtml si 1.1");
    		
		HttpClient httpClient = new DefaultHttpClient();
		
		System.out.println("getHtml si 1.2");
		HttpContext localContext = new BasicHttpContext();
		
		System.out.println("getHtml si 1.3"+url);
		HttpGet httpGet = new HttpGet(url);
		System.out.println("getHtml si 1.4");
		HttpResponse response = httpClient.execute(httpGet, localContext);
		System.out.println("getHtml si 1.5");*/
		System.out.println("getHtml si 1.5.K");
        HttpClient client = new DefaultHttpClient();
        System.out.println("getHtml si 1.5.K2"+url);
        URI website = new URI(url);
        System.out.println("getHtml si 1.5.K3");
        HttpGet request = new HttpGet();
        System.out.println("getHtml si 1.5.K4");
        request.setURI(website);
        System.out.println("getHtml si 1.5.K5");
        HttpResponse response = client.execute(request);
        System.out.println("getHtml si 1.5.K6");
		String result = "";
		System.out.println("getHtml si 1.6");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
		     response.getEntity().getContent()));
		System.out.println("getHtml si 1.7");
		result = reader.readLine();
	
	    
		return result;
		
    }//end gethtml
    
    
    
    
	
}
