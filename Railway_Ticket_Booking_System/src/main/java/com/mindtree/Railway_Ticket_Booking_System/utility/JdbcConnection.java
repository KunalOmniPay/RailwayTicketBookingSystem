package com.mindtree.Railway_Ticket_Booking_System.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mindtree.Railway_Ticket_Booking_System.exception.daoException.ConnectionfailedException;


public class JdbcConnection {
	private static final String url = "jdbc:mysql://localhost:3306/RailwayTicketBooking";
	private static final String username = "root";
	private static final String password = "Tomphiker@25";

	public static Connection getConnection() throws ConnectionfailedException {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return connection;
	}

	public static void closeResource(Connection connection) throws ConnectionfailedException {

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException ex) {
				throw new ConnectionfailedException(ex.getMessage());
			}
		}
	}

	public static void closeResource(PreparedStatement ps) throws ConnectionfailedException {

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException ex) {
				throw new ConnectionfailedException(ex.getMessage());
			}
		}
	}

	public static void closeResource(ResultSet rs) throws ConnectionfailedException {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				throw new ConnectionfailedException(ex.getMessage());
			}
		}
	}

}
