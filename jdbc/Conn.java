package ly;

import  java.sql.*;
import  java.util.*;

public class Conn{
	private static String driverName = "net.sourceforge.jtds.jdbc.Driver";
	private static String url = "jdbc:jtds:sqlserver://localhost:1433/sg";
	private static String userName = "sa";
	private static String passWord = "sa";
	public static void main(String[] args) {
		
		
		System.out.print("please input select sql:");
		Scanner scanner = new Scanner(System.in);
        String sql = scanner.nextLine();
		while(!sql.equalsIgnoreCase("exit")){
			if (sql.toUpperCase().contains("SELECT")) {
				Connection conn = null;
				Statement st = null;
	        	ResultSet rs = null;
	        	try{
	        		conn = Util.getConnection(driverName , url , userName , passWord);
	        		System.out.print(conn);
	        		st = conn.createStatement();
	        		System.out.print(sql);
	        		rs = st.executeQuery(sql);
	                handleResultSet(rs);
	        	} catch (Exception e){
	        		 e.printStackTrace();
	        	}finally{
	        		Util.close(rs , st , conn);
	        		System.out.print("please input select sql:");
					sql = scanner.nextLine();
	        	}
	        	
			}else{
				printAndExit();
			}
		}
		System.exit(0);
		
	}

	public static void printAndExit(){
		System.out.println("input error!");
		//System.exit(0);
	}

	public static void handleResultSet(ResultSet rs) throws Exception{
        ResultSetMetaData rsmd = rs.getMetaData();
        int index = 1;
 		System.out.println();
        while (rs.next()){
            index++;

            for (int i = 1; i <= rsmd.getColumnCount(); i++)
            {
                String colName = rsmd.getColumnName(i);
                String colValue = rs.getString(colName);
                if (i != 1)
                {
                    System.out.print(" , ");
                }
                System.out.print(colName + " = " + colValue);
            }
            System.out.println();
        }

        System.out.println((index - 1) + " Record(s) Found.");
    }

}