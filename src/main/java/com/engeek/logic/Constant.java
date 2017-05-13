package com.engeek.logic;

public class Constant {
	public static final int status_ok = 0;
	public static final int status_error = 1;

	public static final int error_non = 0;
	public static final int error_request = 1;
	public static final int error_db = 2;
	
	public static final int error_authen = 5;


	

	// JDBC driver name and database URL
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost/engeek?useUnicode=true&characterEncoding=utf-8";

	// Database credentials
	public static final String USER = "root";
	public static final String PASS = "orek@db16";

	
	
}
