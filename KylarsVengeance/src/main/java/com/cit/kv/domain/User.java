package com.cit.kv.domain;

public class User {
	public enum UserType
	{
		admin,
		customer
	}
	private Long id;
	private String userType;
	private String fName;
	private String lName;
	private int level;
	private Long balance;
	
	public User(Long id, String userType, String fName, String lName, int level, Long balance) {
		super();
		this.id = id;
		this.userType = userType;
		this.fName = fName;
		this.lName = lName;
		this.level = level;
		this.balance = balance;	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id 
				+ ", userType=" + userType
				+ ", fName=" + fName
				+ ", lName=" + lName
				+ ", level=" + level
				+ ", balance=" + balance + "]";
	}		
}
