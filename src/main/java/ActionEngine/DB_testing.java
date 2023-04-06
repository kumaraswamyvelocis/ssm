package ActionEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_testing {

	public static void main(String[] args) throws SQLException {

		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kumar","root","9291325038@Kumar");
		Statement stmt= con.createStatement();
		//String s= "INSERT INTO student_attendence VALUES(1,'RAVI',20,100)";
		String s= "select S_NO, s_name,age from student_attendence LIMIT 2 ";
		ResultSet result=stmt.executeQuery(s);
		 while(result.next()) {
			 
			  int sl = result.getInt("S_NO");
			  String s_name = result.getString("S_NAME");
			  int age = result.getInt("AGE");
			  
			  
			  System.out.println(sl+"   "+s_name+"  "+age);
		 }
	
		con.close();
		System.out.println("data entered");
	}}