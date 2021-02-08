package com.mindtree.Railway_Ticket_Booking_System.service.implementation;

import java.util.Collections;
import java.util.List;

import com.mindtree.Railway_Ticket_Booking_System.dao.UserDao;
import com.mindtree.Railway_Ticket_Booking_System.dao.implementation.UserDaoImpl;
import com.mindtree.Railway_Ticket_Booking_System.entity.User;
import com.mindtree.Railway_Ticket_Booking_System.exception.daoException.RailwayBookingSystemDaoException;
import com.mindtree.Railway_Ticket_Booking_System.exception.serviceException.RailwayTicketBookingServiceException;
import com.mindtree.Railway_Ticket_Booking_System.exception.serviceException.UserNotFound;
import com.mindtree.Railway_Ticket_Booking_System.exception.serviceException.UserServiceException;
import com.mindtree.Railway_Ticket_Booking_System.service.UserService;

public class UserServiceImpl implements UserService {
private UserDao userDao = new UserDaoImpl();
	public String setUser(User user) throws RailwayTicketBookingServiceException {
		String message = "";
		try {
			message = userDao.saveUser(user);
		} catch (RailwayBookingSystemDaoException e) {
			throw new UserServiceException(e.getMessage());
		}
		return message;
	}
	public String SetFareTrainId(int fare, int trainId, int userId) throws RailwayTicketBookingServiceException {
		List<User> users = null;
		User userobj = null;
		boolean isUserExist = true; 
		try {
			users = userDao.getUsers();
		} catch (RailwayBookingSystemDaoException e) {
			throw new UserServiceException(e.getMessage());
		}
		for(User user : users)
		{
			if(user.getUserId()==userId)
			{
				userobj = user;
				isUserExist = false;
				break;
			}
		}
		if(isUserExist)
		{
			throw new UserNotFound("User is not available of this id");
		}String message = "";
		try {
			message = userDao.UpdateFareTrainId(userobj, trainId, fare);
		} catch (RailwayBookingSystemDaoException e) {
			throw new UserServiceException(e.getMessage());
		}
		return message;
	}
	public List<User> getAllUsersOfTrainId(int trainId) throws RailwayTicketBookingServiceException {
		List<User> users = null;
		try {
			users = userDao.getAllUsersOfGivenTrainId(trainId);
		} catch (RailwayBookingSystemDaoException e) {
			throw new UserServiceException(e.getMessage());
		}
		Collections.sort(users);
		return users;
	}

}
