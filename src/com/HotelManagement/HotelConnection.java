package com.HotelManagement;

import java.sql.Connection;

import com.dynamicconn.Conn1;

public class HotelConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=Conn1.mycon();
		 if (con != null) 
		 {
	        System.out.println("Connection is successful");
		 }
		

	}

}
