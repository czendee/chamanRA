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


public class FileManager {

	
    private static final String FILENAME_EMPLOYEE_RECORD = "mobileEmployeeRecord.txt"; 
    private static final String TAG = "FileManager"; 
    
    
    
	public String processFileFunctions(Context ctx,String activityPerform, String valueEmployee, String valueParm) {
        String result=null;
        try{
        	System.out.println("processFileFunctions - 1");
            if("00".equals(activityPerform)){//Employee:query employeeId/Key
         	   System.out.println("processFileFunctions - 0.1");
         	   result =reviewFileExist(ctx,FILENAME_EMPLOYEE_RECORD);
         	   System.out.println("processFileFunctions - 0.2");
            }else if("01".equals(activityPerform)){//Employee:query employeeId/Key
        	   System.out.println("processFileFunctions - 1.1");
        	   result = reviewEmployeeIdKey(ctx,valueEmployee, valueParm);
        	   System.out.println("processFileFunctions - 1.2");
           }else if("02".equals(activityPerform)){//Employee:create employeeId/Key
        	           	   
        	   result = createFileEmployeeIdKey(ctx,valueEmployee, valueParm);

           }else if("03".equals(activityPerform)){//Employee:update employeeId with new Key
        	   
        	   result = updateFileEmployeeIdKey(ctx,valueEmployee, valueParm);

           }else if("04".equals(activityPerform)){//Employee:update employeeId with new Status
        	   
        	   result = updateFileEmployeeIdStatus(ctx,valueEmployee, valueParm);

           }else if("05".equals(activityPerform)){//Employee:get Status for employeeId
        	   result =getStatusForEmployeeId(ctx,valueEmployee);
           } else if("06".equals(activityPerform)){//get file Content
        	   System.out.println("processFileFunctions - 1.1");
        	   result = readFromFile(ctx,valueEmployee); //valueEmployee as fileName
        	   
        	   System.out.println("processFileFunctions - 1.2");
           }          
        }catch(Exception e){
        	//error handler

        	System.out.println("processFileFunctions - 5");
        	return null;        	
        }
        System.out.println("processFileFunctions - 6");
        
        return result;
		

	}	//end method
	
	
	/**
	 * reviewFileExist
	 * review if the file exist
	 *
	 *@return String with status: Y- file exist/ N- file doesnt exists
	  *@throws
	 */
	   public String reviewFileExist(Context ctx,String fileName){
		   String methodResult=null;
		   try{
			   System.out.println("reviewFileExist - 1");
			       String result= readFromFile(ctx, fileName);
			       System.out.println("reviewFileExist - 2");
				  if("E10 - File not found".equals(result)){
					  methodResult="N";
				  }else if(  "E20 - Can not read file".equals(result)){
					  methodResult="N";
					  
			      }else{
			    	  methodResult="Y";
			      }
				  System.out.println("reviewFileExist - 3");
			   
		   }catch(Exception e){
			   methodResult="N";
			   Log.e(TAG, "error at reviewFileExist : " + e.toString());
		   }
		   System.out.println("reviewFileExist - 4");
		   return methodResult;
	   }
	   

	/**
	 * reviewEmployeeIdKey
	 * review if the file contains these employeeId and Key
	 *
	 *@param String  employee id 
	 *@param String  key code
	 *@return String with status: YY- employee Id match and key match/
	 *                            YN- employee Id match and key doesnt match/
     *                            NY- employee Id no match nor key  match/
     *                            NN-  error occurred/
	 *@throws
	 */
	  public String reviewEmployeeIdKey(Context ctx,String valueEmployee, String valueKey){
		   String methodResult=null;
		   try{
			   System.out.println("reviewEmployeeIdKey - 1");
        	   String result =reviewFileExist(ctx,FILENAME_EMPLOYEE_RECORD);
        	   System.out.println("reviewEmployeeIdKey - 2");
        	   if(result!=null && "Y".equals(result)){
        		   System.out.println("reviewEmployeeIdKey - 3");
			       result= readFromFile(ctx, FILENAME_EMPLOYEE_RECORD);
			       System.out.println("reviewEmployeeIdKey - 4"+result);
			       
				   if("E10 - File not found".equals(result)){
					  methodResult="NN";
					  System.out.println("reviewEmployeeIdKey - 5");
				   }else if(  "E20 - Can not read file".equals(result)){
					  methodResult="NN";					  
					  System.out.println("reviewEmployeeIdKey - 6");
			       }else{
			    	   System.out.println("reviewEmployeeIdKey - 7:"+valueEmployee);
			    	  //YY- employee Id match and key match/
			    	  //YN- employee Id match and key doesnt match/
			    	  //NY- employee Id no match nor key  match/
			    	  if(result ==null ){
			    		  methodResult="NN";
						   Log.e(TAG, "ERROR at reviewEmployeeIdKey : " +"NO data in the file");
						   System.out.println("ERROR at reviewEmployeeIdKey : " +"NO data in the file");
			    	  }else if( result.indexOf("    "+valueEmployee)>=0){
			    		  System.out.println("reviewEmployeeIdKey - 7.1:"+valueKey);
			    		  if( result.indexOf("   "+valueKey)>=0){
				    		  methodResult="YY";
							   Log.e(TAG, "OK at reviewEmployeeIdKey : " +"match both in the file");
							   if( result.indexOf("SACTIVE")>=0){
								   methodResult="YYA";
							   }else{
								   methodResult="YYI";
							   }
			    		  }else{
				    		  methodResult="YN";
							  Log.e(TAG, "ERROR at reviewEmployeeIdKey : " +"KEY doesn not match in the file");			    			  
			    		  }
			    	  }else{
			    		  methodResult="NY";
						  Log.e(TAG, "ERROR at reviewEmployeeIdKey : " +"Employee Id not match in the file");			    		  
			    	  }
			    	  System.out.println("reviewEmployeeIdKey - 8");				    					    	  
			       }//end else
				   System.out.println("reviewEmployeeIdKey - 9");
        	   }else{//end else fileExists
        		   methodResult="NN";
        		   System.out.println("reviewEmployeeIdKey - 10");
        	   }
        	   System.out.println("reviewEmployeeIdKey - 11");	   
		   }catch(Exception e){
			   methodResult="NN";
			   Log.e(TAG, "error at reviewFileExist : " + e.toString());
		   }
		   System.out.println("reviewEmployeeIdKey - 12");
		   return methodResult;		  
	  }
	
	/**
	 * createFileEmployeeIdKey
	 * Create file and store  new the employeeId and Key
	 * set the employee status to READY
	 *
	 *@param String  employee id 
	 *@param String  key code
	 *@return String with status: YY- employee Id  and key stored/
	 *                            YN- employee Id does exist with a different key/
     *                            NY- employee Id nor key were stored/
     *                            NN-  error occurred/
	 *@throws
	 */
	  public String createFileEmployeeIdKey(Context ctx, String valueEmployee,String valueKey){
		   String methodResult=null;
		   try{
			   System.out.println("createFileEmployeeIdKey - 1");
        	   String result =reviewFileExist(ctx,FILENAME_EMPLOYEE_RECORD);
        	   System.out.println("createFileEmployeeIdKey - 2");
    		   //Do create file
    		   String record=valueKey;//passed as parameter
    		           		   
    		  System.out.println("createFileEmployeeIdKey - 4 -"+record);
    		  
    		   writeToFile( ctx,FILENAME_EMPLOYEE_RECORD, record);
    		   System.out.println("createFileEmployeeIdKey - 5");
    		   methodResult="YY"; 
    		   
/*        	   if(result!=null && "Y".equals(result)){
        		   //do not create file
        		   //update file
        		   System.out.println("createFileEmployeeIdKey - 3");
        		   methodResult="YY";
        		   
        	   }else{//end else fileExists

        		   //Do create file
        		   String record=valueKey;//passed as parameter
        		           		   
        		  System.out.println("createFileEmployeeIdKey - 4 -"+record);
        		  
        		   writeToFile( ctx,FILENAME_EMPLOYEE_RECORD, record);
        		   System.out.println("createFileEmployeeIdKey - 5");
        		   methodResult="YY";        		   
        	   }
*/			    
        	   System.out.println("createFileEmployeeIdKey - 6");
		   }catch(Exception e){
			   methodResult="NN";
			   Log.e(TAG, "error at createFileEmployeeIdKey : " + e.toString());
		   }
		   System.out.println("createFileEmployeeIdKey - 7");
		   return methodResult;			  		  
	  }
    
	/**
	 * updateFileEmployeeIdKey
	 * Update the employeeId info, set a new Key
	 * set the employee status to READY
	 *
	 *@param String  employee id 
	 *@param String  key code
	 *@return String with status: YY-  key stored/
	 *                            YN- employee Id does not exist/
     *                            NY- employee Id / key were not stored/
     *                            NN-  error occurred/
	 *@throws
	 */
	 public String updateFileEmployeeIdKey(Context ctx, String valueEmployee,String valueKey){
		   String methodResult=null;
		   try{
        	   String result =reviewFileExist(ctx,FILENAME_EMPLOYEE_RECORD);
        	   if(result!=null && "Y".equals(result)){
        		   //update the  Key
        		   
        		   
        		   methodResult="YY";
        		   
        	   }else{//end else fileExists

        		           		   
        		   methodResult="NN";        		   
        	   }
        	   

			   
		   }catch(Exception e){
			   methodResult="NN";
			   Log.e(TAG, "error at updateFileEmployeeIdKey : " + e.toString());
		   }
	
		   return methodResult;			  
	  }
    
	/**
	 * updateFileEmployeeIdStatus
	 * Update the employeeId, set a new Status
	 * 
	 *@param String  employee id 
	 *@param String  newStatus
	 *@return String with status: YY-  status set/
	 *                            YN-  employee Id does not exist/
     *                            NY-  current status is the same as requested for new status/
     *                            NN-  error when updating the new status/
	 *@throws
	 */
	 public String updateFileEmployeeIdStatus(Context ctx, String valueEmployee,String newStatus){
		   String methodResult=null;
		   try{
        	   String result =reviewFileExist(ctx,FILENAME_EMPLOYEE_RECORD);
        	   if(result!=null && "Y".equals(result)){
        		   //update the  Status
        		           		   
        		   methodResult="YY";
        		   
        	   }else{//end else fileExists
        		           		   
        		   methodResult="NN";        		   
        	   }
			   
		   }catch(Exception e){
			   methodResult="NN";
			   Log.e(TAG, "error at updateFileEmployeeIdStatus : " + e.toString());
		   }
	
		   return methodResult;			  
	  }
	 
	/**
	 * getStatusForEmployeeId
	 * get the status of the employeeId
	 * 
	 *@param String  employee id 
	 *
	 *@return String with employee status
	 *                            YN-  employee Id does not exist/
     *                            NY-  employee id does not have status/
     *                            NN-  error when querying the new status
     *                            001 - ACTIVE/
	 *			    		      002 - INACTIVE/
	 *			    		      003 - BLOCKED
	 *@throws
	 */    
	 public String getStatusForEmployeeId(Context ctx, String valueEmployee){
		  
		   String methodResult=null;
		   try{
        	   String result =reviewFileExist(ctx,FILENAME_EMPLOYEE_RECORD);
        	   if(result!=null && "Y".equals(result)){
        		   
        	   
			       result= readFromFile(ctx, FILENAME_EMPLOYEE_RECORD);
			   
				  if("E10 - File not found".equals(result)){
					  methodResult="NN";
				  }else if(  "E20 - Can not read file".equals(result)){
					  methodResult="NN";					  
			      }else{
			    	  //YN- employee Id does not exist/
			    	  //NY- employee id doesnot have status/
			    	  //NN- error when querying the new status/
			    	  if(result !=null ){
			    		  methodResult="NN";
						   Log.e(TAG, "ERROR at reviewEmployeeIdKey : " +"NO data in the file");
			    	  }else if( result.indexOf("    "+valueEmployee)>0){	
			    		  int start=result.indexOf("    "+valueEmployee);
			    		  String resultTemp =result.substring(
			    				  start+10,
			    				  result.length()
			    				  );
			    		  
			    		  if( resultTemp.indexOf("  S")>0 ){
				    		  
				    		  int startStatus=result.indexOf("  S");
				    		  methodResult=result.substring(
				    				  startStatus+3,
				    				  startStatus+6
				    				  );
				    		  //status 001 - ACTIVE/
				    		  //       002 - INACTIVE/
				    		  //       003 - BLOCKED
				    		  

							   Log.e(TAG, "OK at getStatusForEmployeeId : " +"STATUS in the file");
			    			  
			    		  }else{
				    		  methodResult="YN";
							  Log.e(TAG, "ERROR at reviewEmployeeIdKey : " +"KEY doesn not match in the file");			    			  
			    		  }
			    	  }else{
			    		  methodResult="NY";
						  Log.e(TAG, "ERROR at reviewEmployeeIdKey : " +"Employee Id not match in the file");			    		  
			    	  }
			    						    					    	  
			      }//end else
			   
        	   }else{//end else fileExists
        		   methodResult="NN";
        		   
        	   }
        	   
		   }catch(Exception e){
			   methodResult="NN";
			   Log.e(TAG, "error at reviewFileExist : " + e.toString());
		   }
	
		   return methodResult;	
		   
	  }	 
    
	 
    private void writeToFile( Context ctx,String fileNameStr, String data) { 
    	try {
    		
    		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(ctx.openFileOutput(fileNameStr, Context.MODE_PRIVATE));
    		outputStreamWriter.write(data);
    		outputStreamWriter.close();
    		}catch (IOException e) {
    			//Log.e(TAG, "File write failed: " + e.toString());
    			System.out.println("writeToFile 1:"+e);
    		} 
    }
    
    //MODE_APPEND
    private void writeToFileAppend( Context ctx,String fileNameStr, String data) { 
    	try {
    		
    		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(ctx.openFileOutput(fileNameStr, Context.MODE_APPEND));
    		outputStreamWriter.write(data);
    		outputStreamWriter.close();
    		}catch (IOException e) {
    			//Log.e(TAG, "File write failed: " + e.toString());
    			System.out.println("writeToFile 1:"+e);
    		} 
    }    
    
    private String readFromFile(Context ctx, String fileNameStr) {
    	String ret = "";
    	try {
    		System.out.println("readFromFile 1:");
    		InputStream inputStream = ctx.openFileInput(fileNameStr);
    		System.out.println("readFromFile 2:");
    		if ( inputStream != null ) {
    			System.out.println("readFromFile 3:");
    			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    			System.out.println("readFromFile 4:");
    			String receiveString = "";
    			StringBuilder stringBuilder = new StringBuilder();
    			System.out.println("readFromFile 5:");
    			while ( (receiveString = bufferedReader.readLine()) != null ) { 
    				stringBuilder.append(receiveString);
    			}
    			System.out.println("readFromFile 6:");
    			inputStream.close();
    			
    			ret = stringBuilder.toString();
    			System.out.println("readFromFile 7:"+ret);
    			
    		}
    	}         catch (FileNotFoundException e) {
    		Log.e(TAG, "File not found: " + e.toString());
    		    ret="E10 - File not found";
    	} catch (IOException e) {
    		Log.e(TAG, "Can not read file: " + e.toString());
    		    ret="E20 - Can not read file";
    	}
    	System.out.println("readFromFile 8:");
    	return ret;
    } 
    
    
    
	
	/**
	 *  
	 * Verify if the  employee id and Key exist and are registered in the mobile 
	 * application
	 *
	 *@param String  employee id 
	 *@param String  key code
	 *@return String with the status for the employee id and Key.
	 *@throws Exception
	 */
	
	public String verifyEmployeeAndKeyExistsInMobile(String valueEmployee, String valueKey) throws Exception {
	
		String statusEmployee=null;
		try {
			
			
		} catch (Exception e) {
		    
		    throw new Exception("Other Exception", e);
		    
		}
		return statusEmployee;
	}
	
}
