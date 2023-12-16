/*
 * ResultSetExtented.java
 *
 * Created on August 30, 2018, 4:29 PM
 */

package com.test.util.db;

import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author  Murali.S
 */
public class ResultSetExtented { 

	private java.sql.Statement lst;
	private java.sql.PreparedStatement pstmt;
	private java.sql.ResultSet lrs;

	public ResultSetExtented() { 

	}

	public java.sql.ResultSet getResultSetOld(com.test.util.db.PooledConnection argConExt, String argSql)
			throws java.sql.SQLException{
		try{
			lst = argConExt.econ.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//System.out.println(argSql);
			lrs = lst.executeQuery(argSql);
			argConExt.releaseConnection();
		}catch(SQLException se){
			argConExt.releaseConnection();
			System.out.println("Error: Function getResultSet " +argSql);
			SQLException iose = se;
			while(iose != null){
				System.out.println("SQLState: " + iose.getSQLState() +
						"SQLErrorCode: " + iose.getErrorCode()+
						"SQLMessage: " + iose.getMessage());
				iose = se.getNextException();
			}
			throw se;
		}
		return lrs;
	}

	public java.sql.ResultSet getResultSet(com.test.util.db.PooledConnection argConExt, String argSql,ArrayList<Object> queryParametersList)
			throws java.sql.SQLException{
		try{
			//pstmt = argConExt.econ.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt = argConExt.econ.prepareStatement(argSql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if(queryParametersList!=null && queryParametersList.size()>0) {
				int index=0;
				for(int i=0;i<queryParametersList.size();i++) {
					if(queryParametersList.get(i) instanceof String) {
						System.out.println("String");
					}
					else if(queryParametersList.get(i) instanceof Integer) {
						System.out.println("Integer");						
					}
					
					System.out.println("argSql >> "+argSql+" i >> " +i+ ", Obj Value >> "+queryParametersList.get(i).toString());
					pstmt.setObject(++index, queryParametersList.get(i));
				}
			}

			//lrs = pstmt.executeQuery(argSql);
			lrs = pstmt.executeQuery();
			argConExt.releaseConnection();
		}catch(SQLException se){
			argConExt.releaseConnection();
			System.out.println("Error: Function getResultSet " +argSql);
			SQLException iose = se;
			while(iose != null){
				System.out.println("getSQLState: " + iose.getSQLState() +
						"\n SQLErrorCode: " + iose.getErrorCode()+
						"\n SQLMessage: " + iose.getMessage());
				iose = se.getNextException();
			}
			throw se;
		}
		return lrs;
	}

	/*protected void finalize() throws Throwable {
        try {
            try{
                if (lrs.isAfterLast()){
                    lrs.close();
                }
            }catch(Exception e){}            
            lrs = null;
            lst.close();
            lst = null;
            //System.out.println("finalize ok");
        }catch(Exception e){
            System.out.println("Error in finalize"+e);
        }finally {
            super.finalize();
        }
    }*/
	
// To be used for Type conversion with prepared statement if necessary	
//	public void setObject(int parameterIndex, Object parameterObj) throws SQLException {
//	    synchronized (checkClosed().getConnectionMutex()) {
//	        if (parameterObj == null) {
//	            setNull(parameterIndex, java.sql.Types.OTHER);
//	        } else {
//	            if (parameterObj instanceof Byte) {
//	                setInt(parameterIndex, ((Byte) parameterObj).intValue());
//	            } else if (parameterObj instanceof String) {
//	                setString(parameterIndex, (String) parameterObj);
//	            } else if (parameterObj instanceof BigDecimal) {
//	                setBigDecimal(parameterIndex, (BigDecimal) parameterObj);
//	            } else if (parameterObj instanceof Short) {
//	                setShort(parameterIndex, ((Short) parameterObj).shortValue());
//	            } else if (parameterObj instanceof Integer) {
//	                setInt(parameterIndex, ((Integer) parameterObj).intValue());
//	            } else if (parameterObj instanceof Long) {
//	                setLong(parameterIndex, ((Long) parameterObj).longValue());
//	            } else if (parameterObj instanceof Float) {
//	                setFloat(parameterIndex, ((Float) parameterObj).floatValue());
//	            } else if (parameterObj instanceof Double) {
//	                setDouble(parameterIndex, ((Double) parameterObj).doubleValue());
//	            } else if (parameterObj instanceof byte[]) {
//	                setBytes(parameterIndex, (byte[]) parameterObj);
//	            } else if (parameterObj instanceof java.sql.Date) {
//	                setDate(parameterIndex, (java.sql.Date) parameterObj);
//	            } else if (parameterObj instanceof Time) {
//	                setTime(parameterIndex, (Time) parameterObj);
//	            } else if (parameterObj instanceof Timestamp) {
//	                setTimestamp(parameterIndex, (Timestamp) parameterObj);
//	            } else if (parameterObj instanceof Boolean) {
//	                setBoolean(parameterIndex, ((Boolean) parameterObj).booleanValue());
//	            } else if (parameterObj instanceof InputStream) {
//	                setBinaryStream(parameterIndex, (InputStream) parameterObj, -1);
//	            } else if (parameterObj instanceof java.sql.Blob) {
//	                setBlob(parameterIndex, (java.sql.Blob) parameterObj);
//	            } else if (parameterObj instanceof java.sql.Clob) {
//	                setClob(parameterIndex, (java.sql.Clob) parameterObj);
//	            } else if (this.connection.getTreatUtilDateAsTimestamp() && parameterObj instanceof java.util.Date) {
//	                setTimestamp(parameterIndex, new Timestamp(((java.util.Date) parameterObj).getTime()));
//	            } else if (parameterObj instanceof BigInteger) {
//	                setString(parameterIndex, parameterObj.toString());
//	            } else {
//	                setSerializableObject(parameterIndex, parameterObj);
//	            }
//	        }
//	    }
//	}
}//Class End