package com.bridgeit.login;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

class callableStatement {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the student id ");
		int id = scanner.nextInt();
		
		Connection connection = null;
		CallableStatement callablestatement = null;
		String query = "{call bridgeit.getStudentName(?,?,?)}";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");

			callablestatement = connection.prepareCall(query);
			callablestatement.setInt(1, id);
			
			callablestatement.execute();
			callablestatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			String name = callablestatement.getString(2);
			System.out.println("Student Name : " + name);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (callablestatement != null) {
				try {
					callablestatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
