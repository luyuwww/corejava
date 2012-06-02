package ly;

import  java.sql.*;

public class Util{
	public static void close(ResultSet rs, Statement st, Connection conn){
			close(rs);
			close(st,conn);
	}
	public static void close(Statement st,Connection conn){
		close(st);
		close(conn);	
	}
	public static void close(ResultSet rs){
		try{rs.close();}catch(SQLException e){e.printStackTrace();}
	}
	public static void close(Statement st){
		try{st.close();}catch(SQLException e){e.printStackTrace();}	
	}
	public static void close(Connection conn){
		try{conn.close();}catch(SQLException e){e.printStackTrace();}
	}
//jdbc:jtds:sqlserver://localhost:1433/lyb","sa","123456  
	public static Connection getConnection(String dirverName , String url , String userName,String passWord){
		Connection conn = null;
		try{Class.forName(dirverName);
		 conn = DriverManager.getConnection(url,userName,passWord);	
		}catch(Exception e){
		      e.printStackTrace();
		      System.out.println("a");
		      throw new RuntimeException(e.getMessage());
		    }
		return conn;
	}
}