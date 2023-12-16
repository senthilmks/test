package com.test;
import java.sql.Connection;
import java.sql.DriverManager;


public class SQLDBConnection 
{
	  public Connection getConnection() throws Exception       
	  {
	    // Get connection
	    //DriverManager.registerDriver(new com.microsoft.jdbc.sqlserver.SQLServerDriver());		 		  
		  
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();//SQL Server 2005 JDBC driver
		 
		 
//		 String url="jdbc:sqlserver://172.16.111.36;database=BAS;"; 
//		 Connection connection = DriverManager.getConnection(url,"sa","Bio!@#srm2021()"); //Live db (14-01-2021 )
		 
		 String url="jdbc:sqlserver://172.16.111.40;database=BAS;";  // New facial live
		 Connection connection = DriverManager.getConnection(url,"sa","AgaNaanooruN00l#2021"); // Facial Live db (14-01-2021 )
		 

//		 String url="jdbc:sqlserver://192.168.168.221;database=BAS;"; // Dev
//		 Connection connection = DriverManager.getConnection(url,"sa","Admin@123");//Development tempdb 221 --- 23-05-2019	 
		 

		 
		//String url="jdbc:sqlserver://172.16.100.9;database=BAS;";

		 // String url="jdbc:sqlserver://172.16.111.36;database=BAS;";  Connection connection = DriverManager.getConnection(url,"sa","BioAdmin!#%");//Live DB 
		//String url="jdbc:sqlserver://172.16.0.205;encrypt=false;sslProtocol=TLSv1.1;database=BAS;"; Connection connection = DriverManager.getConnection(url,"sa","Emanager@2018");//Development db
		
		//String url="jdbc:sqlserver://172.16.0.205;database=BAS;loginTimeout=1;socketTimeout=1"; Connection connection = DriverManager.getConnection(url,"sa","Emanager@2018");//Development db
		// String url="jdbc:sqlserver://172.16.111.38;database=BAS;loginTimeout=5;socketTimeout=5";Connection connection = DriverManager.getConnection(url,"sa","Admin@123");//Development db replica of live
		//
        //           String url="jdbc:sqlserver://172.16.111.36;database=BAS;"; Connection connection = DriverManager.getConnection(url,"sa","Bio!@#srm2021()");  //Live db 23-04-2019
		//  String url="jdbc:sqlserver://172.16.111.36;database=BAS;"; Connection connection = DriverManager.getConnection(url,"sa","Bioet@2019");  //Live db 23-04-2019
		  // String url="jdbc:sqlserver://172.16.111.36;database=BAS;"; Connection connection = DriverManager.getConnection(url,"sa","Bioet@2019");//Live db (23-04-2019  )
		 	//String url="jdbc:sqlserver://172.16.0.205;database=BAS;"; Connection connection = DriverManager.getConnection(url,"sa","Emanager@2018");//Development db

			//String url="jdbc:sqlserver://172.16.111.36;database=BAS;"; Connection connection = DriverManager.getConnection(url,"sa","Bioet@2019");//Live db (23-04-2019  )
                        

		
		//String url="jdbc:sqlserver://172.16.0.205;database=BASJAN12;";// Latest Live Back db(For testing) 12/01/2017
		//System.out.println("DB using "+url);  R
		//String url="jdbc:sqlserver://10.1.32.43;database=BAS;";  // Testing
		//String url="jdbc:sqlserver://172.16.0.41/SQLEXPRESS/Databases/BAS:1357;user=sa;password=sa;";		
		//Connection connection = DriverManager.getConnection(url,"sa","bioadmin123"); // Testing	    	    
		
	    if (connection != null)  
	    {
	      //System.out.println("SQL Server 2005 Successfully connected");
	      // Meta data
	     /* DatabaseMetaData meta = connection.getMetaData();
	      System.out.println("\nDriver Information");
	      System.out.println("Driver Name: "+ meta.getDriverName());
//	      System.out.println("Driver Version: "+ meta.getDriverVersion());AttendanceMonthlyPrintforTeach
	      System.out.println("\nDatabase Information ");
	      System.out.println("Database Name: "+ meta.getDatabaseProductName());
	      System.out.println("Database Version: "+meta.getDatabaseProductVersion());*/
	    }
	    else
	    {
	    	System.out.println("SQL Server 2005 could not be connected");
	    }
	    return connection;
		  
		 /* Driver d = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		  String connectionUrl = "jdbc:sqlserver://172.16.0.41;database=BAS;";
		  Connection con = d.connect(connectionUrl, new Properties());*/
	 } // Test
}


