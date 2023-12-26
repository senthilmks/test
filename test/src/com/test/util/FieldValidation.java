package com.test.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import net.sf.json.JSONObject;

public class FieldValidation {
	
	public final int REQUIRED =1;
	public final int NUMERIC =2;
	public final int ALPHABETS = 3;
	public final int ALPHANUM = 4;
	public final int EMAIL = 5;
	public final int PASSWORD = 6;
	public final int SELECTED =7;
	public final int IS_DATE_VALID =8;
	public final int IS_PERCENTAGE =9;
	public final int DOB =10;
	public final int NAME =11;	
	public final int MOBILENO =12;
	public final int CHECKED =13;
	public final int DOUBLE =14;
	
	
	private String errorMessage = "";
	private int counter = 0;
	
	java.util.HashMap errorList = new HashMap();;
	
	
	
	public java.util.HashMap getErrorList() {
		return errorList;
	}
	public void setErrorList(java.util.HashMap errorList) {
		this.errorList = errorList;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}	
	public boolean validate(int type,String fieldValue,String fieldName,String fieldDesc, int count,int modeOfDisplay)
	{
		boolean isValid = true;
		
		if(type==REQUIRED)
		{
			if(fieldValue==null || fieldValue.trim().length()==0)
			{
				counter++;
				//errorMessage=errorMessage.trim()+counter+". Enter "+fieldName+"<br>";
				if (modeOfDisplay==1) {
					errorList.put(fieldName+"Ind", "Enter "+fieldDesc);
				}
				else {
					errorMessage=errorMessage.trim()+counter+". Enter "+fieldDesc+"<br>";
				}
				isValid=false;
			}
		}
		if(type==ALPHABETS)
		{
			if(fieldValue!=null && fieldValue.trim().length()>0)
			{
				
				boolean isAlphabets = fieldValue.matches("[a-zA-Z]");
				
				if(!isAlphabets) {				
				counter++;
				//errorMessage=errorMessage.trim()+counter+". Enter "+fieldName+"<br>";
				if (modeOfDisplay==1) {
					errorList.put(fieldName+"Ind", "Enter only Alphabets in "+fieldDesc);
				}
				else {
					errorMessage=errorMessage.trim()+counter+". Enter only Alphabets in "+fieldDesc+"<br>";
				}
				isValid=false;
				}
			}
		}
		if(type==CHECKED)
		{
			if(fieldValue==null || fieldValue.trim().length()==0)
			{
				counter++;
				errorMessage=errorMessage.trim()+" "+counter+". Select "+fieldDesc+"<br>";
				isValid=false;
			}
		}
		if(type==NUMERIC)
		{
			if(fieldValue!=null && fieldValue.trim().length()>0) 
			{
				for(int i=0;i<fieldValue.length();i++)
				{
					if(! (Character.isDigit(fieldValue.charAt(i))))
					{
						counter++;
						errorMessage=errorMessage.trim()+" "+counter+". "+fieldDesc+" Should contain only numbers<br>";
						isValid=false;
						break;
					}
				}
				
			}
		}
		
		if(type==DOUBLE)
		{
			if(fieldValue!=null && fieldValue.trim().length()>0)
			{
				for(int i=0;i<fieldValue.length();i++)
				{
					
					if(! (Character.isDigit(fieldValue.charAt(i))) && !(String.valueOf(fieldValue.charAt(i)).equalsIgnoreCase(".")) )
					{
						
						counter++;
						errorMessage=errorMessage.trim()+" "+counter+". "+fieldDesc+" Should contain only numbers and .<br>";
						isValid=false;
						break;
					}
				}
				
			}
		}
		if(type==PASSWORD)
		{
			if(fieldValue!=null && fieldValue.trim().length()>0)
			{
				if(fieldValue.trim().length()<5)
				{
					counter++;
					
					if (modeOfDisplay==1) {
						errorList.put(fieldName+"Ind", fieldDesc+"  should be minimum 5 characters");
					}
					else {
						errorMessage=errorMessage.trim()+counter+". "+fieldDesc+" should be minimum 5 characters <br>";
					}
					
					//errorMessage=errorMessage.trim()+" "+counter+". "+fieldDesc+" should be minimum 5 characters"+"<br>";
					isValid=false;
				}
			}
		}
		
		if(type==SELECTED)
		{
			if(fieldValue!=null && fieldValue.trim().length()>0)
			{
				if( (fieldValue.trim().indexOf("Select")!=-1) || (fieldValue.trim().equalsIgnoreCase("-1")) || (fieldValue.trim().trim().length()==0) || (fieldValue.trim().equalsIgnoreCase("NNN")) )
				{
					counter++;
					errorMessage=errorMessage.trim()+" "+counter+". "+"Select "+fieldDesc+"<br>";
					isValid=false;
				}
			}
			else
			{
				counter++;
				errorMessage=errorMessage.trim()+" "+counter+". "+"Select "+fieldDesc+"<br>";
				isValid=false;
			}
		}
		
		if(type==IS_DATE_VALID)
		{
			if(fieldValue!=null && fieldValue.trim().length()>0)
			{
				try
				{
					Date d =null;
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");  // define ur format here.
	                sdf.setLenient(false); // important to set this to false.
	                d = sdf.parse(fieldValue);
					isValid=true;
				}
				catch(Exception e)
				{
					counter++;
					errorMessage=errorMessage.trim()+" "+counter+". "+"Invalid "+fieldDesc+"<br>";
					isValid=false;
				}
			}
		}
		
		if(type==DOB) 
		{
			if(fieldValue!=null && fieldValue.trim().length()>0)
			{
				try
				{
					Services.getDate_DD_MMM_YYYY(fieldValue);
					System.out.println("emanager >> Field Validation >> fieldValue >> "+fieldValue);
					System.out.println("emanager >> Field Validation >> Services.getCurDate_MMDDYYYY() "+Services.getCurDate_MMDDYYYY());
					isValid=Services.isEndDateBeforeStartDate(fieldValue,Services.getCurDate_MMDDYYYY());
					System.out.println("emanager >> Field Validation >> isValid DOB >> "+isValid);
					if(isValid)
					{
						counter++;
						errorMessage=errorMessage.trim()+" "+counter+". "+fieldDesc+" should be less than Current Date"+"<br>";
						isValid=false;
					}
					else
					{
						isValid=true;
					}
				}
				catch(Exception e)
				{
					counter++;
					errorMessage=errorMessage.trim()+" "+counter+". "+"Invalid "+fieldDesc+"<br>";
					isValid=false;
				}
			}
		}
		
		if(type==IS_PERCENTAGE)
		{
			if(fieldValue!=null && fieldValue.trim().length()>0)
			{
				if(Integer.parseInt(fieldValue.trim())>100)
				{
					counter++;
					errorMessage=errorMessage.trim()+" "+counter+". "+"Enter "+fieldDesc+" less than or equal to 100 <br>";
					isValid=false;
				}
			}
		}
		if(type==NAME)
		{
			try
			{
				if(fieldValue!=null && fieldValue.trim().length()>3)
				{
					System.out.println("emanager >> Field Validation >> fieldValue >> Name >> "+fieldValue);
					if(fieldValue.trim().substring(1, 2).equals(".") || fieldValue.trim().substring(2, 3).equals("."))
					{
						counter++;
						errorMessage=errorMessage.trim()+" "+counter+". "+fieldDesc+" should have initial(s) at the end"+"<br>";
						isValid=false;
					}
				}
			}			
			catch(Exception e)
			{
				counter++;
				errorMessage=errorMessage.trim()+" "+counter+". "+"Invalid "+fieldDesc+"<br>";
				isValid=false;
			}
		}
		if(type==MOBILENO)
		{
			if(fieldValue!=null && fieldValue.trim().length()>0)
			{
				if(fieldValue.trim().length()<10)
				{
					counter++;
					errorMessage=errorMessage.trim()+" "+counter+". "+"Enter "+fieldDesc+" correctly. should be 10 digits.<br>";
					isValid=false;
				}
			}
		}
		if(type==EMAIL)
		{
			if(fieldValue!=null && fieldValue.trim().length()>0)
			{			
				String[] mailIdList = fieldValue.split("@");
			
				String domain = mailIdList[1];
				
				if(!domain.trim().contains("srmist"))
				{
					counter++;
					errorMessage=errorMessage.trim()+" "+counter+". "+"Enter "+fieldDesc+" correctly. should be Official mail ID.<br>";
					isValid=false;
				}
			}
		}
		return isValid;
	}
	
	public String getJSONErrorObj(){
		
		JSONObject JSONErrorObj = new JSONObject();
		
		JSONErrorObj = JSONObject.fromObject(this.errorList);
		
		return JSONErrorObj.toString();
		
	}
	/* Set Other Validation */
	public void setOtherValidation(String errorMsg, int count)
	{
		counter = count;
		counter++;
		this.errorMessage = this.errorMessage + "" + counter 
		+ ". "+ errorMsg + "  <br>";
		
	}
}

