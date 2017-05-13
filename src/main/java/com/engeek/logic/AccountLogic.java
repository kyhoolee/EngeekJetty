package com.engeek.logic;

import java.util.List;

import org.json.JSONObject;
import org.mindrot.BCrypt;

import com.engeek.dao.UserDAO;
import com.engeek.model.User;

public class AccountLogic {

	public static String hashed(String input) {
		return BCrypt.hashpw(input, BCrypt.gensalt());
	}

	public static String hashPassword(String password) {
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		return hashed;
	}

	public static boolean checkPassword(String password, String hashed) {
		return (BCrypt.checkpw(password, hashed));
	}

	public static JSONObject register(String userName, String password) {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONObject error = new JSONObject();

		List<User> users = UserDAO.getUserByUserName(userName);
		if (users.size() != 0 || userName == null || userName.length() == 0
				|| password == null || password.length() == 0) {
			result.put("status", Constant.status_error);
			result.put("data", data);

			error.put("code", Constant.error_db);
			error.put("message",
					"userName existed | userName is null | password is null");

			result.put("error", error);

			return result;
		}

		User u = new User(userName, hashPassword(password));
		boolean r = UserDAO.createUserAccount(u);
		if (r) {
			// String session = updateUserSession(userName);
			result.put("status", Constant.status_ok);

			data.put("result", "success");
			// data.put("sessionKey", session);
			result.put("data", data);

			error.put("code", Constant.error_non);
			error.put("message", "no error");

			result.put("error", error);
		} else {
			result.put("status", Constant.status_error);
			result.put("data", data);

			error.put("code", Constant.error_db);
			error.put("message", "database error");

			result.put("error", error);
		}

		return result;
	}

	public static JSONObject login(String userName, String passWord) {

		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONObject error = new JSONObject();

		List<User> r = UserDAO.getUserByUserName(userName);
		if (r.size() == 0 || userName == null || userName.length() == 0
				|| passWord == null || passWord.length() == 0) {
			result.put("status", Constant.status_error);
			result.put("data", data);

			error.put("code", Constant.error_db);
			error.put("message", "username not existed");

			result.put("error", error);

			return result;
		}

		User shop = r.get(0);
		boolean res = checkPassword(passWord, shop.getPassWord());

		if (res) {
			// String session = updateUserSession(userName);

			result.put("status", Constant.status_ok);

			data.put("result", "success");
			// data.put("sessionKey", session);
			// data.put("userStatus", shop.getStatus());
			result.put("data", data);

			error.put("code", Constant.error_non);
			error.put("message", "no error");

			result.put("error", error);
		} else {
			result.put("status", Constant.status_error);
			result.put("data", data);

			error.put("code", Constant.error_db);
			error.put("message", "wrong password");

			result.put("error", error);
		}

		return result;

	}

	public static JSONObject getProfile(String userName) {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONObject error = new JSONObject();

		List<User> users = UserDAO.getUserByUserName(userName);
		if (users.size() == 0 || userName == null || userName.length() == 0) {
			result.put("status", Constant.status_error);
			result.put("data", data);

			error.put("code", Constant.error_db);
			error.put("message", "userName not existed | userName is null");

			result.put("error", error);

			return result;
		} else {
			User user = users.get(0);
			user.setPassWord("");
			result.put("status", Constant.status_ok);

			data.put("profile", user.toJSON());
			result.put("data", data);

			error.put("code", Constant.error_non);
			error.put("message", "no error");

			result.put("error", error);
		}

		return result;
	}
	
	

	public static JSONObject updateUserProfile(String userName, 
			String name, String gender, String google, String facebook, String avatarUrl,
			int type, int hint, int invitedFriends) {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONObject error = new JSONObject();

		List<User> users = UserDAO.getUserByUserName(userName);
		if (users.size() == 0 || userName == null || userName.length() == 0) {
			result.put("status", Constant.status_error);
			result.put("data", data);

			error.put("code", Constant.error_db);
			error.put("message", "userName not existed | userName is null");

			result.put("error", error);

			return result;
		} else {
			boolean r = UserDAO.updateUserProfile(userName, name, gender, google, facebook, avatarUrl,
					type, hint, invitedFriends);

			if (r) {
				List<User> us = UserDAO.getUserByUserName(userName);
				
				result.put("status", Constant.status_ok);
				
				data.put("profile", us.get(0).toJSON());
				result.put("data", data);

				error.put("code", Constant.error_non);
				error.put("message", "no error");

				result.put("error", error);
			} else {
				result.put("status", Constant.status_error);

				result.put("data", data);

				error.put("code", Constant.error_db);
				error.put("message", "error db");

				result.put("error", error);
			}
		}

		return result;
	}

	public static boolean checkUserSession(String userName, String sessionKey) {
		return true;
	}
	
	public static JSONObject genErrorSession() {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONObject error = new JSONObject();

		result.put("status", Constant.status_error);
		result.put("data", data);

		error.put("code", Constant.error_authen);
		error.put("message", "sessionKey error");

		result.put("error", error);

		return result;
	}

}
