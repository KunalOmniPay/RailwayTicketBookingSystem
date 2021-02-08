package com.mindtree.Railway_Ticket_Booking_System.entity;

public class User implements Comparable<User>{
	private int userId;
	private String userName;
	private int fare;
	private int TrainId;

	public User() {
	}

	public User(int userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}
	
	public User(int userId, String userName, int TrainId, int fare) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.TrainId = TrainId;
		this.fare = fare;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public int getTrainId() {
		return TrainId;
	}

	public void setTrainId(int trainId) {
		TrainId = trainId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", fare=" + fare + ", TrainId=" + TrainId + "]";
	}

	public int compareTo(User o) {
		if(this.getFare()>o.getFare())
			return 1;
		return -1;
	}

}
