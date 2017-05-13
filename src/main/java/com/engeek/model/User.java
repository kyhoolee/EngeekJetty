package com.engeek.model;

import java.sql.Timestamp;

import org.json.JSONObject;

public class User {
	private int id;
	private String userName;
	private String passWord;
	
	private String facebook;
	private String google;
	
	private Timestamp created;
	
	private String name;
	private String gender;
	private String avatarUrl;
	
	private int hint = -1;
	private int type = -1;
	private int paymentStatus = -1;
	private Timestamp paymentTime = new Timestamp(System.currentTimeMillis());
	
	private int invitedFriends;
	
	
	
	

	public User() {
		super();
		
	}
	
	public User(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		
		
	}

	public User(int id, String userName, String passWord, String facebook,
			String google, Timestamp created, String name, String gender,
			String avatarUrl, int hint, int type, Timestamp paymentTime,
			int invitedFriends) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.facebook = facebook;
		this.google = google;
		this.created = created;
		this.name = name;
		this.gender = gender;
		this.avatarUrl = avatarUrl;
		this.hint = hint;
		this.type = type;
		this.paymentTime = paymentTime;
		this.invitedFriends = invitedFriends;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getGoogle() {
		return google;
	}

	public void setGoogle(String google) {
		this.google = google;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public int getHint() {
		return hint;
	}

	public void setHint(int hint) {
		this.hint = hint;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Timestamp getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Timestamp paymentTime) {
		this.paymentTime = paymentTime;
	}

	public int getInvitedFriends() {
		return invitedFriends;
	}

	public void setInvitedFriends(int invitedFriends) {
		this.invitedFriends = invitedFriends;
	}
	
	
	
	public JSONObject toJSON() {
		JSONObject o = new JSONObject();
		
		o.put("id", this.getId());
		o.put("userName", this.getUserName());
		o.put("name", this.getName());
		
		o.put("facebook", this.getFacebook());
		o.put("google", this.getGoogle());
		
		o.put("gender", this.getGender());
		o.put("avatarUrl", this.getAvatarUrl());
		o.put("hint", this.getHint());
		o.put("type", this.getType());
		o.put("paymentStatus", this.getPaymentStatus());
		o.put("paymentTime", this.getPaymentTime());
		o.put("invitedFriends", this.getInvitedFriends());
		
		return o;
	}

	public int getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
	
}
