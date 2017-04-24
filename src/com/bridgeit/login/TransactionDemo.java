package com.bridgeit.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Scanner;

class TransactionDemo {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		PreparedStatement preparedstatement1 = null;
		Savepoint savepoint=null;
		

		String query = "insert into bridgeit.student values(?,?,?)";
		String query1 = "insert into bridgeit.studentaddress values(?,?,?,?)";

		System.out.println("Enter the student id ");
		int id = scanner.nextInt();

		System.out.println("Enter the student name ");
		String name = scanner.next();

		System.out.println("Enter the student branch ");
		String branch = scanner.next();

		System.out.println("Enter the student age ");
		int age = scanner.nextInt();

		System.out.println("Enter the student address ");
		String address = scanner.next();

		System.out.println("Enter the mobile number ");
		String mobilenumber = scanner.next();

		scanner.close();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");

			connection.setAutoCommit(false);
			preparedstatement = connection.prepareStatement(query);
			preparedstatement.setInt(1, id);
			preparedstatement.setString(2, name);
			preparedstatement.setString(3, branch);
			preparedstatement.executeUpdate();
			System.out.println("student educational details executed");
			savepoint=connection.setSavepoint();
			

			preparedstatement1 = connection.prepareStatement(query1);
			preparedstatement1.setInt(1, id);
			preparedstatement1.setInt(2, age);
			preparedstatement1.setString(3, address);
			preparedstatement1.setString(4, mobilenumber);
			preparedstatement1.executeUpdate();
			System.out.println("student personal details executed");
			connection.commit();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.rollback();
			System.out.println("operation rolled back");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedstatement1 != null) {
				try {
					preparedstatement1.close();
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
