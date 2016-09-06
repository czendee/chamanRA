package com.carlosZ.amigoxhalan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream.*;

import java.io.FileInputStream.*;


import java.io.FileNotFoundException;
import android.content.Context; 
import android.util.Log; 


import android.content.Intent;


public class ParseDataManager {

	
    private static final String FILENAME_EMPLOYEE_RECORD = "mobileEmployeeRecord.txt"; 
    private static final String TAG = "FileManager"; 
    
    
    
	public String parseDataFunctions(String dataResult,String activityPerform, String valueEmployee, String valueParm) {
        String result=null;
        try{
        	System.out.println("parseDataFunctions - 1");
        	
            if(Constants.GET_BUILDING_ALL_DATA.equals(activityPerform)){//GET_ALL INFORMATION FOR A BUILDING:query building Id
          	   System.out.println("parseDataFunctions - 0.1");
          	   result =parseDataGetBuildingAllInfo(dataResult);
          	   
          	   System.out.println("parseDataFunctions - 0.2");
             }
          	  else{
         	   System.out.println("parseDataFunctions - 1.7");
            }      
            
            
            
        }catch(Exception e){
        	//error handler

        	System.out.println("parseDataFunctions - 5");
        	return null;        	
        }
        System.out.println("parseDataFunctions - 6");
        
        return result;
		

	}	//end method
	
	
	/**
	 * parseDataGetBuildingAllInfo
	 * parse the building all info
	 *
	 *@return String with parsed data in piecces of data
	  *@throws
	 */
	   public String parseDataGetBuildingAllInfo( String resultData){
		   String methodResult=null;
		   try{
			   System.out.println("parseDataGetBuildingAllInfo - 1");
//			   methodResult= Constants.TEMPORAL_FILE_TEST_EMPLOYEE_INFO;
			       System.out.println("parseDataGetBuildingAllInfo - 2");
			       
			       methodResult=resultData;
			       
			    	if(methodResult.indexOf("buildunits[")>=0){
			    		
				    	//take out the ]endCData
				    	if(methodResult.indexOf("]ENDbuildunits")>0){
				    		methodResult=methodResult.substring(
				    				methodResult.indexOf("buildunits["),
				    				methodResult.indexOf("]ENDbuildunits")+9//characters
				    								) ;// the final marker 
				    		System.out.println("parseResult - data found:"+methodResult);
				    		 
				    	}else{
				    		//error
				    		System.out.println("parseResult - ERROR no right data:final");
				    		methodResult="N no right data:final";
				    	}
				    	
			    	}else{
			    		//error
			    		System.out.println("parseResult - ERROR no right data:initial");
			    		methodResult="N ERROR no right data:initial";
			    	}
			    	
			   
		   }catch(Exception e){
			   methodResult="N error"+e.toString();
			   Log.e(TAG, "error at reviewFileExist : " + e.toString());
		   }
		   System.out.println("reviewFileExist - 4");
		   return methodResult;
	   }
	   

		/**
		 * parseDataCollectorsData
		 * parse the Collectors data File
		 *
		 *@return String with parsed data in pieces of data
		  *@throws
		 */
		   public String parseDataCollectorsData( String resultData){
			   String methodResult=null;
			   try{
				   System.out.println("parseDataCollectorsData - 1");

				       System.out.println("parseDataCollectorsData - 2");
				       
				       methodResult=resultData;
				       
				    	if(methodResult.indexOf("coll[")>=0){
				    		
					    	//take out the ]endCData
					    	if(methodResult.indexOf("]endColl")>0){
					    		methodResult=methodResult.substring(
					    				methodResult.indexOf("coll["),
					    				methodResult.indexOf("]endColl")+8//characters
					    								) ;// the final marker 
					    		 
					    		 
					    	}else{
					    		//error
					    		System.out.println("parseResult - ERROR no right data:final");
					    		methodResult="N";
					    	}
					    	
				    	}else{
				    		//error
				    		System.out.println("parseResult - ERROR no right data:initial");
				    		methodResult="N";
				    	}
				    	
				   
			   }catch(Exception e){
				   methodResult="N";
				   Log.e(TAG, "error at parseDataCollectorsData : " + e.toString());
			   }
			   System.out.println("parseDataCollectorsData - 4");
			   return methodResult;
		   }

			/**
			 * parseDataSystemsData
			 * parse the Systems data File
			 *
			 *@return String with parsed data in pieces of data
			  *@throws
			 */
			   public String parseDataSystemsData( String resultData){
				   String methodResult=null;
				   try{
					   System.out.println("parseDataSystemsData - 1");

					       System.out.println("parseDataSystemsData - 2");
					       
					       methodResult=resultData;
					       
					    	if(methodResult.indexOf("colL[")>=0){
					    		
						    	//take out the ]endCData
						    	if(methodResult.indexOf("]endColL")>0){
						    		methodResult=methodResult.substring(
						    				methodResult.indexOf("colL["),
						    				methodResult.indexOf("]endColL")+8//characters
						    								) ;// the final marker 
						    		 
						    		 
						    	}else{
						    		//error
						    		System.out.println("parseResult - ERROR no right data:final");
						    		methodResult="N";
						    	}
						    	
					    	}else{
					    		//error
					    		System.out.println("parseResult - ERROR no right data:initial");
					    		methodResult="N";
					    	}
					    	
					   
				   }catch(Exception e){
					   methodResult="N";
					   Log.e(TAG, "error at parseDataSystemsData : " + e.toString());
				   }
				   System.out.println("parseDataSystemsData - 4");
				   return methodResult;
			   }		   
				   
			/**
			 * parseDataMetricsData
			 * parse the Collectors Metrics data File
			 *
			 *@return String with parsed data in pieces of data
			  *@throws
			 */
			   public String parseDataMetricsData( String resultData, String collectorId){
				   String methodResult=null;
				   try{
					   System.out.println("parseDataMetricsData - 1");

					       System.out.println("parseDataMetricsData - 2:"+resultData);
					       
					       methodResult=resultData;
					       
					    	if(methodResult.indexOf("coll"+collectorId+"[")>=0){
					    		
						    	//take out the ]endCData
						    	if(methodResult.indexOf("]"+collectorId+"endColl")>0){
						    		methodResult=methodResult.substring(
						    				methodResult.indexOf("coll"+collectorId+"["),
						    				methodResult.indexOf("]"+collectorId+"endColl")+12//characters
						    								) ;// the final marker 
						    		 
						    		 
						    	}else{
						    		//error
						    		System.out.println("parseResult - ERROR no right data:final");
						    		methodResult="N";
						    	}
						    	
					    	}else{
					    		//error
					    		System.out.println("parseResult - ERROR no right data:initial");
					    		methodResult="N";
					    	}
					    	
					   
				   }catch(Exception e){
					   methodResult="N";
					   Log.e(TAG, "error at parseDataCollectorsData : " + e.toString());
				   }
				   System.out.println("parseDataCollectorsData - 4");
				   return methodResult;
			   }
	
}
