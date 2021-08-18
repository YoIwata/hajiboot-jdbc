package com.example.hajibootjdbc;

import java.sql.*;

public class DBConnect {

	public static void main(String[] args) {

		String url = "jdbc:mysql://127.0.0.1:3306/YGETS";
		String username = "root";
		String password = "password";
		System.out.println("Connecting database...");

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");
			
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			System.out.println("JDBCドライバのバージョン: " + databaseMetaData.getDriverVersion());
			
			System.out.println("MySQLのバージョン: " + databaseMetaData.getDatabaseProductVersion());
			
			
			statement = connection.createStatement();
			String sql = "SELECT * FROM PLAN where HOTEL_ID = 0000000003;";
			
			resultSet = statement.executeQuery(sql);
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int count = resultSetMetaData.getColumnCount();
			System.out.println("行数は: " + count);
			
			while (resultSet.next()) {
				System.out.println(resultSet.getRow() + "行目");
				System.out.println("HOTEL_ID: " + resultSet.getString("HOTEL_ID"));
				System.out.println("PLAN_ID: " + resultSet.getString("PLAN_ID"));
			}
			
			resultSet.close();
			statement.close();
			
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
					System.out.println("切断しました");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
