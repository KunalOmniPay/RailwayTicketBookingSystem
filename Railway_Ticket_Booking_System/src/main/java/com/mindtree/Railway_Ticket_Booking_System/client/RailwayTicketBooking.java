package com.mindtree.Railway_Ticket_Booking_System.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mindtree.Railway_Ticket_Booking_System.entity.Train;
import com.mindtree.Railway_Ticket_Booking_System.entity.User;
import com.mindtree.Railway_Ticket_Booking_System.exception.serviceException.RailwayTicketBookingServiceException;
import com.mindtree.Railway_Ticket_Booking_System.service.TrainService;
import com.mindtree.Railway_Ticket_Booking_System.service.UserService;
import com.mindtree.Railway_Ticket_Booking_System.service.implementation.TrainServiceImpl;
import com.mindtree.Railway_Ticket_Booking_System.service.implementation.UserServiceImpl;

public class RailwayTicketBooking {
	private static Scanner sc = new Scanner(System.in);
	private static TrainService trainService = new TrainServiceImpl();
	private static UserService userService = new UserServiceImpl();

	public static void main(String[] args) {
		boolean menu = true;
		do {
			displayMenu();
			int switchMenuInput = sc.nextInt();
			switch (switchMenuInput) {
			case 1: {
				String message = "";
				try {
					message = trainService.setTrain(getTrainData());
					System.out.println(message);
				} catch (RailwayTicketBookingServiceException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 2: {
				String message = "";
				try {
					message = userService.setUser(getUserData());
					System.out.println(message);
				} catch (RailwayTicketBookingServiceException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 3: {
				System.out.println("source");
				sc.nextLine();
				String source = sc.nextLine();
				System.out.println("Enter destination");
				String destination = sc.nextLine();
				List<Train> trains = new ArrayList<Train>();
				try {
					trains = trainService.getTrainBySourceDestination(source, destination);
				} catch (RailwayTicketBookingServiceException e) {
					System.out.println(e.getMessage());
				}
				displayTrain(trains);
				System.out.println("Enter TrainId");
				int trainId = sc.nextInt();
				System.out.println("Enter Userid");
				int userId = sc.nextInt();
				Train train = null;
				try {
					train = trainService.findTrainOfGivenTrainId(trainId);
				} catch (RailwayTicketBookingServiceException e) {
					System.out.println(e.getMessage());
				}
				int fare = train.getDistance() * 10;
				try {
					String message = userService.SetFareTrainId(fare, trainId, userId);
					System.out.println(message);
				} catch (RailwayTicketBookingServiceException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 4: {
				System.out.println("Enter train id");
				int trainId = sc.nextInt();
				try {
					List<User> user = userService.getAllUsersOfTrainId(trainId);
					displayUsers(user);
				} catch (RailwayTicketBookingServiceException e) {
					System.out.println(e.getMessage());
				}
			}
			}

		} while (menu);

	}

	private static void displayUsers(List<User> user) {
		for (User user1 : user) {
			System.out.println(user1.toString());
		}

	}

	public static void displayMenu() {
		System.out.println("1 Enter train data");
		System.out.println("2 Enter user data");
		System.out.println("3 Book Ticket");
		System.out.println("4 Show All Booking for a train");
	}

	public static Train getTrainData() {
		System.out.println("Enter train id");
		int trainId = sc.nextInt();
		System.out.println("Enter train name");
		sc.nextLine();
		String trainName = sc.nextLine();
		System.out.println("Enter Source");
		String source = sc.nextLine();
		System.out.println("Enter destination");
		String destination = sc.nextLine();
		System.out.println("Enter distance");
		int distance = sc.nextInt();
		Train train = new Train(trainId, trainName, source, destination, distance);
		return train;
	}

	public static User getUserData() {
		System.out.println("Enter user id");
		int userId = sc.nextInt();
		System.out.println("Enter User name");
		sc.nextLine();
		String userName = sc.nextLine();
		User user = new User(userId, userName);
		return user;
	}

	public static void displayTrain(List<Train> trains) {
		for (Train train : trains) {
			System.out.println(train.toString());
		}
	}
}
