package com.mindtree.Railway_Ticket_Booking_System.service;

import java.util.List;

import com.mindtree.Railway_Ticket_Booking_System.entity.User;
import com.mindtree.Railway_Ticket_Booking_System.exception.serviceException.RailwayTicketBookingServiceException;

public interface UserService {
public String setUser(User user) throws RailwayTicketBookingServiceException;

public String SetFareTrainId(int fare, int trainId, int userId) throws RailwayTicketBookingServiceException;

public List<User> getAllUsersOfTrainId(int trainId) throws RailwayTicketBookingServiceException;
}
