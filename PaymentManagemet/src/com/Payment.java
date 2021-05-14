package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	
		
		//A common method to connect to the DB
		private Connection connect(){
			Connection con = null;
			try{
				Class.forName("com.mysql.jdbc.Driver");

				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "");
				
			}catch (Exception e){
				e.printStackTrace();
			}
			
			return con;
		}
			
		
		
		//Insert Project Details
		public String InsertPayment(String Item,String Date , String P_Method ,String Amount){
			String output = "";
			try{
					Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for inserting."; 
				}
				
					
					// create a prepared statement
					String Sql = "INSERT INTO `paymentdetails`( `T_Ammount`, `P_Method`, `Date`) VALUES (?,?,?)";
					String query = " insert into paymentdetails (`T_Ammount`,`P_Method`,`Date`)" + " values (?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setString(1, Amount);
					preparedStmt.setString(2, P_Method);
					preparedStmt.setString(3, Date);
					// execute the statement3
					preparedStmt.execute();
					System.out.println("Connected");
					con.close();
					 
					 String newProj = ReadPayment(); 
					 output = "{\"status\":\"success\", \"data\": \"" + newProj + "\"}";
					 
					 }catch (Exception e)
					 {
						 
						 output = "{\"status\":\"error\", \"data\":\"Error while inserting the project.\"}"; 
						 System.err.println(e.getMessage());
					 }
			 return output;
		 }
		
		
		
		
		public String ReadPayment(){
			String output = "";
			try{
					Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for reading."; 
			}
					
				// Prepare the html table to be displayed
				output = 
						"<table border='1' >"+ 
						"<tr >" +
							 "<th >Payment Ammount</th>" +
							 "<th >Payment Method</th>" +
							 "<th>Date</th>" +
							 "<th>Update</th>" +
							 "<th>Remove</th>" +
						
						 "</tr>";
	
				 String query = "select * from paymentdetails";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 
				 // iterate through the rows in the result set
				 while (rs.next()){
					 
					 
					 String ID =  Integer.toString(rs.getInt("PID"));
					 String Ammount = rs.getString("T_Ammount");
					 String Method = rs.getString("P_Method");
					 String Date = (rs.getString("Date"));
	
					 
					 // Add into the html table
					 
					 //output += "<tr><td>" + ID + "</td>";
					 output += "<td>" + Date + "</td>";
					 output += "<td>" + Method + "</td>";
					 output += "<td>" +  Ammount+ "</td>";
					
		
					 
					 
					 // buttons
					
					 output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-userid='" + ID + "'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' "
								+ "class='btnRemove btn btn-danger' data-userid='" + ID + "'></td></tr>"; 
				 }
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
			 
			 
			 }catch (Exception e){
				 
				 output = "Error while reading the Projects.";
				 System.err.println(e.getMessage());
			 }
			 return output;
			 
		}
		
		
		
		public String UpdatePayment(String ID, String Date , String P_Method ,String Amount){ 
			String output = ""; 
			try{
				Connection con = connect(); 
				if (con == null){
					return "Error while connecting to the database for updating."; 
				} 
				
				 // create a prepared statement
				String query = "UPDATE paymentdetails SET T_Ammount=?,P_Method=?,Date=? WHERE PID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, Amount);
				preparedStmt.setString(2, P_Method);
				preparedStmt.setString(3, Date);
				// preparedStmt.setString(1, code);
				preparedStmt.setInt(4, Integer.parseInt(ID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				 
				 String newProj = ReadPayment(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newProj + "\"}";
				 
		
				 } catch (Exception e) {
					 
					 output = "{\"status\":\"error\", \"data\": \"Error while updating the project.\"}";
					 System.err.println(e.getMessage()); 
				 } 
				 return output; 
		 }
		
		

		public String DeletePayment(String ID) { 
			String output = ""; 
			try{ 
				Connection con = connect(); 
				if (con == null) { 
					return "Error while connecting to the database for deleting."; 
				} 
					// create a prepared statement
				String query = "delete from paymentdetails where PID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(ID));
				// execute the statement
				preparedStmt.execute();
				con.close();
					
					String newUser = ReadPayment(); 
					output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}"; 
					
			} catch (Exception e) { 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		}
		
}