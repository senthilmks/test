
package com.test.util.db;

public class DBConnectionException extends Exception {

	private static final long serialVersionUID = -3441535657668163364L;

	public DBConnectionException() {
	}

	public DBConnectionException(String errorMessage) { 
		super(errorMessage);
	}
 
}//Class End
