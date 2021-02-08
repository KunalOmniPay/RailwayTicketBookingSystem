package com.mindtree.Railway_Ticket_Booking_System.dao;

import java.util.List;

import com.mindtree.Railway_Ticket_Booking_System.entity.Train;
import com.mindtree.Railway_Ticket_Booking_System.exception.daoException.RailwayBookingSystemDaoException;

public interface TrainDao {
	public String saveTrainData(Train train) throws RailwayBookingSystemDaoException;

	public List<Train> getTrains() throws RailwayBookingSystemDaoException;
}
