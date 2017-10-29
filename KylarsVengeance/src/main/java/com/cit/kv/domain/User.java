package com.cit.kv.domain;

public class User {
	public enum UserType
	{
		Goblin,
		Knight,
		Wizard,
		Priest,
		Archer,
		Berserker
	}
	private Long id;
	private String userName;
	private String password;
	private String userType;	
	private int level;
	private Long balance;
	
	public User(Long id, String userName, String password, String userType, int level, Long balance) {
		super();
		this.id = id;
		this.setUserName(userName);
		this.setPassword(password);
		this.userType = userType;
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
				+ ", userName=" + userName
				+ ", password=" + password
				+ ", level=" + level
				+ ", balance=" + balance + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}		
}
