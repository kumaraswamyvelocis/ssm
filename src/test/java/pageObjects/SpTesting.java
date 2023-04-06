package pageObjects;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class SpTesting {
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs;
	CallableStatement cstmt;
	ResultSet rs1;
	ResultSet rs2;
	
	@BeforeClass
	void setup() throws SQLException {
		con= DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","9291325038@Kumar");
	}
	@AfterClass
	 void teardown() throws SQLException {
		 
		 con.close();
	 }

	 @Test(priority = 1)
	 void TestStoreProceduresExit() throws SQLException {
		stmt= con.createStatement();
		rs= stmt.executeQuery("show procedure status where name = 'xxx'");
		rs.next();
		Assert.assertEquals(rs.getString("name"),"xxx");
	 }	 
	 @Test(priority = 2)
	 void Test_ALLEMPLOYS() throws SQLException 
	 { 
		 cstmt= con.prepareCall("{call database1_enum_mdname_(?,?)}");
		 cstmt.setString(1, "7369");
		 cstmt.setString(2, "q");
		rs1= cstmt.executeQuery();
		Statement stmt = con.createStatement();
		rs2= stmt.executeQuery("select * from data where emp_id=7369 and md_name = 'q'");
		 Assert.assertEquals(compareResultSets(rs1,rs2),true);
	 }
		 public  boolean compareResultSets(ResultSet resultset1,ResultSet resltset2 ) throws SQLException{
			while(resultset1.next())
			 {
				resltset2.next();
				int count= resultset1.getMetaData().getColumnCount();
				for(int i=1;i<=count;i++)
				{
					if(!StringUtils.equals(resultset1.getString(i),resltset2.getNString(i)))
					{
						return false;
					}
				}
			 }
			return true;
			}}
		 
		
		 