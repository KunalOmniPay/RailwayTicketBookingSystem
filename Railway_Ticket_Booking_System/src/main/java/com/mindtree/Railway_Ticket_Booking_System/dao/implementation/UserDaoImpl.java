package com.mindtree.Railway_Ticket_Booking_System.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mindtree.Railway_Ticket_Booking_System.dao.UserDao;
import com.mindtree.Railway_Ticket_Booking_System.entity.User;
import com.mindtree.Railway_Ticket_Booking_System.exception.daoException.ConnectionfailedException;
import com.mindtree.Railway_Ticket_Booking_System.exception.daoException.RailwayBookingSystemDaoException;
import com.mindtree.Railway_Ticket_Booking_System.exception.daoException.UserDaoException;
import com.mindtree.Railway_Ticket_Booking_System.utility.JdbcConnection;

public class UserDaoImpl implements UserDao{

	public String saveUser(User user) throws RailwayBookingSystemDaoException {
		String message = "";
		Connection con = null;
		try {
			con = JdbcConnection.getConnection();
		}catch(ConnectionfailedException e)
		{
			System.out.println(e.getMessage());
		}
		String sql = "Insert into User(UserId, UserName) values(?,?)";
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, user.getUserId());
			statement.setString(2, user.getUserName());
		int rowInserted = statement.executeUpdate();
		message = "User inserted succefully";
		}catch(SQLException e)
		{
			throw new UserDaoException(e.getMessage());
		}
		finally {
			JdbcConnection.closeResource(statement);
			JdbcConnection.closeResource(con);
		}
		return message;
	}

	public List<User> getUsers() throws RailwayBookingSystemDaoException {
		List<User> users = new ArrayList<User>();
		Connection con = null;
		try {
			con = JdbcConnection.getConnection();
		}catch(ConnectionfailedException e)
		{
			throw new UserDaoException(e.getMessage());
		}
		String sql = "Select * from User";
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next())
			{
				User user = new User(rs.getInt(1), rs.getString(2));
				users.add(user);
			}
		}catch(SQLException e)
		{
			throw new UserDaoException(e.getMessage());
		}
		
		return users;
	}

	public String UpdateFareTrainId(User userobj, int trainId, int fare) throws RailwayBookingSystemDaoException {
		String message = "";
		Connection con = null;
		try {
			con = JdbcConnection.getConnection();
		}catch(ConnectionfailedException e)
		{
			throw new UserDaoException(e.getMessage());
		}
		String sql = "update User set Trainid = ?, fare = ? where UserId = ?";
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, trainId);
			statement.setInt(2, fare);
			statement.setInt(3, userobj.getUserId());
			statement.executeUpdate();
			message = "Ticket Booked Successfully and calculated fare is " + fare;
		}catch(SQLException e)
		{
			throw new UserDaoException(e.getMessage());
		}
		return message;
	}

	public List<User> getAllUsersOfGivenTrainId(int trainId) throws RailwayBookingSystemDaoException {
		List<User> users = new ArrayList<User>();
		Connection con = null;
		try {
			con = JdbcConnection.getConnection();
		}catch(ConnectionfailedException e)
		{
			throw new UserDaoException(e.getMessage());
		}
		String sql = "select * from User where TrainId = trainId";
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, trainId);
			rs = statement.executeQuery();
			User user = new User(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			users.add(user);
		}catch(SQLException e)
		{
			throw new UserDaoException(e.getMessage());
		}
		return users;
	}


}
