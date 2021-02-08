package com.mindtree.Railway_Ticket_Booking_System.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.mindtree.Railway_Ticket_Booking_System.dao.TrainDao;
import com.mindtree.Railway_Ticket_Booking_System.dao.implementation.TrainDaoImpl;
import com.mindtree.Railway_Ticket_Booking_System.entity.Train;
import com.mindtree.Railway_Ticket_Booking_System.exception.daoException.RailwayBookingSystemDaoException;
import com.mindtree.Railway_Ticket_Booking_System.exception.serviceException.NoTrainOfSourceDestination;
import com.mindtree.Railway_Ticket_Booking_System.exception.serviceException.RailwayTicketBookingServiceException;
import com.mindtree.Railway_Ticket_Booking_System.exception.serviceException.TrainNotFoundException;
import com.mindtree.Railway_Ticket_Booking_System.exception.serviceException.TrainServiceException;
import com.mindtree.Railway_Ticket_Booking_System.service.TrainService;

public class TrainServiceImpl implements TrainService {
private TrainDao trainDao = new TrainDaoImpl();

	public String setTrain(Train train) throws RailwayTicketBookingServiceException {
		String message = "";
		try {
			message = trainDao.saveTrainData(train);
		} catch (RailwayBookingSystemDaoException e) {
			throw new TrainServiceException();
		}
		return message;
	}

	public List<Train> getTrainBySourceDestination(String source, String destination) throws RailwayTicketBookingServiceException {
		List<Train> trains = null;
		List<Train> trainsOfGivenSourceDestination = new ArrayList<Train>();
		try {
			trains = trainDao.getTrains();
			for(Train train : trains)
			{
				if(train.getSource().equals(source) && train.getDestination().equals(destination))
				{
					trainsOfGivenSourceDestination.add(train);
				}
			}
			if(trainsOfGivenSourceDestination.size()==0)
			{
				throw new NoTrainOfSourceDestination("No train is avialable of this source and destination");
			}
		} catch (RailwayBookingSystemDaoException e) {
			throw new TrainServiceException(e.getMessage());
		}
		return trainsOfGivenSourceDestination;
	}

	public Train findTrainOfGivenTrainId(int trainId) throws RailwayTicketBookingServiceException {
		List<Train> trains = new ArrayList<Train>();
		boolean isTrainExist = true; 
		Train trainobj = null;
		try {
			trains = trainDao.getTrains();
			for(Train train : trains)
			{
				if(train.getTrainId()==trainId)
				{
					isTrainExist = false;
					trainobj = train;
					break;
				}
			}
			if(isTrainExist)
			{
				throw new TrainNotFoundException("Train is not available of given id");
			}
		} catch (RailwayBookingSystemDaoException e) {
			throw new TrainServiceException(e.getMessage());
		}
		return trainobj;
	}

}
