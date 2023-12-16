/*
 * ConnectionPoolMonitorThread.java
 *
 * Created on August 30, 2018, 4:29 PM
 */
package com.test.util.db;

import java.util.Vector;

public class ConnectionMonitor extends Thread { 
        private long monitorTimeinMilliseconds=0;
        private int minimumConnections=0;
        private int connectionTimeOut=0;
        private String threadname="";
	protected Vector<PooledConnection> connectionPool = null; 
//        protected List<PooledConnection> connectionPool = new CopyOnWriteArrayList<PooledConnection>(); // Final Performance

	public ConnectionMonitor(String ThreadName,long _monitorTimeinMilliseconds,
                int _minimumConnections,int _connectionTimeOut,Vector<PooledConnection> connectionPool) {
                this.threadname=ThreadName;
                if(_monitorTimeinMilliseconds<5000){
                   _monitorTimeinMilliseconds=5000;
                }
                this.monitorTimeinMilliseconds=_monitorTimeinMilliseconds;
		this.connectionPool = connectionPool;
                this.minimumConnections=_minimumConnections;
                this.connectionTimeOut=_connectionTimeOut;
                
	}
        
	public void run() {
		while (true) // for the thread to run continuously
		{
//                    System.out.println("-----------------  " +threadname + " Thread Monitoring  -----------------");
                    if(connectionPool!=null && connectionPool.size()<=0){
                        try {
				Thread.sleep(monitorTimeinMilliseconds); 
			} catch (Exception e) {
				// Nothing to do special.
			}
                    }
                    int istart= connectionPool.size();
                    long idleness = 0;
                    long currentTime = 0;
                    PooledConnection pcon = null;
                    int freeConnections = 0;
                    for (int j = 0; j < connectionPool.size(); j++) {
                            pcon = (PooledConnection) connectionPool.elementAt(j);
                            if (pcon.inUse() == false) {
                                    freeConnections += 1;
                            }
                    }
			try {
				pcon = null;
				if (connectionPool.size() >= minimumConnections && freeConnections >= minimumConnections) {
					for (int i = connectionPool.size()-1; i >=0 ; i--) {
//                                        for( int i=0 ; i<= connectionPool.size()-1 ;i++){
                                            
						pcon = (PooledConnection) connectionPool.elementAt(i);
						if (pcon.inUse() == false) {
							idleness = pcon.getStartingOfIdleness();
							currentTime = System.currentTimeMillis();
							idleness = currentTime - idleness;
    							if (idleness >= connectionTimeOut && connectionPool.size() > minimumConnections) {
								pcon.close();
								connectionPool.removeElementAt(i);
							}
						} // end of outer if
					} // end of for
				} // end of if
//                                System.err.println("-----------------  " +threadname + " Thread Monitoring "+istart + " " + connectionPool.size());
			} // end of try
			catch (Exception e) {
				e.printStackTrace();
			}
 
			// going to sleep.
			try {
				Thread.sleep(monitorTimeinMilliseconds); 
			} catch (Exception e) {
				// Nothing to do special.
			}
//                    System.err.println("********** End Thread Called ****************"+connectionPool.size());
		} // end of outermost while loop for the thread.
	}
}//Class End