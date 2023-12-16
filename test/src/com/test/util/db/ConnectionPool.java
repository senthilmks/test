/*
 * ConnectionPool.java
 *
 * Created on August 30, 2018, 4:29 PM
 */
package com.test.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong; 

public class ConnectionPool {

	private String driver = null;
	private String url = null;
//	private int isize = 0;
	private String username = null;
	private String password = null;
	private Vector<PooledConnection> pool = null;
	private int minimumConnections = 0;
	private int connectionTimeOutInMinutes = 0;
        private int startupconnections=0;
        private int maximumConnections=1;
        
//        private AtomicLong lngWaitCount = new AtomicLong();
        
	public ConnectionPool() {
	}
//        public long getWaitCount(){return lngWaitCount.get();}
        
	public Vector<PooledConnection> getConnectionPool() {
		return pool;
	}

	public void setDriver(String value) {
		if (value != null) {
			driver = value;
		}
	}

	public String getDriver() {
		return driver;
	}

	public void setURL(String value) {
		if (value != null) {
			url = value;
		}
	}

	public String getURL() {
		return url;
	}

	/**
	 * Sets the maximum size of the connection pool.
	 * 
	 * @param value
	 *            pool value
	 * @return void
	 */
//	public void setSize(int value) {
//		if (value > 1) {
//			isize = value;
//		}
//	}

	/**
	 * Retrieves the size of the connection pool.
	 * 
	 * @return integer - Size of the connection pool.
	 */
//	public int getSize() {
//		return isize;
//	}

	public void setUserName(String value) {
		if (value != null) {
			username = value;
		}
	}

	public String getUserName() {
		return username;
	}

	public void setPassword(String value) {
		if (value != null) {
			password = value;
		}
	}

	public String getPassword() {
		return password;
	}

	private Connection createConnection() throws Exception {
		Connection con = null;
		try {
//                    System.out.println("url->"+url);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
                    System.out.println(url.substring(url.lastIndexOf(":") + 1).toUpperCase()
					+ " Domain not initialized in the ODBC datasource in the Server");
			throw new Exception(url.substring(url.lastIndexOf(":") + 1).toUpperCase()
					+ " Domain not initialized in the ODBC datasource in the Server");
		}
		return con;
	}

	public void SetMaximumConnections(int value) {
		this.maximumConnections = value;
		return;
	}
	public void setMinimumConnections(int value) {
		this.minimumConnections = value;
		return;
	}
        public void setStartUpConnections(int value){
            this.startupconnections=value;
        }
        public int getStartUpConnections(){
            return this.startupconnections;
        }

	public void setConnectionTimeOutInMinutes(int value) {
		this.connectionTimeOutInMinutes = value;
		return;
	}

	public int getMaximumConnections() {
		return this.maximumConnections;
	}
	public int getMinimumConnections() {
		return this.minimumConnections;
	}

	public int getConnectionTimeOutInMinutes() {
		return this.connectionTimeOutInMinutes;
	}
        public String getConnectionTimeOutInMinutesString() {
		return getHumanReadingTime(this.connectionTimeOutInMinutes);
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
	public synchronized void initializePool() throws Exception {

		if (driver == null) {
//                        System.out.println("No Driver name Specified");
			throw new Exception("No Driver name Specified");
		}
		if (url == null) {
                        System.out.println("No URL Specified");
			throw new Exception("No URL Specified");
		}
//		if (isize < 1) {
//			throw new Exception("Pool size is less than 1");
//		}
		try {
			Class.forName(driver);
                        if (pool == null) {
                            pool = new Vector<PooledConnection>(this.minimumConnections+startupconnections);
                        }
			for (int x = 0; x < this.minimumConnections+startupconnections; x++) {
				Connection con = createConnection();
				if (con != null) {
					PooledConnection pcon = new PooledConnection(con);
					addConnection(pcon);
					pcon.setStartingOfIdleness();
				}
			}
		} catch (Exception e) {
                        System.out.println("initializePool DataBase Connection Failed "+e.toString());
			throw new Exception("initializePool DataBase Connection Failed "+e.toString());
		}

	}

	private void addConnection(PooledConnection value) {
		if (pool == null) {
			pool = new Vector<PooledConnection>(minimumConnections);
		}
//                pool.add(value);
		pool.addElement(value);
	}

	private boolean getConnectionStatus(PooledConnection pcon){
            Connection conn = null;
            PreparedStatement pstmt = null;
            boolean bResult=false;
            String sqlQuery = "SELECT 1";   
            try {
                conn = pcon.getConnection();
                pstmt = conn.prepareStatement(sqlQuery);
                bResult=pstmt.execute();
                pstmt.close();
            }catch(SQLException se){
                bResult=false;
            }catch (Exception e) {
                bResult=false;
            }
            return bResult;
        }

	public void releaseConnection(Connection con) throws DBConnectionException {
		{
                    boolean bReleased=false; 
			for (int x = 0; x < pool.size(); x++) {
                                PooledConnection pcon = (PooledConnection) pool.get(x);
				if (pcon.getConnection() == con) {
                                    pcon.releaseConnection();
//					pcon.setInUse(false);
//					pcon.setStartingOfIdleness();
                                        bReleased=true;
					break;
				}
			}
                        if(bReleased==false && con!=null){
                            //This connection not found in the connection pool. unable to reuse so close this connection.
                            try {con.close();} catch (Exception e) {}
                        }
		}
	}
	private void CloseConnection(Connection con) throws DBConnectionException {
		{
                    boolean bReleased=false;
			for (int x = 0; x < pool.size(); x++) {
                                PooledConnection pcon = (PooledConnection) pool.get(x);
				if (pcon.getConnection() == con) {
					pcon.setInUse(true);
					pcon.close();
                                        pool.removeElementAt(x);
                                        bReleased=false;
					break;
				}
			}
                        if(bReleased==false && con!=null){
                            //This connection not found in the connection pool. unable to reuse so close this connection.
                            try {con.close();} catch (Exception e) {}
                        }
		}
	}
//        public void Sleep(long millsec){
//            long lWaitedtime=0;
//            int x=0;
//            long count=0;
//            long currentTime=System.currentTimeMillis();
//            while(lWaitedtime<=millsec){
//                ++count;
//                for(x=0;x<=255;x++){
//                    Thread.yield();
////                    System.out.print("");
//                }
//                lWaitedtime=System.currentTimeMillis()-currentTime;
//                
//            }
//            System.out.println("Sleep End "+count + " "+lWaitedtime + " "+java.lang.Thread.currentThread().getName());
//        }
	public PooledConnection getConnection() throws DBConnectionException {
            return getConnection(true);
        }
	private void resetConnection(PooledConnection pcon){
            boolean bFound=false;
            try {
                    pcon.setInUse(true);
                    CloseConnection(pcon.econ);
                    pcon.setSQLString("Bad Connection Closing..");
                    pcon.econ = createConnection();
                    pcon.Reset();
                    pcon.setSQLString("Reset Connection Checking..");
                    for (int x = 0; x < pool.size(); x++) {
                        PooledConnection pconn = (PooledConnection) pool.get(x);
                        if (pconn!=null && pconn.getConnection() == pcon.econ) {
                            bFound=true;
                        }
                    }
                    if(bFound==false && pcon.econ!=null){
                        pcon.setInUse(true);
                        addConnection(pcon);
                    }
                    pcon.setSQLString("Reset Connection Success..");
                
                } catch (Exception e) {
			System.out.println("resetConnection Database connection failed "+e.toString());
		}
            
         }

	private PooledConnection getConnection(boolean bNew) throws DBConnectionException {
		int x = 0;PooledConnection pcon = null;
		try {
                    boolean blnDoNotExitLoop = true;
                     while(blnDoNotExitLoop || pcon == null){
                         for( x=pool.size()-1; x>=0 ;x--){
//			for (x=0; x < pool.size(); x++) {
				pcon = (PooledConnection) pool.elementAt(x);
				if (pcon.inUse() == false) {
                                    pcon.setInUse(true);
                                    blnDoNotExitLoop=false;
                                    break;
				}else{
                                    pcon=null;
                                }
			}
                        if(pcon!=null && blnDoNotExitLoop==false){
                            break;
                        }
                        if(pool.size()<maximumConnections){
//                            System.out.println("Pool Size->"+pool.size());
                            Connection con = createConnection();
                            pcon = new PooledConnection(con);
                            pcon.setInUse(true);
                            pool.addElement(pcon);
                             blnDoNotExitLoop=false;
                             break;
                        }else{ 
//                            if(bNew==true){bNew=false;lngWaitCount.incrementAndGet();}
                            //System.out.println("Thread yield "+java.lang.Thread.currentThread().getName());
                            for (int lintYield = 0; lintYield <= 127; lintYield++){                    
                                java.lang.Thread.yield();
                            }
//                            synchronized(this){
//                                try {wait(500);} catch (Exception e) {}
//                            }
                            try {java.lang.Thread.sleep(100);} catch (Exception e) {}
                        }
                    }//While Loop
//                     
                    if(!getConnectionStatus(pcon)){
                        pcon.setSQLString("Bad Connection Reseting..");
                        resetConnection(pcon);
                    }
                    pcon.setInUse(true);
                    pcon.incrementUsageCounter();
                    pcon.setStartingOfIdleness();
//                    if(lngWaitCount.get()>0 && bNew==false){lngWaitCount.decrementAndGet();}
                    return(pcon);
                        
		} catch (Exception e) {
                    System.out.println("Database connection failed "+e.toString());
			throw new DBConnectionException("Database connection failed "+e.toString());
		}
	}

	public synchronized void emptyPool() {
		System.err.println("Close Connection");
		for (int x = 0; x < pool.size(); x++) {
			System.err.println("Closing JDBC Connection " + x);
			PooledConnection pcon = (PooledConnection) pool.elementAt(x);
//			PooledConnection pcon = (PooledConnection) pool.get(x);
			if (pcon.inUse() == false) {
				pcon.close();
			} else {
				try {
					java.lang.Thread.sleep(30000);
					pcon.close();
				} catch (InterruptedException ie) {
					System.err.println(ie.getMessage());
				}
			}
		}
	}
 
        public int getSize(){
            if( pool!=null){
                 return pool.size();
            }
            return -1; 
        }        
}//Class End
