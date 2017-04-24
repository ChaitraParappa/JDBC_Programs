package com.bridgeit.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class batchUpdate {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		PreparedStatement preparedstatement1 = null;
		Scanner scanner = new Scanner(System.in);
		String insertqry = "insert into bridgeit.student values(?,?,?)";
		String deleteqry = "delete from bridgeit.student where id=4";
		System.out.println("Enter the student id ");
		int id = scanner.nextInt();
		System.out.println("Enter the student name ");
		String name = scanner.next();
		System.out.println("Enter the branch ");
		String branch = scanner.next();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");

			preparedstatement = connection.prepareStatement(insertqry);
			preparedstatement.setInt(1, id);
			preparedstatement.setString(2, name);
			preparedstatement.setString(3, branch);

			preparedstatement.addBatch();
			int[] arr = preparedstatement.executeBatch();
			for (int i : arr) {
				System.out.println(i);
			}

			preparedstatement1 = connection.prepareStatement(deleteqry);
			preparedstatement1.addBatch();
			int[] arr1 = preparedstatement1.executeBatch();
			for (int i : arr1) {
				System.out.println(i);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		finally {
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
