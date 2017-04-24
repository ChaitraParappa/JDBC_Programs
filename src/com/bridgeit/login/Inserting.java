package com.bridgeit.login;

import java.sql.*;
import java.util.Scanner;

class Inserting {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet resultset = null;

		String query = "select name  from bridgeit.user where name=? and password=?";
		String query1 = "insert into bridgeit.user values(?,?)";

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the user name");
		String name = scanner.next();
		System.out.println("Enter the password");
		String password = scanner.next();
		scanner.close();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			System.out.println("connection");

			preparedstatement = connection.prepareStatement(query1);
			preparedstatement.setString(1, name);
			preparedstatement.setString(2, password);
			preparedstatement.execute();
			preparedstatement = connection.prepareStatement(query);
			preparedstatement.setString(1, name);
			preparedstatement.setString(2, password);
			resultset = preparedstatement.executeQuery();
			
			if (resultset.next()) {
				String username = resultset.getString(1);
				System.out.println("welcome " + username);
			} else {
				System.out.println("invalid name or password");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultset != null) {
				try {
					resultset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedstatement != null) {
				try {
					preparedstatement.close();
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
