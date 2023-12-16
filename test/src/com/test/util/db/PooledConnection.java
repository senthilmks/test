/*
 * PooledConnection.java
 *
 * Created on August 30, 2018, 4:29 PM
 */
package com.test.util.db;
 
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

public class PooledConnection {
        private static final Logger QueryLogger = Logger.getLogger(PooledConnection.class); 
	private long concreatedtime=0;
	private long startingOfIdleness;
	private long queryexecutedtime=0;
	public java.sql.Connection econ = null;
	private boolean inuse = false;
        private String lstrSql = "", lstrObjInfo = "";
        private long lngFDCounter = 0; 
        
        public void incrementUsageCounter() {
            lngFDCounter++;
        }
        
        public long getUsageCount() { 
            return lngFDCounter;
        }
        public void setSQLString(String val) {      
            lstrSql = val;
        }
        public String getSQLString() {
            return lstrSql;
        }

        public void setObjectInfo(String val) {
            lstrObjInfo = val;
        }

        public String getObjectInfo() {
            return lstrObjInfo;
        }
        public void Reset(){
            concreatedtime= System.currentTimeMillis();
            lngFDCounter=0;
        }
	public PooledConnection(java.sql.Connection value) {
		if (value != null) {
			econ = value;
			concreatedtime= System.currentTimeMillis();
		}
	}
	public java.sql.Connection getConnection() {
		return econ;
	}

	public long getStartingOfIdleness() {
		return this.startingOfIdleness;
	}

	public void setStartingOfIdleness() {
		startingOfIdleness = System.currentTimeMillis();
	}
 
	public void setInUse(boolean value) {
		inuse = value;
	}

	public boolean inUse() {
		return inuse;
	}

	/**
	 * Close the physical connection.
	 * 
	 * @return void
	 */
	public void close() {
		try {
			econ.close();
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
	}
         public void releaseConnection() {
            long currentTime = System.currentTimeMillis();
            queryexecutedtime=currentTime-startingOfIdleness ;
            if(queryexecutedtime>60000){
                LogQuery();
            }
            this.inuse = false;
            setStartingOfIdleness();  
        }
        
        public void LogQuery(){
            String sTmp= getUsageTime()+"-"+getSQLString();
            QueryLogger.warn(sTmp);
        }
        private String getHumanReadingTime(long timeDifInMilliSec){
             String str="";
            try {
                 int SECOND = 1000;
            int MINUTE = 60 * SECOND;
            int HOUR = 60 * MINUTE;
            int DAY = 24 * HOUR;

            long ms = timeDifInMilliSec;
            StringBuffer text = new StringBuffer("");
            if (ms > DAY) {
              text.append(ms / DAY).append(" days ");
              ms %= DAY;
            }
            if (ms > HOUR) {
              text.append(ms / HOUR).append(" hours ");
              ms %= HOUR;
            }
            if (ms > MINUTE) {
                text.append(ms / MINUTE).append(" minutes ");
                ms %= MINUTE;
            }
              if (ms > SECOND) {
                text.append(ms / SECOND).append(" seconds ");
                ms %= SECOND;
            }
            text.append(ms + " ms");
            str=text.toString();
            } catch (Exception e) {
            }
            return str;
        }
        public String getIdleTime(){
//            String strpattern="dd-MM-yyyy hh:mm:ss:SS a";
            String str="";
        try {
                long timeDifInMilliSec =System.currentTimeMillis()-startingOfIdleness;
                str=getHumanReadingTime(timeDifInMilliSec);
            } catch (Exception e) {}
            return str;
        }
        
	
        public  String getUsageTime(){
            String str="";
        try {
            long timeDifInMilliSec =queryexecutedtime;
            str=getHumanReadingTime(timeDifInMilliSec);
            } catch (Exception e) {}
        return str;
     }
       
	public String getConCreatedTimeString() {
		return getmilltodate(this.concreatedtime);
	} 
        public  String getmilltodate(long millis) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm:ss:SSSS a");    
            java.util.Date resultdate = new java.util.Date(millis);
//            System.out.println(sdf.format(resultdate));
            return sdf.format(resultdate);
    } 
        
}//Class End