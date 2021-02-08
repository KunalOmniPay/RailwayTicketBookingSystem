package com.mindtree.Railway_Ticket_Booking_System.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mindtree.Railway_Ticket_Booking_System.dao.TrainDao;
import com.mindtree.Railway_Ticket_Booking_System.entity.Train;
import com.mindtree.Railway_Ticket_Booking_System.exception.daoException.ConnectionfailedException;
import com.mindtree.Railway_Ticket_Booking_System.exception.daoException.RailwayBookingSystemDaoException;
import com.mindtree.Railway_Ticket_Booking_System.exception.daoException.TraindaoException;
import com.mindtree.Railway_Ticket_Booking_System.utility.JdbcConnection;

public class TrainDaoImpl implements TrainDao{

	public String saveTrainData(Train train) throws RailwayBookingSystemDaoException {
		String message = "";
		Connection con = null;
		try {
			con = JdbcConnection.getConnection();
		}catch(ConnectionfailedException e)
		{
			throw new TraindaoException(e.getMessage());
		}
		String sql = "insert into Train values(?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, train.getTrainId());
			statement.setString(2, train.getTrainName());
			statement.setString(3, train.getSource());
			statement.setString(4, train.getDestination());
			statement.setInt(5, train.getDistance());
			int rowInserted = statement.executeUpdate();
			message = "Train inserted successfully";
		}catch(SQLException e)
		{
			throw new TraindaoException(e.getMessage());
		}
		finally {
			JdbcConnection.closeResource(statement);
			JdbcConnection.closeResource(con);
		}
		return message;
	}

	public List<Train> getTrains() throws RailwayBookingSystemDaoException {
		Connection con = null;
		try {
			con = JdbcConnection.getConnection();
		}catch(ConnectionfailedException e)
		{
			throw new TraindaoException(e.getMessage());
		}
		String sql = "Select * from Train";
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<Train> trains = new ArrayList<Train>();
		try {
			statement = con.prepareStatement(sql);
			rs = statement.executeQuery();
			while(rs.next())
			{
				Train train = new Train(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				trains.add(train);
			}
		}catch(SQLException e)
		{
			throw new TraindaoException(e.getMessage());
		}
		
		return trains;
	}

	
		
	

}
