package com.mindtree.Railway_Ticket_Booking_System.dao;



import java.util.List;

import com.mindtree.Railway_Ticket_Booking_System.entity.User;
import com.mindtree.Railway_Ticket_Booking_System.exception.daoException.RailwayBookingSystemDaoException;

public interface UserDao {
public String saveUser(User user) throws RailwayBookingSystemDaoException;
public List<User> getUsers() throws RailwayBookingSystemDaoException;
public String UpdateFareTrainId(User userobj, int trainId, int fare)throws RailwayBookingSystemDaoException;
public List<User> getAllUsersOfGivenTrainId(int trainId) throws RailwayBookingSystemDaoException;
}
