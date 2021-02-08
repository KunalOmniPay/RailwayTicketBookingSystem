package com.mindtree.Railway_Ticket_Booking_System.service;

import java.util.List;

import com.mindtree.Railway_Ticket_Booking_System.entity.Train;
import com.mindtree.Railway_Ticket_Booking_System.exception.serviceException.RailwayTicketBookingServiceException;

public interface TrainService {
	public String setTrain(Train train) throws RailwayTicketBookingServiceException;

	public List<Train> getTrainBySourceDestination(String source, String destination) throws RailwayTicketBookingServiceException;

	public Train findTrainOfGivenTrainId(int trainId) throws RailwayTicketBookingServiceException;
}
