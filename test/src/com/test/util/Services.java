package com.test.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class Services {
	
	public static String removeLeadingZeroes(String value) {
	     if(value.trim().length()>6){
		while (value.length() > 1 && value.indexOf("0")==0){value = value.substring(1);}}
	         return value;
	}

	public static String setEmptyIfNull(String str)  
	{
		if(str==null)
		{
			str="";
		}
		return str;
	}
	public static String getMonthInWord(int m)
	{
		// for adding next month by anis
		if(m==12)
		{
			m=0;
		} // for adding next month by anis
		String months[] = new String [12];
		months[0]="JAN";
		months[1]="FEB";
		months[2]="MAR";
		months[3]="APR";
		months[4]="MAY";
		months[5]="JUN";
		months[6]="JUL";
		months[7]="AUG";
		months[8]="SEP";
		months[9]="OCT";
		months[10]="NOV";
		months[11]="DEC";
		return months[m];		
	}
	
	public static String getDayInWord(int d)
	{
		// for adding next month by anis
		if(d==7)
		{
			d=0;
		} // for adding next month by anis
		String days[] = new String [7];
		days[0]="SUN";
		days[1]="MON";
		days[2]="TUE";
		days[3]="WED";
		days[4]="THU";
		days[5]="FRI";
		days[6]="SAT";
		return days[d];		
	}
	
	public static int getDaysInMonthNew(int month, int Year)
	{
		int noOfDays=0;
		//System.out.println("CLIB : Services.getAddedDate month sundara test>> "+month);
		//System.out.println("CLIB : Services.getAddedDate Year sundara test>> "+Year);
		//Date cDate = new Date(inDate);
		Calendar cal = new GregorianCalendar(Year, month, 1);
		noOfDays=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//System.out.println("CLIB : Services.getAddedDate noOfDays >> "+noOfDays);
		return noOfDays-1;
	}
	
	public static String getCurDate_MMDDYYYY()
	{
		String curDate="";
		Date cDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		String curMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
		if(curMonth.length()<2)
		{
			curMonth="0"+curMonth;
		}
		//System.out.println("CLIB : Services.getCurDate_MMDDYYYY >> curMonth >> "+curMonth);
		String curDay =(String.valueOf(cal.get(Calendar.DATE)));
		if(curDay.length()<2)
		{
			curDay="0"+curDay;
		}
		curDate=(curMonth)+"/"+(curDay)+"/"+String.valueOf(cal.get(Calendar.YEAR));
		//System.out.println("CLIB : Services.getCurDate_MMDDYYYY >> curDate >> "+curDate);
		//curDate="12/20/2014";
		return curDate;
	}
	
	public static String getCurDate_DDMMYYYY()
	{
		String curDate="";
		Date cDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		String curMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
		if(curMonth.length()<2)
		{
			curMonth="0"+curMonth;
		}
		//System.out.println("CLIB : Services.getCurDate_MMDDYYYY >> curMonth >> "+curMonth);
		String curDay =(String.valueOf(cal.get(Calendar.DATE)));
		if(curDay.length()<2)
		{
			curDay="0"+curDay;
		}
		curDate=(curDay)+"/"+(curMonth)+"/"+String.valueOf(cal.get(Calendar.YEAR));
		//System.out.println("CLIB : Services.getCurDate_MMDDYYYY >> curDate >> "+curDate);
		return curDate;
	}
	
	public static String getCurDate()
	{
		String curDate="";
		Date cDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		/*String curMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
		if(curMonth.length()<2)
		{
			curMonth="0"+curMonth;
		}*/
		String curDay =(String.valueOf(cal.get(Calendar.DATE)));
		if(curDay.length()<2)
		{
			curDay="0"+curDay;
		}
		curDate=curDay+"/"+(getMonthInWord(cal.get(Calendar.MONTH)))+"/"+(String.valueOf(cal.get(Calendar.YEAR)));
		//curDate=curDay+"-"+curMonth+"-"+(String.valueOf(cal.get(Calendar.YEAR)));
		//curDate=(String.valueOf(cal.get(Calendar.DATE)))+"-"+(getMonthInWord(cal.get(Calendar.MONTH)))+"-"+(String.valueOf(cal.get(Calendar.YEAR)));
		return curDate;
	}
	
	public static String getDayOfDate(String inDate)
	{
		String result="";
		//System.out.println("CLIB : Services.getDate_DD_MMM_YYYY input Date >> "+inDate);
		Date cDate = new Date(inDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		result=(String.valueOf(cal.get(Calendar.DATE)));
		//System.out.println("CLIB : Services.getDayOfDate Day >> "+result);
		return result;
	}
	
	public static String getDayNameOfDate(String inDate) 
	{
		String result="";
		//System.out.println("CLIB : Services.getDate_DD_MMM_YYYY input Date >> "+inDate);
		Date cDate = new Date(inDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		result=(String.valueOf(cal.get(Calendar.DAY_OF_WEEK)));
		//System.out.println("CLIB : Services.getDayOfDate Day >> "+result);
		return result;
	}
	
	public static boolean checkIsDateSaturday(String inDate)
	{
		System.out.println("CLIB : Services.checkIsDateSaturday input Date >> "+inDate);
		Date cDate = new Date(inDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);  
		System.out.println("CLIB : Services.checkIsDateSaturday input dayOfWeek >> "+dayOfWeek);
		if(dayOfWeek==Calendar.SATURDAY)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static String getMonthOfDate(String inDate)
	{
		String result="";
		//System.out.println("CLIB : Services.getDate_DD_MMM_YYYY input Date >> "+inDate);
		Date cDate = new Date(inDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		result=(String.valueOf(cal.get(Calendar.MONTH)));
		//System.out.println("CLIB : Services.getDayOfMonth Month >> "+result);
		return result;
	}
	
	public static String getLastDateOfMonth(String inDate)
	{
		String result="";
		//System.out.println("CLIB : Services.getDate_DD_MMM_YYYY input Date >> "+inDate);
		Date cDate = new Date(inDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));  
		result=(String.valueOf(cal.get(Calendar.MONTH)+1))+"/"+((String.valueOf(cal.get(Calendar.DATE)))+"/"+String.valueOf(cal.get(Calendar.YEAR)));
		//System.out.println("CLIB : Services.getDayOfMonth Month >> "+result);
		return result;
	}
	
	public static String getYearOfDate(String inDate)
	{
		String result="";
		//System.out.println("CLIB : Services.getDate_DD_MMM_YYYY input Date >> "+inDate);
		Date cDate = new Date(inDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		result=(String.valueOf(cal.get(Calendar.YEAR)));
		//System.out.println("CLIB : Services.getDayOfYear Year >> "+result);
		return result;
	}
	
	public static String getCurHour()
	{
		String curHour="0";
		int curHourInt=0;
		Date cDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		curHourInt = cal.get(Calendar.HOUR);
		if(curHourInt==0)
		{
			curHourInt=12;
		}
		curHour=String.valueOf(curHourInt);
		
		if(curHour.trim().length()<2)
		{
			curHour="0"+curHour;
		}
		//System.out.println("CLIB : Services.getCurHour >>"+curHour);
		return curHour;
	}
	public static String getCurMin()
	{
		String curMin="0";
		Date cDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		curMin=String.valueOf(cal.get(Calendar.MINUTE));
		if(curMin.trim().length()<2)
		{
			curMin="0"+curMin;
		}
		return curMin;
	}
	public static String getAMPM()
	{
		String meridian="AM";
		Date cDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		//meridian=String.valueOf(cal.get(Calendar.AM_PM));
		//System.out.println("CLIB : Services.getAMPM String.valueOf(cal.get(Calendar.AM_PM)) >>"+String.valueOf(cal.get(Calendar.AM_PM)));
		//System.out.println("CLIB : Services.getAMPM String.valueOf(cal.get(Calendar.AM))) >>"+String.valueOf(cal.get(Calendar.AM)));
		//System.out.println("CLIB : Services.getAMPM >>"+meridian);
		if(Calendar.AM_PM>0)
			return "PM";
		else
			return "AM";
	}
	public static String getCurSec()
	{
		String curSec="0";
		Date cDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		curSec=String.valueOf(cal.get(Calendar.SECOND));
		if(curSec.trim().length()<2)
		{
			curSec="0"+curSec;
		}
		return curSec;
	}
	public static String getCurTime()
	{
		String curTime="";
		String meridian="AM";
		Date cDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		//cal.set(Calendar.HOUR,0);
		//cal.set(Calendar.MINUTE,0);
		//cal.set(Calendar.SECOND,0);
		if(cal.get(Calendar.HOUR) >12 ||  cal.get(Calendar.AM_PM)>0)
			meridian=" PM";
		else
			meridian=" AM";
		curTime=(String.valueOf(cal.get(Calendar.HOUR)))+":"+(cal.get(Calendar.MINUTE))+":"+(String.valueOf(cal.get(Calendar.SECOND))+meridian);
		return curTime;
	}
	
	public static String getDate_DD_MMM_YYYY(String inDate)
	{
		String resDate="";
		//System.out.println("CLIB : Services.getDate_DD_MMM_YYYY input Date >> "+inDate);
		Date cDate = new Date(inDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		resDate=(String.valueOf(cal.get(Calendar.DATE)))+"-"+(getMonthInWord(cal.get(Calendar.MONTH)))+"-"+(String.valueOf(cal.get(Calendar.YEAR)));
		//System.out.println("CLIB : Services.getDate_DD_MMM_YYYY Result Date >> "+resDate);
		return resDate;
	}
	
	public static String getInDate_MMDDYYY(String inDate)
	{
		String resDate="";
		//System.out.println("CLIB : Services.getDate_DD_MMM_YYYY input Date >> "+inDate);
		Date cDate = new Date(inDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		String date = String.valueOf(cal.get(Calendar.DATE));
		if(date!=null && date.trim().length()<2)
		{
			date="0"+date;
		}
		
		String month = (String.valueOf(cal.get(Calendar.MONTH)+1));
		if(month!=null && month.trim().length()<2)
		{
			month="0"+month;
		}
		resDate=(month+"/"+date+"/"+String.valueOf(cal.get(Calendar.YEAR)));		
		//resDate=(String.valueOf(cal.get(Calendar.MONTH)+1))+"/"+((String.valueOf(cal.get(Calendar.DATE)))+"/"+String.valueOf(cal.get(Calendar.YEAR)));
		System.out.println("CLIB : Services.getDate_DD_MMM_YYYY Result Date >> "+resDate);
		return resDate;
	}
	
	public static String getInDate_DDMMYYY(String inDate)
	{
		String resDate="";
		//System.out.println("CLIB : Services.getInDate_DDMMYYY input Date >> "+inDate);
		Date cDate = new Date(inDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		String date = String.valueOf(cal.get(Calendar.DATE));
		if(date!=null && date.trim().length()<2)
		{
			date="0"+date;
		}
		
		String month = (String.valueOf(cal.get(Calendar.MONTH)+1));
		if(month!=null && month.trim().length()<2)
		{
			month="0"+month;
		}
		resDate=(date+"/"+month+"/"+String.valueOf(cal.get(Calendar.YEAR)));
		//System.out.println("CLIB : Services.getInDate_DDMMYYY Result Date >> "+resDate);
		return resDate;
	}
	
	
	public static String getCurrentYear_YYYY()
	{
		String resYear="";
		Date cDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		resYear=String.valueOf(cal.get(Calendar.YEAR));
		return resYear;
	}
	
	public static String getCurrentMonthInNumber() 
	{
		String resYear="";
		Date cDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		resYear=String.valueOf(cal.get(Calendar.MONTH)+1);
		System.out.println("resYear==="+resYear);
		return resYear;
	}
	
	public static int getDaysInMonth(int month, int Year)
	{
		int noOfDays=0;
		//System.out.println("CLIB : Services.getAddedDate month >> "+month);
		//System.out.println("CLIB : Services.getAddedDate Year >> "+Year);
		//Date cDate = new Date(inDate);
		Calendar cal = Calendar.getInstance();
		cal.set(Year,month,1);
		noOfDays=cal.getActualMaximum(cal.DAY_OF_MONTH);
		
		return noOfDays;
	}
	
	public static boolean isDateBetweenDates(String checkDate, String fromDate, String toDate)
	{
		
		//System.out.println("CLIB : Services.getAddedDate inDate >> "+inDate);
		//System.out.println("CLIB : Services.getAddedDate noOfDays >> "+noOfDays);
		Date cDate = new Date(checkDate);
		Date fDate= new Date(fromDate);
		Date tDate= new Date(toDate);
		//System.out.println("Services >> isDateBetweenDates >>  isEndDateAfterStartDate >> "+isEndDateAfterStartDate(checkDate, toDate));
		//System.out.println("Services >> isDateBetweenDates >>  isEndDateBeforeStartDate >> "+isEndDateBeforeStartDate(checkDate, fromDate));
		return (isEndDateAfterStartDate(checkDate, toDate) || isTwoDatesAreEqual(checkDate, toDate))&& (isEndDateBeforeStartDate(checkDate, fromDate) || isTwoDatesAreEqual(checkDate, fromDate));

	}
	
	public static String getAddedDate(String inDate, int noOfDays)
	{
		String resDate="";
		//System.out.println("CLIB : Services.getAddedDate inDate >> "+inDate);
		//System.out.println("CLIB : Services.getAddedDate noOfDays >> "+noOfDays);
		Date cDate = new Date(inDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(cDate);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.DATE,cal.get(Calendar.DATE)+noOfDays);
		resDate=(String.valueOf(cal.get(Calendar.MONTH)+1))+"/"+((String.valueOf(cal.get(Calendar.DATE)))+"/"+String.valueOf(cal.get(Calendar.YEAR)));
		//System.out.println("CLIB : Services.getAddedDate resDate >> "+resDate);
		return resDate;
	}
/*	public static Date getDate_DD_MMM_YY(String tempDate)
	{
		Date cDate = new Date(tempDate);
		System.out.println("Result Date in Date"+cDate);
		return cDate;
	}*/
	public static int get_Date_Diff(String s_startDate, String s_endDate)
	{
		int noOfDaysDiff=0;

		System.out.println("CLIB : Services.getDate_DD_MMM_YY startDate >> "+s_startDate+" endDate"+s_endDate);
		Date startDate = new Date(s_startDate);
		Date endDate = new Date(s_endDate);
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(startDate);
		cal2.setTime(endDate);
		noOfDaysDiff = cal2.get(java.util.Calendar.DAY_OF_YEAR) -
        cal1.get(java.util.Calendar.DAY_OF_YEAR);
		int y2 = cal2.get(java.util.Calendar.YEAR);
		if (cal1.get(java.util.Calendar.YEAR) != y2)
		{
			cal1 = (java.util.Calendar) cal1.clone();
			do {
				noOfDaysDiff += cal1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
				cal1.add(java.util.Calendar.YEAR, 1);
			} while (cal1.get(java.util.Calendar.YEAR) != y2);
		}		
		System.out.println("CLIB : Services.getDate_DD_MMM_YY noOfDaysDiff >> "+noOfDaysDiff);
		return noOfDaysDiff;
	}
	
	public static String getCurrentMethodName()
	{
		Throwable t = new Throwable();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		t.printStackTrace(new PrintWriter(baos,true));
		byte [] stackTraceBytes= baos.toByteArray();
		String line=null;
		try
		{
			ByteArrayInputStream bais = new ByteArrayInputStream(stackTraceBytes);
			BufferedReader in = new BufferedReader(new InputStreamReader(bais));
			in.readLine();			
			in.readLine();
			line=in.readLine();
			in.close();
		}
		catch(IOException e)
		{
		}
		String methodName = line.substring(4,line.length());
		return methodName;
	}
	
	public static boolean isEndDateAfterStartDate(String strstartDt, String strendDt) 
	{
		Date startDt = new Date(strstartDt);
		Date endDt = new Date (strendDt);		
		Calendar startCal,endCal;
		startCal = Calendar.getInstance();
		startCal.setTime(startDt);
		endCal = Calendar.getInstance();
		endCal.setTime(endDt);
		int workDays = 0;


		if (endCal.getTimeInMillis()> startCal.getTimeInMillis()) 
		{			
			return true;
		}
		else
		{
			return false;
		}
		

	}
	
	public static int getWorkDays(String strstartDt, String strendDt) 
	{
		Date startDt = new Date(strstartDt);
		Date endDt = new Date (strendDt);		
		Calendar startCal,endCal;
		startCal = Calendar.getInstance();
		startCal.setTime(startDt);
		endCal = Calendar.getInstance();
		endCal.setTime(endDt);
		int workDays = 0;

//		Return 0 if start and end are the same
		if (startCal.getTimeInMillis()==endCal.getTimeInMillis()) 
		{
			return 0;
		}
//		Just in case the dates were transposed this prevents infinite loop
//		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) 
//		{
//			startCal.setTime(endDt);
//			endCal.setTime(startDt);
//		}
		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) 
		{
			//return 0;
			/*do 
			{
				endCal.add(Calendar.DAY_OF_MONTH, 1);
				//if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) 
				//{
					++workDays;
					
				//}
			} while (startCal.getTimeInMillis() >= endCal.getTimeInMillis());*/
			return 0;
		}

		do 
		{
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			//if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) 
			//{
				++workDays;  
				
			//}
		} while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

		//return workDays;
		return (workDays-1);  /* To exclude the book return date */
	}
	
	
	public static int getDiffDays(String strstartDt, String strendDt) 
	{
		Date startDt = new Date(strstartDt);
		Date endDt = new Date (strendDt);		
		Calendar startCal,endCal;
		startCal = Calendar.getInstance();
		startCal.setTime(startDt);
		endCal = Calendar.getInstance();
		endCal.setTime(endDt);
		int workDays = 0;

//		Return 0 if start and end are the same
		if (startCal.getTimeInMillis()==endCal.getTimeInMillis()) 
		{
			return 0;
		}
//		Just in case the dates were transposed this prevents infinite loop
//		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) 
//		{
//			startCal.setTime(endDt);
//			endCal.setTime(startDt);
//		}
		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) 
		{			
			return 0;
		}

		do 
		{
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			//if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) 
			//{
				++workDays;
				
			//}
		} while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

		//return workDays;
		return (workDays);  /* To exclude the book return date */
	}
	
	public static boolean isValidAccess(String IPAds)
	{
		boolean isValid=false;
		System.out.println("CLIB>> Services >> IPAds >> "+IPAds);
		ResourceBundle rb = ResourceBundle.getBundle("srm/clib/common/util/regips");
		System.out.println("CLIB>> Services >> Resource Path got ");
		String ipslist=rb.getString("ipslist");
		System.out.println("CLIB>> Services >> Registered IP List >> "+ipslist);
		isValid=(ipslist.indexOf(","+IPAds+",")==-1)?false:true;
		return isValid;
	}
	
	public static boolean copyFile (String srcWithPath, String destWithPath)
	{

		File src = new File(srcWithPath); 
		File dst = new File(destWithPath);
		if(!src.exists())
		{
			System.out.println("Services >> copyFile >> Source File does not exist.");
			//System.exit(0);
			return false;
		}
		else
		{

			InputStream in;
			OutputStream out;
			try 
			{
				in = new FileInputStream(src);
				out = new FileOutputStream(dst);
//				Transfer bytes from in to out
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0)
				{
					out.write(buf, 0, len);
				}
				in.close();
				out.close();
			} 
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				System.out.println("Services >> copyFile >> FileNotFoundException.");
				return false;
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				System.out.println("Services >> copyFile >> IOException.");
				return false;
			} 
		}
		System.out.println("File copied.");
		return true;
	}
	
	public static void deleteFile(String fileWithPath)
	{
		 File f = new File(fileWithPath);

		 // Make sure the file or directory exists and isn't write protected
		 if (!f.exists())
			 throw new IllegalArgumentException(
					 "Delete: no such file or directory: " + fileWithPath);

		/* if (!f.canWrite())
			 throw new IllegalArgumentException("Delete: write protected: "
					 + fileWithPath);

		 // If it is a directory, make sure it is empty
		 if (f.isDirectory()) 
		 {
			 String[] files = f.list();
			 if (files.length > 0)
				 throw new IllegalArgumentException(
						 "Delete: directory not empty: " + fileWithPath);
		 }*/

		 // Attempt to delete it
		 boolean success = f.delete();

		 if (!success)
		 {
			 System.out.println("File could not be deleted");
			 throw new IllegalArgumentException("Delete: deletion failed");
		 }
	}
	
	 public static int getAge(int y, int m, int d) 
	 {
	     Calendar cal = new GregorianCalendar(y, m, d);
	     Calendar now = new GregorianCalendar();
	     int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
	     if((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH)) || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH) && cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH)))
	     {
	        res--;
	     }
	     return res;
	   }

		public static boolean isTwoDatesAreEqual(String strstartDt, String strendDt) 
		{
			Date startDt = new Date(strstartDt);
			Date endDt = new Date (strendDt);		
			Calendar startCal,endCal;
			startCal = Calendar.getInstance();
			startCal.setTime(startDt);
			endCal = Calendar.getInstance();
			endCal.setTime(endDt);
			int workDays = 0;


			if (endCal.getTimeInMillis()== startCal.getTimeInMillis()) 
			{			
				return true;
			}
			else
			{
				return false;
			}
			

		}
		public static boolean isEndDateBeforeStartDate(String strstartDt, String strendDt) 
		{
			Date startDt = new Date(strstartDt);
			Date endDt = new Date (strendDt);		
			Calendar startCal,endCal;
			startCal = Calendar.getInstance();
			startCal.setTime(startDt);
			endCal = Calendar.getInstance();
			endCal.setTime(endDt);
			int workDays = 0;


			if (endCal.getTimeInMillis()< startCal.getTimeInMillis()) 
			{			
				return true;
			}
			else
			{
				return false;
			}
			

		}

//		/**
//		 * To get getMax
//		 * @param datasrc
//		 * @param cardNo
//		 * @return
//		 * @throws CLibUserException
//		 */
//		public static String getMax(DataSource datasrc, String tableName,String fieldName) throws CLibUserException
//		{
//			// TODO Auto-generated method stub
//			PreparedStatement pst=null;
//			ResultSet rs=null;
//			Connection dbcon=null;
//			String sqlQry="";
//			String maxNo="0";
//			try
//			{			
//				dbcon=datasrc.getConnection();
//				sqlQry = "SELECT MAX("+fieldName+") AS MAX FROM "+tableName;
//				//System.out.println("CLIB : in Services >> getMax >> sqlQuery >> "+sqlQry);
//				pst = dbcon.prepareStatement(sqlQry);
//				rs=pst.executeQuery();
//				if(rs.next())
//				{
//					maxNo = Services.setEmptyIfNull(rs.getString(1));
//					if(maxNo==null || maxNo.trim().length()==0 || maxNo.trim().equalsIgnoreCase("--"))
//					{
//						maxNo="0";
//					}
//				}
//				//System.out.println("CLIB : in Services >> getMax >> maxNo >> "+maxNo);
//				rs.close();
//				pst.close();
//				dbcon.close();
//				rs=null;
//				pst=null;
//				dbcon=null;
//			}
//			catch(SQLException e)
//			{
//				System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//				//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				//throw new CLibUserException(errMsg);
//			}
//			catch(Exception e)
//			{
//				System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//				//this errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				//throw new CLibUserException(errMsg);
//			}
//			finally
//			{
//				try 
//				{
//					if(rs!=null)
//					{
//						rs.close();
//					}
//					if(pst!=null)
//					{
//						pst.close();;
//					}
//					if(dbcon!=null)
//					{
//						dbcon.close();
//					}
//				} 
//				catch(SQLException e)
//				{
//					System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//					//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					//throw new CLibUserException(errMsg);
//				}
//				catch(Exception e)
//				{
//					System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//					//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					//throw new CLibUserException(errMsg);
//				}
//				
//			}
//			return maxNo;
//		}
//		
//		/**
//		 * To get getMax
//		 * @param datasrc
//		 * @param cardNo
//		 * @return
//		 * @throws CLibUserException
//		 */
//		public static String getMaxFromSQL(DataSource datasrc, String tableName,String fieldName) throws CLibUserException
//		{
//			// TODO Auto-generated method stub
//			PreparedStatement pst=null;
//			ResultSet rs=null;
//			Connection dbcon=null;
//			String sqlQry="";
//			String maxNo="0";
//			try
//			{			
//				//dbcon=datasrc.getConnection();
//				SQLDBConnection sQLDBConnection = new SQLDBConnection();
//				dbcon=sQLDBConnection.getConnection();
//				sqlQry = "SELECT MAX("+fieldName+") AS MAX FROM "+tableName;
//				System.out.println("CLIB : in Services >> getMax >> sqlQuery >> "+sqlQry);
//				pst = dbcon.prepareStatement(sqlQry);
//				rs=pst.executeQuery();
//				if(rs.next())
//				{
//					maxNo = Services.setEmptyIfNull(rs.getString(1));
//					if(maxNo==null || maxNo.trim().length()==0 || maxNo.trim().equalsIgnoreCase("--"))
//					{
//						maxNo="0";
//					}
//				}
//				System.out.println("CLIB : in Services >> getMax >> maxNo >> "+maxNo);
//				rs.close();
//				pst.close();
//				dbcon.close();
//				rs=null;
//				pst=null;
//				dbcon=null;
//			}
//			catch(SQLException e)
//			{
//				System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//				//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				//throw new CLibUserException(errMsg);
//			}
//			catch(Exception e)
//			{
//				System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//				//this errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				//throw new CLibUserException(errMsg);
//			}
//			finally
//			{
//				try 
//				{
//					if(rs!=null)
//					{
//						rs.close();
//					}
//					if(pst!=null)
//					{
//						pst.close();;
//					}
//					if(dbcon!=null)
//					{
//						dbcon.close();
//					}
//				} 
//				catch(SQLException e)
//				{
//					System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//					//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					//throw new CLibUserException(errMsg);
//				}
//				catch(Exception e)
//				{
//					System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//					//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					//throw new CLibUserException(errMsg);
//				}
//				
//			}
//			return maxNo;
//		}
//		
//		/**
//		 * To get the Admin Message List
//		 * @param datasrc
//		 * @param cardNo
//		 * @return
//		 * @throws CLibUserException
//		 */
//		public static ArrayList getAdminMsgListToDisplay(ArrayList msgList,String whereClause) throws CLibUserException
//		{
//			PreparedStatement pst=null;
//			ResultSet rs=null;
//			Connection dbcon=null;	
//			msgList = new ArrayList();
//			try
//			{
//				System.out.println("eMan : in Services >> getAdminMsgListToDisplay whereClause >> "+whereClause);
//				SQLDBConnection sQLDBConnection = new SQLDBConnection();   
//				dbcon = sQLDBConnection.getConnection();
//				String sqlQry="";
//				sqlQry="SELECT MESSAGE,PRIORITY,OWNER FROM TBLADMINMSGS" + whereClause+ " ORDER BY OWNER,ID ";	
//
//				System.out.println("eMan : in Services >> getAdminMsgListToDisplay sqlQuery >> "+sqlQry);
//				pst = dbcon.prepareStatement(sqlQry);
//				rs=pst.executeQuery();
//				while(rs.next())
//				{
//					AdminMsgDisplayBDO adminMsgDisplayBDO = new AdminMsgDisplayBDO();
//					adminMsgDisplayBDO.setMsg(Services.setEmptyIfNull(rs.getString(1)));		
//					adminMsgDisplayBDO.setPriority(Services.setEmptyIfNull(rs.getString(2)));
//					adminMsgDisplayBDO.setOwner(Services.setEmptyIfNull(rs.getString(3)));
//					msgList.add(adminMsgDisplayBDO);			
//				}
//				rs.close();
//				pst.close();
//				dbcon.close();
//				rs=null;
//				pst=null;
//				dbcon=null;		
//				return msgList;
//			}
//			catch(SQLException e)
//			{
//				System.out.println("eMan : Exception in Services >> getAdminMsgListToDisplay >> "+e.getMessage());
//				String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				throw new CLibUserException(errMsg);
//			}
//			catch(Exception e)
//			{
//				System.out.println("eMan : Exception in Services >> getAdminMsgListToDisplay >> "+e.getMessage());
//				String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				throw new CLibUserException(errMsg);
//			}
//			finally
//			{
//				try 
//				{
//					if(rs!=null)
//					{
//						rs.close();
//					}
//					if(pst!=null)
//					{
//						pst.close();;
//					}
//					if(dbcon!=null)
//					{
//						dbcon.close();
//					}				
//					
//				} 
//				catch(SQLException e)
//				{
//					System.out.println("eMan : Exception in Services >> getAdminMsgListToDisplay >> "+e.getMessage());
//					String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					throw new CLibUserException(errMsg);
//				}
//				catch(Exception e)
//				{
//					System.out.println("eMan : Exception in Services >> getAdminMsgListToDisplay >> "+e.getMessage());
//					String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					throw new CLibUserException(errMsg);
//				}
//				
//			}
//		}
//		
//		
//		/**
//		 * To get the Admin Message List
//		 * @param datasrc
//		 * @param cardNo
//		 * @return
//		 * @throws CLibUserException
//		 */
//		public static ArrayList getDeptMsgListToDisplay(ArrayList msgList,String whereClause, String whereClauseInput,String facultyid) throws CLibUserException
//		{
//			PreparedStatement pst=null;
//			ResultSet rs=null;
//			Connection dbcon=null;	
//			msgList = new ArrayList();
//			int deptNo=Integer.parseInt(whereClauseInput);
//			try
//			{
//				System.out.println("eMan : in Services >> getAdminMsgListToDisplay whereClause >> "+whereClause);
//				SQLDBConnection sQLDBConnection = new SQLDBConnection();
//				dbcon = sQLDBConnection.getConnection();
//				String sqlQry="";
//				sqlQry="SELECT MESSAGE,PRIORITY,OWNER FROM TBLADMINMSGS" + whereClause+ " ORDER BY OWNER,ID ";	
//
//				System.out.println("eMan : in Services >> getAdminMsgListToDisplay sqlQuery >> "+sqlQry);
//				pst = dbcon.prepareStatement(sqlQry);
//				pst.setInt(1,deptNo);
//				pst.setInt(2,Integer.parseInt(facultyid));			
//				rs=pst.executeQuery();
//				while(rs.next())
//				{
//					AdminMsgDisplayBDO adminMsgDisplayBDO = new AdminMsgDisplayBDO();
//					adminMsgDisplayBDO.setMsg(Services.setEmptyIfNull(rs.getString(1)));		
//					adminMsgDisplayBDO.setPriority(Services.setEmptyIfNull(rs.getString(2)));
//					adminMsgDisplayBDO.setOwner(Services.setEmptyIfNull(rs.getString(3)));
//					msgList.add(adminMsgDisplayBDO);			
//				}
//				rs.close();
//				pst.close();
//				dbcon.close();
//				rs=null;
//				pst=null;
//				dbcon=null;		
//				return msgList;
//			}
//			catch(SQLException e)
//			{
//				System.out.println("eMan : Exception in Services >> getAdminMsgListToDisplay >> "+e.getMessage());
//				String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				throw new CLibUserException(errMsg);
//			}
//			catch(Exception e)
//			{
//				System.out.println("eMan : Exception in Services >> getAdminMsgListToDisplay >> "+e.getMessage());
//				String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				throw new CLibUserException(errMsg);
//			}
//			finally
//			{
//				try 
//				{
//					if(rs!=null)
//					{
//						rs.close();
//					}
//					if(pst!=null)
//					{
//						pst.close();;
//					}
//					if(dbcon!=null)
//					{
//						dbcon.close();
//					}				
//					
//				} 
//				catch(SQLException e)
//				{
//					System.out.println("eMan : Exception in Services >> getAdminMsgListToDisplay >> "+e.getMessage());
//					String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					throw new CLibUserException(errMsg);
//				}
//				catch(Exception e)
//				{
//					System.out.println("eMan : Exception in Services >> getAdminMsgListToDisplay >> "+e.getMessage());
//					String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					throw new CLibUserException(errMsg);
//				}
//				
//			}
//		}
//		
//		/**
//		 * To get the Services List
//		 * @param datasrc
//		 * @param FacultyId,deptID and ServiceID
//		 * @return
//		 * @throws CLibUserException
//		 */
//		public static ArrayList getSystemServices(ArrayList serviceList) throws CLibUserException
//		{
//			PreparedStatement pst=null;
//			ResultSet rs=null;
//			Connection dbcon=null;	
//			serviceList = new ArrayList();
//			DeptInfoDO deptInfoDO = null;
//			try
//			{
//				SQLDBConnection sQLDBConnection = new SQLDBConnection();
//				dbcon = sQLDBConnection.getConnection();
//				String sqlQry="";
//				sqlQry="SELECT ID,NAME FROM TBL_SERVICES_MASTER ORDER BY ID ";	
//
//				System.out.println("eMan : in Services >> getAdminMsgListToDisplay sqlQuery >> "+sqlQry);
//				pst = dbcon.prepareStatement(sqlQry);
//	
//				rs=pst.executeQuery();
//				while(rs.next())
//				{
//					deptInfoDO = new DeptInfoDO();
//					deptInfoDO.setCode(Services.setEmptyIfNull(rs.getString(1)));		
//					deptInfoDO.setName(Services.setEmptyIfNull(rs.getString(2)));					
//					serviceList.add(deptInfoDO);	
//					deptInfoDO=null;
//				}
//				rs.close();
//				pst.close();
//				dbcon.close();
//				rs=null;
//				pst=null;
//				dbcon=null;		
//				return serviceList;
//			}
//			catch(SQLException e)
//			{
//				System.out.println("eMan : Exception in Services >> getAdminMsgListToDisplay >> "+e.getMessage());
//				String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				throw new CLibUserException(errMsg);
//			}
//			catch(Exception e)
//			{
//				System.out.println("eMan : Exception in Services >> getAdminMsgListToDisplay >> "+e.getMessage());
//				String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				throw new CLibUserException(errMsg);
//			}
//			finally
//			{
//				try 
//				{
//					if(rs!=null)
//					{
//						rs.close();
//					}
//					if(pst!=null)
//					{
//						pst.close();;
//					}
//					if(dbcon!=null)
//					{
//						dbcon.close();
//					}				
//					
//				} 
//				catch(SQLException e)
//				{
//					System.out.println("eMan : Exception in Services >> getAdminMsgListToDisplay >> "+e.getMessage());
//					String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					throw new CLibUserException(errMsg);
//				}
//				catch(Exception e)
//				{
//					System.out.println("eMan : Exception in Services >> getAdminMsgListToDisplay >> "+e.getMessage());
//					String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					throw new CLibUserException(errMsg);
//				}
//				
//			}
//		}
//		
//		/**
//		 * To get the Services List
//		 * @param datasrc
//		 * @param FacultyId,deptID and ServiceID
//		 * @return
//		 * @throws CLibUserException
//		 */
//		public static boolean getIsServiceAssignedForDept(String serviceID,String empID) throws CLibUserException
//		{
//			PreparedStatement pst=null;
//			ResultSet rs=null;
//			Connection dbcon=null;	
//			DeptInfoDO deptInfoDO = null;
//			boolean isAssigned=false;
//			int empIDint=Integer.parseInt(empID);
//			try
//			{
//				SQLDBConnection sQLDBConnection = new SQLDBConnection();
//				dbcon = sQLDBConnection.getConnection();
//				String sqlQry="";
//				sqlQry="SELECT SERVICEID FROM TBLMAPPING_SERVICE_DEPT WHERE SERVICEID=? AND DEPTID=(SELECT DEPT_CODE FROM EMPORG WHERE CAST(EMP_NO AS INT)=?) AND FACULTYID=(SELECT FACULTYID FROM EMPORG WHERE CAST(EMP_NO AS INT)=?) ";	
//
//				System.out.println("eMan : in Services >> getIsServiceAssignedForDept sqlQuery >> "+sqlQry);
//				
//				System.out.println("eMan : in Servicesid >> "+serviceID);
//				System.out.println("eMan : in empID >> "+empID);
//				pst = dbcon.prepareStatement(sqlQry);
//				pst.setString(1,serviceID);
//				pst.setInt(2,empIDint);
//				pst.setInt(3,empIDint);
//				rs=pst.executeQuery();
//				if(rs.next())
//				{ 
//					isAssigned=true;
//				}
//				rs.close();
//				pst.close();
//				dbcon.close();
//				rs=null;
//				pst=null;
//				dbcon=null;		
//				return isAssigned;
//			}
//			catch(SQLException e)
//			{
//				System.out.println("eMan : Exception in Services >> getIsServiceAssignedForDept >> "+e.getMessage());
//				String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				throw new CLibUserException(errMsg);
//			}
//			catch(Exception e)
//			{
//				System.out.println("eMan : Exception in Services >> getIsServiceAssignedForDept >> "+e.getMessage());
//				String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				throw new CLibUserException(errMsg);
//			}
//			finally
//			{
//				try 
//				{
//					if(rs!=null)
//					{
//						rs.close();
//					}
//					if(pst!=null)
//					{
//						pst.close();;
//					}
//					if(dbcon!=null)
//					{
//						dbcon.close();
//					}				
//					
//				} 
//				catch(SQLException e)
//				{
//					System.out.println("eMan : Exception in Services >> getIsServiceAssignedForDept >> "+e.getMessage());
//					String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					throw new CLibUserException(errMsg);
//				}
//				catch(Exception e)
//				{
//					System.out.println("eMan : Exception in Services >> getIsServiceAssignedForDept >> "+e.getMessage());
//					String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					throw new CLibUserException(errMsg);
//				}
//				
//			}
//		}
//		 
//		/**
//		 * To get the Services List
//		 * @param datasrc
//		 * @param FacultyId,deptID and ServiceID
//		 * @return
//		 * @throws CLibUserException
//		 */
//		public static boolean getIsServiceAssignedForDeptByDeptID(String serviceID,String deptID) throws CLibUserException
//		{
//			PreparedStatement pst=null;
//			ResultSet rs=null;
//			Connection dbcon=null;	
//			DeptInfoDO deptInfoDO = null;
//			boolean isAssigned=false;
//			int deptIDint=Integer.parseInt(deptID);
//			try
//			{
//				SQLDBConnection sQLDBConnection = new SQLDBConnection();
//				dbcon = sQLDBConnection.getConnection();
//				String sqlQry="";
//				sqlQry="SELECT SERVICEID FROM TBLMAPPING_SERVICE_DEPT WHERE SERVICEID=? AND DEPTID=? ";	
//
//				System.out.println("eMan : in Services >> getIsServiceAssignedForDeptByDeptID sqlQuery >> "+sqlQry);
//				pst = dbcon.prepareStatement(sqlQry);
//				pst.setString(1,serviceID);
//				pst.setInt(2,deptIDint);
//				//pst.setInt(3,empIDint);
//				rs=pst.executeQuery();
//				if(rs.next())
//				{
//					isAssigned=true;
//				}
//				rs.close();
//				pst.close();
//				dbcon.close();
//				rs=null;
//				pst=null;
//				dbcon=null;		
//				return isAssigned;
//			}
//			catch(SQLException e)
//			{
//				System.out.println("eMan : Exception in Services >> getIsServiceAssignedForDeptByDeptID >> "+e.getMessage());
//				String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				throw new CLibUserException(errMsg);
//			}
//			catch(Exception e)
//			{
//				System.out.println("eMan : Exception in Services >> getIsServiceAssignedForDeptByDeptID >> "+e.getMessage());
//				String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				throw new CLibUserException(errMsg);
//			}
//			finally
//			{
//				try 
//				{
//					if(rs!=null)
//					{
//						rs.close();
//					}
//					if(pst!=null)
//					{
//						pst.close();;
//					}
//					if(dbcon!=null)
//					{
//						dbcon.close();
//					}				
//					
//				} 
//				catch(SQLException e)
//				{
//					System.out.println("eMan : Exception in Services >> getIsServiceAssignedForDeptByDeptID >> "+e.getMessage());
//					String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					throw new CLibUserException(errMsg);
//				}
//				catch(Exception e)
//				{
//					System.out.println("eMan : Exception in Services >> getIsServiceAssignedForDeptByDeptID >> "+e.getMessage());
//					String errMsg ="Exception occured in <br> Class: Services <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					throw new CLibUserException(errMsg);
//				}
//				
//			}
//		}
//		
//		
//		/**
//		 * To export to Excel
//		 * @param datasrc
//		 * @param cardNo
//		 * @return 
//		 * @return
//		 * @throws CLibUserException
//		 */
//		public static void exportToCSV(HttpServlet servlet,HttpServletRequest request,HttpServletResponse response,ArrayList headerList,ArrayList resultList,String fileName) throws CLibUserException
//		{
//			try
//			{
//				//response.setContentType("application/vnd.ms-excel");
//				//response.setHeader("Content-Disposition", "attachment; filename=myfile.csv");
//				int i=0;
//				PrintWriter out = response.getWriter();
//			     // String filename = "c:\\myfile.csv";
//			      FileWriter fw = new FileWriter("c:\\myfile.csv");
//			      if(headerList!=null && headerList.size()>0)
//			      {
//			    	  for(i=0;i<headerList.size();i++)
//			    	  {
//			    		  fw.write((String) headerList.get(i));
//			    		  fw.write(",");
//			    	  }
//			      }
//			      fw.write("\n");
//			     // System.out.println("eMan >> Services.java >> resultList.size >>"+resultList.size());
//			      if(resultList!=null && resultList.size()>0)
//			      {
//			    	  
//			    	  for(i=0;i<resultList.size();i++)
//			    	  {
//			    		  Object obj=resultList.get(i);
//				    	  Field[] field =obj.getClass().getDeclaredFields();
//				    	  Object obj1=null;
//			    		 for (i=0;i<field.length;i++) 
//			    		  {
//			    		     field[i].setAccessible(true); // if you want to modify private fields
//			    			// System.out.println("eMan >> Services.java >>"+field[i].getName());
//			    			 //System.out.println("eMan >> Services.java obj >>"+field[i].get(obj1));
//			    			 fw.write(field[i].get(obj1).toString());
//				    		 fw.write(",");
//				    		
//			    		  }
//			    		 obj=null;
//			    		 fw.write("\n");
//			    	  }
//			      }
//			      fw.flush();
//			      fw.close();
//			     // System.out.println("<b>Csv file Successfully created.</b>");
//				/*WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
//				WritableSheet s = w.createSheet("Demo", 0);
//				s.addCell(new Label(0, 0, "Hello World"));
//				w.write();
//				w.close();*/
//			} 
//			catch(Exception e)
//			{
//				System.out.println("CLIB : Exception in Services >> exportToCSV >> "+e.getMessage());
//				String errMsg ="Exception occured in <br> Class: Services.java <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				throw new CLibUserException(errMsg);
//			}			
//		}
//		
//		/**
//		 * To export to Excel
//		 * @param datasrc
//		 * @param cardNo
//		 * @return 
//		 * @return
//		 * @throws CLibUserException
//		 */
//	/*	public static void exportToExcel(HttpServlet servlet,HttpServletRequest request,HttpServletResponse response,ArrayList headerList,ArrayList resultList,String fileName) throws CLibUserException
//		{
//			try
//			{
//				//response.setContentType("application/vnd.ms-excel");
//				//response.setHeader("Content-Disposition", "attachment; filename=myfile.csv");
//				 Workbook wb = new HSSFWorkbook();
//				    //Workbook wb = new XSSFWorkbook();
//				    CreationHelper createHelper = wb.getCreationHelper();
//				    Sheet sheet = wb.createSheet("new sheet");
//
//				    // Create a row and put some cells in it. Rows are 0 based.
//				    Row row = sheet.createRow((short)0);
//				    // Create a cell and put a value in it.
//				    Cell cell = row.createCell(0);
//				    cell.setCellValue(1);
//
//				    // Or do it on one line.
//				    row.createCell(1).setCellValue(1.2);
//				    row.createCell(2).setCellValue(
//				         createHelper.createRichTextString("This is a string"));
//				    row.createCell(3).setCellValue(true);
//
//				    // Write the output to a file
//				    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
//				    wb.write(fileOut);
//				    fileOut.close();
//
//
//
//				/*WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
//				WritableSheet s = w.createSheet("Demo", 0);
//				s.addCell(new Label(0, 0, "Hello World"));
//				w.write();
//				w.close();
//			} 
//			catch(Exception e)
//			{
//				System.out.println("CLIB : Exception in Services >> exportToCSV >> "+e.getMessage());
//				String errMsg ="Exception occured in <br> Class: Services.java <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				throw new CLibUserException(errMsg);
//			}			
//		}*/
//		
//		/*public static String getEncDecPass(String val,String mode)
//		{
//			PassMan passMan = new PassMan();
//			if(mode==null || mode.trim().length()==0)
//			{
//				val=null;
//			}
//			else if(mode.trim().equalsIgnoreCase("E"))
//			{
//				val=passMan.EncPass(val);
//			}
//			else if(mode.trim().equalsIgnoreCase("D"))
//			{
//				val=passMan.DecPass(val);
//			}
//			return val;
//		}
//		*/
//		public static String getLeaveColumnName(String leaveTypeApplied)
//		{
//			String leaveTypeColumn="";
//			if(leaveTypeApplied!=null && (leaveTypeApplied.trim().equals("1") || leaveTypeApplied.trim().equals("2"))) 
//			{
//				leaveTypeColumn="PER";
//			}
//			else if(leaveTypeApplied!=null && leaveTypeApplied.trim().equals("3") )  //CL=3 
//			{
//				leaveTypeColumn="CL";
//			}
//			else if(leaveTypeApplied!=null && leaveTypeApplied.trim().equals("4") )  //EL=3 
//			{
//				leaveTypeColumn="EL";
//			}
//			else if(leaveTypeApplied!=null && leaveTypeApplied.trim().equals("5") )  //VL=3 
//			{
//				leaveTypeColumn="VL";
//			}
//			else if(leaveTypeApplied!=null && leaveTypeApplied.trim().equals("6") )  //OD=6 
//			{
//				leaveTypeColumn="OD";
//			}
//			else if(leaveTypeApplied!=null && leaveTypeApplied.trim().equals("7") )  //ML=3 
//			{
//				leaveTypeColumn="ML";
//			}	
//			else if(leaveTypeApplied!=null && leaveTypeApplied.trim().equals("14") )  //MWP=14 
//			{
//				leaveTypeColumn="MWP";
//			}
//			else if(leaveTypeApplied!=null && leaveTypeApplied.trim().equals("16") )  //RH=14 
//			{
//				leaveTypeColumn="RH";
//			}
//			/********************************/
//			else if(leaveTypeApplied!=null && leaveTypeApplied.trim().equals("8") )  //CO=8
//			{
//				leaveTypeColumn="CO";
//			}			
//			else if(leaveTypeApplied!=null && leaveTypeApplied.trim().equals("13") )  //TL=13
//			{
//				leaveTypeColumn="TL";
//			}
//			
//			else if(leaveTypeApplied!=null && leaveTypeApplied.trim().equals("17") )  //Academic Leave=17 
//			{
//				leaveTypeColumn="AcademicLeave";
//			}
//			
//			else if(leaveTypeApplied!=null && leaveTypeApplied.trim().equals("18") )  //SCL=14 
//			{
//				leaveTypeColumn="SCL";
//			}
//			
//			else if(leaveTypeApplied!=null && leaveTypeApplied.trim().equals("19") )  //AccumulatedLeave=19 
//			{
//				leaveTypeColumn="AccumulatedLeave";
//			}
//            else if(leaveTypeApplied!=null && leaveTypeApplied.trim().equals("20") )  //AccumulatedLeave=19 
//			{
//				leaveTypeColumn="WFH";
//			}
//            else if(leaveTypeApplied!=null && leaveTypeApplied.trim().equals("21") )  //AccumulatedLeave=19 
//			{
//				leaveTypeColumn="LMB";
//			}
//			return leaveTypeColumn;
//		}
//		
//		
//		/**
//		 * To get capturePhotoUpload
//		 * @param datasrc
//		 * @param empCode
//		 * @return
//		 * @throws CLibUserException
//		 */
//		public static boolean capturePhotoUpload(String empCode,long fileSize,int width,int height) throws CLibUserException
//		{
//			// TODO Auto-generated method stub
//			PreparedStatement pst=null;
//			Connection dbcon=null;
//			String sqlQry="";
//			int count=0;
//			boolean isSaved=false;
//			try
//			{			
//				SQLDBConnection sQLDBConnection = new SQLDBConnection();
//				dbcon=sQLDBConnection.getConnection();
//				sqlQry = "INSERT INTO TBLPHOTOUPLOAD (empid,datetimeuploaded,datetimemodified,datetimedeleted,filesize,actionstatus,activestatus,width,height,remarks,forceuploadedstatus) VALUES ( ?,getdate(),null,null,?,1,1,?,?,'Initial Upload',null) ";
//				System.out.println("CLIB : in Services >> capturePhotoUpload >> sqlQuery >> "+sqlQry);
//				pst = dbcon.prepareStatement(sqlQry);
//				pst.setString(1, empCode);
//				pst.setLong(2, fileSize);
//				pst.setLong(3, width);
//				pst.setLong(4, height);
//				count=pst.executeUpdate();
//				if(count>0)
//				{
//					isSaved= true;
//				}
//				
//				System.out.println("CLIB : in Services >> capturePhotoUpload >> isSaved >> "+isSaved);
//				pst.close();
//				dbcon.close();
//				pst=null;
//				dbcon=null;
//			}
//			catch(SQLException e)
//			{
//				System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//				//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				//throw new CLibUserException(errMsg);
//			}
//			catch(Exception e)
//			{
//				System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//				//this errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				//throw new CLibUserException(errMsg);
//			}
//			finally
//			{
//				try 
//				{
//					
//					if(pst!=null)
//					{
//						pst.close();;
//					}
//					if(dbcon!=null)
//					{
//						dbcon.close();
//					}
//				} 
//				catch(SQLException e)
//				{
//					System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//					//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					//throw new CLibUserException(errMsg);
//				}
//				catch(Exception e)
//				{
//					System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//					//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					//throw new CLibUserException(errMsg);
//				}
//				
//			}
//			return isSaved;
//		}
//		
//		
//		/**
//		 * To get capturePhotoUpload
//		 * @param datasrc
//		 * @param empCode
//		 * @return
//		 * @throws CLibUserException
//		 */
//		public static boolean capturePhotoModified(String empCode,int action, long fileSize,int width,int height,String remarks) throws CLibUserException
//		{
//			// TODO Auto-generated method stub
//			
//			
//			// 1 - Fresh Insert, 0 - Delete, 2-Upload again with out delete, 3-Upload after delete
//			PreparedStatement pst=null;
//			Connection dbcon=null;
//			String sqlQry="";
//			int count=0;
//			String actionCol="";
//			
//			if(action==0) {
//				actionCol="datetimedeleted";
//			}
//			else if(action==2) {
//				actionCol="datetimemodified";
//			}
//			else if(action==3) {
//				actionCol="datetimeuploaded";
//			}
//			else {
//				actionCol="datetimemodified";
//			}
//					
//			boolean isSaved=false;
//			try
//			{			
//				SQLDBConnection sQLDBConnection = new SQLDBConnection();
//				dbcon=sQLDBConnection.getConnection();
//				
//				sqlQry = "UPDATE TBLPHOTOUPLOAD SET ACTIONSTATUS=?,filesize=?, "+actionCol+"=getdate(),width=?,height=?,remarks=?,forceuploadedstatus=1 WHERE EMPID= ? ";
//				System.out.println("CLIB : in Services >> getMax >> sqlQuery >> "+sqlQry);
//				pst = dbcon.prepareStatement(sqlQry);
//				pst.setInt(1, action);
//				pst.setLong(2, fileSize);
//				
//				pst.setInt(3, width);
//				pst.setInt(4, height);
//				pst.setString(5, remarks);
//				
//				pst.setString(6, empCode);
//				count=pst.executeUpdate();
//				if(count>0)
//				{
//					isSaved= true;
//				}
//				
//				System.out.println("CLIB : in Services >> capturePhotoUpload >> isSaved >> "+isSaved);
//				pst.close();
//				dbcon.close();
//				pst=null;
//				dbcon=null;
//			}
//			catch(SQLException e)
//			{
//				System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//				//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				//throw new CLibUserException(errMsg);
//			}
//			catch(Exception e)
//			{
//				System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//				//this errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				//throw new CLibUserException(errMsg);
//			}
//			finally
//			{
//				try 
//				{
//					
//					if(pst!=null)
//					{
//						pst.close();;
//					}
//					if(dbcon!=null)
//					{
//						dbcon.close();
//					}
//				} 
//				catch(SQLException e)
//				{
//					System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//					//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					//throw new CLibUserException(errMsg);
//				}
//				catch(Exception e)
//				{
//					System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//					//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					//throw new CLibUserException(errMsg);
//				}
//				
//			}
//			return isSaved;
//		}
//		
//		
//		/**
//		 * To get isPhotoUploadAlready
//		 * @param datasrc
//		 * @param empCode
//		 * @return
//		 * @throws CLibUserException
//		 */
//		public static boolean isPhotoUploadAlready(String empCode) throws CLibUserException
//		{
//			// TODO Auto-generated method stub
//			PreparedStatement pst=null;
//			Connection dbcon=null;
//			ResultSet rs=null;
//			String sqlQry="";
//			int count=0;
//			boolean isAvailable=false;
//			try
//			{			
//				SQLDBConnection sQLDBConnection = new SQLDBConnection();
//				dbcon=sQLDBConnection.getConnection();
//				sqlQry = "SELECT EMPID FROM TBLPHOTOUPLOAD WHERE EMPID=? ";
//				System.out.println("CLIB : in Services >> capturePhotoUpload >> sqlQuery >> "+sqlQry);
//				pst = dbcon.prepareStatement(sqlQry);
//				pst.setString(1, empCode);
//
//				rs=pst.executeQuery();
//				if(rs.next())
//				{
//					isAvailable= true;
//				}
//				
//				System.out.println("CLIB : in Services >> capturePhotoUpload >> isAvailable >> "+isAvailable);
//				rs.close();
//				pst.close();
//				dbcon.close();
//				rs=null;
//				pst=null;
//				dbcon=null;
//			}
//			catch(SQLException e)
//			{
//				System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//				//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				//throw new CLibUserException(errMsg);
//			}
//			catch(Exception e)
//			{
//				System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//				//this errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				//throw new CLibUserException(errMsg);
//			}
//			finally
//			{
//				try 
//				{
//					if(rs!=null)
//					{
//						rs.close();
//					}
//					
//					if(pst!=null)
//					{
//						pst.close();;
//					}
//					if(dbcon!=null)
//					{
//						dbcon.close();
//					}
//				} 
//				catch(SQLException e)
//				{
//					System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//					//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					//throw new CLibUserException(errMsg);
//				}
//				catch(Exception e)
//				{
//					System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//					//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					//throw new CLibUserException(errMsg);
//				}
//				
//			}
//			return isAvailable;
//		}
//		
//		
//		
//		/**
//		 * To get isPhotoUploadAlready
//		 * @param datasrc
//		 * @param empCode
//		 * @return
//		 * @throws CLibUserException
//		 */
//		public static boolean isPhotoUploadForcedForEmployee(String empCode) throws CLibUserException
//		{
//			// TODO Auto-generated method stub
//			PreparedStatement pst=null;
//			Connection dbcon=null;
//			ResultSet rs=null;
//			String sqlQry="";
//			int count=0;
//			boolean isForced=false;
//			try
//			{			
//				SQLDBConnection sQLDBConnection = new SQLDBConnection();
//				dbcon=sQLDBConnection.getConnection();
//				sqlQry = "SELECT empcode FROM tblphotouploadforceemployees WHERE empcode=? and activestatus=1";
//				System.out.println("CLIB : in Services >> isPhotoUploadForcedForEmployee >> sqlQuery >> "+sqlQry);
//				pst = dbcon.prepareStatement(sqlQry);
//				pst.setString(1, empCode);
//
//				rs=pst.executeQuery();
//				if(rs.next())
//				{
//					isForced= true;
//				}
//				
//				System.out.println("CLIB : in Services >> isPhotoUploadForcedForEmployee >> isForced >> "+isForced);
//				rs.close();
//				pst.close();
//				dbcon.close();
//				rs=null;
//				pst=null;
//				dbcon=null;
//			}
//			catch(SQLException e)
//			{
//				System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//				//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				//throw new CLibUserException(errMsg);
//			}
//			catch(Exception e)
//			{
//				System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//				//this errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				//throw new CLibUserException(errMsg);
//			}
//			finally
//			{
//				try 
//				{
//					if(rs!=null)
//					{
//						rs.close();
//					}
//					
//					if(pst!=null)
//					{
//						pst.close();;
//					}
//					if(dbcon!=null)
//					{
//						dbcon.close();
//					}
//				} 
//				catch(SQLException e)
//				{
//					System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//					//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					//throw new CLibUserException(errMsg);
//				}
//				catch(Exception e)
//				{
//					System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//					//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					//throw new CLibUserException(errMsg);
//				}
//				
//			}
//			return isForced;
//		}
//		
//		
//		
//		
//		/**
//		 * To get capturePhotoUpload
//		 * @param datasrc
//		 * @param empCode
//		 * @return
//		 * @throws CLibUserException
//		 */
//		public static PhotoUploadBDO getPhotoUploadDetails(String empCode) throws CLibUserException
//		{
//			// TODO Auto-generated method stub
//			PreparedStatement pst=null;
//			Connection dbcon=null;
//			ResultSet rs=null;
//			String sqlQry="";
//			PhotoUploadBDO photoUploadBDO = new PhotoUploadBDO();
//			try
//			{			
//				SQLDBConnection sQLDBConnection = new SQLDBConnection();
//				dbcon=sQLDBConnection.getConnection();
//				sqlQry = "SELECT empid,datetimeuploaded,datetimemodified,datetimedeleted,filesize,actionstatus,activestatus,width,height,remarks FROM TBLPHOTOUPLOAD WHERE EMPID=? ";
//				System.out.println("CLIB : in Services >> capturePhotoUpload >> sqlQuery >> "+sqlQry);
//				pst = dbcon.prepareStatement(sqlQry);
//				pst.setString(1, empCode);
//
//				rs=pst.executeQuery();
//				if(rs.next())
//				{
//					photoUploadBDO.setEmpId(rs.getString(1));
//					photoUploadBDO.setDateTimeUploaded(rs.getString(2));
//					photoUploadBDO.setDateTimeModified(rs.getString(3));
//					photoUploadBDO.setDateTimeDeleted(rs.getString(4));
//					photoUploadBDO.setFileSize(rs.getString(5));
//					photoUploadBDO.setActionStatus(rs.getString(6));
//					photoUploadBDO.setActiveStatus(rs.getString(7));
//					photoUploadBDO.setPhotoWidth(rs.getString(8));
//					photoUploadBDO.setPhotoHeight(rs.getString(9));
//					photoUploadBDO.setRemarks(rs.getString(10));
//				}
//				
//				rs.close();
//				pst.close();
//				dbcon.close();
//				rs=null;
//				pst=null;
//				dbcon=null;
//			}
//			catch(SQLException e)
//			{
//				System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//				//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				//throw new CLibUserException(errMsg);
//			}
//			catch(Exception e)
//			{
//				System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//				//this errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				//throw new CLibUserException(errMsg);
//			}
//			finally
//			{
//				try 
//				{
//					if(rs!=null)
//					{
//						rs.close();
//					}
//					
//					if(pst!=null)
//					{
//						pst.close();;
//					}
//					if(dbcon!=null)
//					{
//						dbcon.close();
//					}
//				} 
//				catch(SQLException e)
//				{
//					System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//					//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					//throw new CLibUserException(errMsg);
//				}
//				catch(Exception e)
//				{
//					System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//					//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					//throw new CLibUserException(errMsg);
//				}
//				
//			}
//			return photoUploadBDO;
//		}		
//		
//		public static boolean updateForcePhotoUploadStatusForEmployee(String empCode,String status) throws CLibUserException
//		{
//			// TODO Auto-generated method stub
//			PreparedStatement pst=null;
//			Connection dbcon=null;
//			String sqlQry="";
//			int count=0;
//			boolean isSaved=false;
//			try
//			{			
//				SQLDBConnection sQLDBConnection = new SQLDBConnection();
//				dbcon=sQLDBConnection.getConnection();
//				sqlQry = "UPDATE tblphotouploadforceemployees SET ACTIVESTATUS=? WHERE EMPCODE= ? ";
//				System.out.println("CLIB : in Services >> capturePhotoUpload >> sqlQuery >> "+sqlQry);
//				pst = dbcon.prepareStatement(sqlQry);
//				pst.setString(1,status);
//				pst.setString(2, empCode);
//				count=pst.executeUpdate();
//				if(count>0)
//				{
//					isSaved= true;
//				}
//				
//				System.out.println("CLIB : in Services >> capturePhotoUpload >> isSaved >> "+isSaved);
//				pst.close();
//				dbcon.close();
//				pst=null;
//				dbcon=null;
//			}
//			catch(SQLException e)
//			{
//				System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//				//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				//throw new CLibUserException(errMsg);
//			}
//			catch(Exception e)
//			{
//				System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//				//this errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//				//throw new CLibUserException(errMsg);
//			}
//			finally
//			{
//				try 
//				{
//					
//					if(pst!=null)
//					{
//						pst.close();;
//					}
//					if(dbcon!=null)
//					{
//						dbcon.close();
//					}
//				} 
//				catch(SQLException e)
//				{
//					System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//					//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					//throw new CLibUserException(errMsg);
//				}
//				catch(Exception e)
//				{
//					System.out.println("CLIB : Exception in Services >> "+e.getMessage());
//					//String errMsg ="Exception occured in <br> Class: "+this.getClass().getName()+" <br> Method: "+Services.getCurrentMethodName() +"The error message is "+e;
//					//throw new CLibUserException(errMsg);
//				}
//				
//			}
//			return isSaved;
//		}
}
