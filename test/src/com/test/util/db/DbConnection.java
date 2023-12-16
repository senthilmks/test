/*
 * DbConnection.java
 *
 * Created on August 30, 2018, 4:29 PM
 */
package com.test.util.db;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 *
 * @author Murali.S
 */
public class DbConnection {
    private ConnectionPool connPool = null; 
//    private Vector<String> Waitpool = null;
    private long _MonitorTime=10000;
    private int obtainedmaxhint=0;
    
    public DbConnection(String ThreadName,int argStatupconnection,int argMinNoOfconnection,
            int argMaxNoOfconnection, int argWaitTime,long MonitorTime){
        try {
            _MonitorTime=MonitorTime;
            initializePool(ThreadName,argStatupconnection,argMinNoOfconnection,argMaxNoOfconnection,argWaitTime,MonitorTime);
            
        } catch (Exception e) {
        }
     }
     
    private void initializePool(String ThreadName,int argStatupconnection,int argMinNoOfconnection,
            int argMaxNoOfconnection, int argWaitTime,long MonitorTime) throws DBConnectionException{
          if(connPool==null){
            connPool = new ConnectionPool();
        }
          try {
                connPool.setDriver(ConnectionExtented.driver);
                connPool.setURL( ConnectionExtented.url);
                connPool.setUserName(ConnectionExtented.userid);
                connPool.setPassword(ConnectionExtented.password);
//                connPool.setSize(ConnectionExtented.poolsize);
                connPool.setMinimumConnections(argMinNoOfconnection);
                connPool.SetMaximumConnections(argMaxNoOfconnection);
                connPool.setConnectionTimeOutInMinutes(argWaitTime);
                connPool.setStartUpConnections(argStatupconnection);              
                connPool.initializePool();
                ConnectionMonitor connectionPoolMonitorThread = new ConnectionMonitor(ThreadName,MonitorTime,argMinNoOfconnection,argWaitTime,connPool.getConnectionPool());   
                connectionPoolMonitorThread.start();
        } catch (Exception e) {
            throw new DBConnectionException("DataBase Connection Failed");
        }
        
    }
//    private void addWaitPool(String sVal){
//        if(Waitpool==null){
//            Waitpool = new Vector<String>(1);
//        }
//        Waitpool.add(sVal);
//    }
//    private boolean removeWaitPool(String sVal){
//        if(Waitpool!=null){
//            return Waitpool.remove(sVal);
//        }
//        return false;
//    }
    
    public PooledConnection getConnection(String argSql){
        PooledConnection tmp = this.getConnection();
         setMaxCount();
        tmp.setSQLString(argSql);
        return tmp;
    }
     
    public PooledConnection getConnection(java.lang.Object obj){
        PooledConnection tmp = this.getConnection();
         setMaxCount();
        tmp.setSQLString(obj.getClass() + " " + obj.toString());
         return tmp;
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
    private void setMaxCount(){
        if(connPool.getSize()>obtainedmaxhint){
                obtainedmaxhint=connPool.getSize();
            }
    }
    public PooledConnection getConnection() {
        try {
           return connPool.getConnection();
        } catch (DBConnectionException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
    public Vector<PooledConnection> getAllConnections() {
        return connPool.getConnectionPool();
    }
//     public long getWaitCount(){return connPool.getWaitCount();}
     public long getWaitCount(){
//         return (Waitpool!=null?Waitpool.size():0);
         return 0;
     }
     
//     public  Vector<String> getWaitListQuery(){return Waitpool;}
     
     public ConnectionPool getPool(){
         return connPool;
     }
     public String getMonitorTime(){
//         return _MonitorTime;
//        return "Monitor Time";
         return getHumanReadingTime(_MonitorTime);
     }
     public int getMaxHitCount(){
         return obtainedmaxhint;
     }
     
}//Class End
